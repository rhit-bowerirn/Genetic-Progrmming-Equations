package graphing.data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList; //for concurrency safety with drawing
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import graphing.fileIO.CSVLoader;
import graphing.fileIO.Loader;


public class Dataset extends CopyOnWriteArrayList<Point> {
    public Dataset() {
        super();
    }

    public Dataset(String csvName) throws Exception {
        super();
        this.loadCSV(csvName);
    }

    public Dataset(Collection<Point> data) {
        super(data);
    }

    public Dataset(Map<Double, Double> data) {
        super(loadFromMap(data));
    }

    public Dataset(List<Double> data, boolean isZeroIndexed) {
        super(loadFromList(data, (isZeroIndexed ? 0 : 1)));
    }

    public void loadCSV(String filename) throws Exception {
        Loader loader = new CSVLoader(filename);
    }

    public boolean addAll(Map<Double, Double> newData) {
        return this.addAll(loadFromMap(newData));
    }

    public boolean addAll(List<Double> newData, boolean isZeroIndexed) {
        return this.addAll(loadFromList(newData, this.size() + (isZeroIndexed ? 0 : 1)));
    }

    public boolean add(double x, double y) {
        return this.add(new Point(x, y));
    }

    public boolean add(double y) {
        return this.add(new Point(this.size(), y));
    }

    // only removes first one, not all duplicates
    public Point remove(double x, double y) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).compareTo(new Point(x, y)) == 0) {
                return this.remove(i);
            }
        }
        return null;
    }

    public List<Double> xValues() {
        return this.stream().map(p -> p.x).collect(Collectors.toList());
    }

    public List<Double> yValues() {
        return this.stream().map(p -> p.y).collect(Collectors.toList());
    }

    public double minX() {
        return this.stream().min(Point.xComparator()).orElse(new Point(0, 0)).x;
    }

    public double maxX() {
        return this.stream().max(Point.xComparator()).orElse(new Point(0, 0)).x;
    }

    public double minY() {
        return this.stream().min(Point.yComparator()).orElse(new Point(0, 0)).y;
    }

    public double maxY() {
        return this.stream().max(Point.xComparator()).orElse(new Point(0, 0)).y;
    }

    public void replaceData(Collection<Point> newData) {
        this.clear();
        this.addAll(newData);
    }

    public void replaceData(Map<Double, Double> newData) {
        this.clear();
        this.addAll(loadFromMap(newData));
    }

    public void replaceData(List<Double> newData, boolean isZeroIndexed) {
        this.clear();
        this.addAll(loadFromList(newData, (isZeroIndexed ? 0 : 1)));
    }

    public void sort() {
        this.sort(Point::compareTo);
    }

    public Dataset transform(Function<Point, Point> action) {
        return new Dataset(this.stream().map(action).collect(Collectors.toList()));
    }

    public void removeDuplicates() {
        this.sort();
        for (int i = 1; i < this.size(); i++) {
            if (this.get(i - 1).compareTo(this.get(i)) == 0) {
                this.remove(i);
                i--;
            }
        }
    }

    private static List<Point> loadFromMap(Map<Double, Double> data) {
        List<Point> points = new CopyOnWriteArrayList<Point>();
        for (double key : data.keySet()) {
            double x = key;
            double y = data.get(key);
            points.add(new Point(x, y));
        }
        Collections.sort(points, Point.xComparator());
        return points;
    }

    private static List<Point> loadFromList(List<Double> data, int startIndex) {
        return IntStream.range(startIndex, data.size() + startIndex)
                .mapToObj(index -> new Point(index, data.get(index)))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }
}
