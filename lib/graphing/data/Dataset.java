package graphing.data;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList; //for concurrency issues with drawing
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Dataset {
    private List<Point> data;

    public Dataset() {
        this.data = new CopyOnWriteArrayList<Point>();
    }

    public Dataset(List<Point> data) {
        this.data = new CopyOnWriteArrayList<Point>(data);
    }

    public Dataset(Map<Double, Double> data) {
        this.data = this.loadFromMap(data);
    }

    public Dataset(List<Double> data, boolean isZeroIndexed) {
        this.data = this.loadFromList(data, (isZeroIndexed ? 0 : 1));
    }

    public boolean addDataPoints(List<Point> newData) {
        return this.data.addAll(newData);
    }

    public boolean addDataPoints(Map<Double, Double> newData) {
        return this.data.addAll(this.loadFromMap(newData));
    }

    public boolean addDataPoints(List<Double> newData, boolean isZeroIndexed) {
        return this.data.addAll(this.loadFromList(newData, this.data.size() + (isZeroIndexed ? 0 : 1)));
    }

    public boolean addDataPoint(Point dataPoint) {
        return this.data.add(dataPoint);
    }

    public boolean addDataPoint(double x, double y) {
        return this.data.add(new Point(x, y));
    }

    public boolean addDataPoint(double y) {
        return this.data.add(new Point(this.data.size(), y));
    }

    public boolean removeDataPoint(Point dataPoint) {
        return this.data.remove(dataPoint);
    }

    // only removes one, not all duplicates
    public boolean removeDataPoint(double x, double y) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).compareTo(new Point(x, y)) == 0) {
                this.data.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Double> xValues() {
        return this.data.stream().map(p -> p.x).collect(Collectors.toList());
    }

    public List<Double> yValues() {
        return this.data.stream().map(p -> p.y).collect(Collectors.toList());
    }

    public double minX() {
        return this.xValues().stream().min(Double::compareTo).orElse(0.0);
    }

    public double maxX() {
        return this.xValues().stream().max(Double::compareTo).orElse(0.0);
    }

    public double minY() {
        return this.yValues().stream().min(Double::compareTo).orElse(0.0);
    }

    public double maxY() {
        return this.yValues().stream().max(Double::compareTo).orElse(0.0);
    }

    public Stream<Point> stream() {
        return this.data.stream();
    }

    public Dataset transformCopy(Function<Point, Point> action) {
        return new Dataset(this.transformData(action));
    }

    public void transform(Function<Point, Point> action) {
        this.data = this.transformData(action);
    }

    public void swapData(List<Point> newData) {
        this.data = newData;
    }

    public void swapData(Map<Double, Double> newData) {
        this.data = this.loadFromMap(newData);
    }

    public void swapData(List<Double> newData, boolean isZeroIndexed) {
        this.data = this.loadFromList(newData, (isZeroIndexed ? 0 : 1));
    }

    public void clear() {
        this.data.clear();
    }

    public void sort() {
        Collections.sort(this.data, Point::compareTo);
    }

    public List<Point> data() {
        return this.data;
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.data.size() == 0;
    }

    // public boolean equals(Dataset o) {
    //     return this.data.equals(o.data());
    // }

    public Iterator<Point> iterator() {
        return this.data.iterator();
    }

    public void removeDuplicates() {
        this.sort();
        for (int i = 1; i < this.data.size(); i++) {
            if (this.data.get(i - 1).compareTo(this.data.get(i)) == 0) {
                this.data.remove(i);
                i--;
            }
        }
    }

    private List<Point> transformData(Function<Point, Point> action) {
        return this.data.stream().map(action).collect(Collectors.toList());
    }

    private List<Point> loadFromMap(Map<Double, Double> data) {
        List<Point> points = new CopyOnWriteArrayList<Point>();
        for (double key : data.keySet()) {
            double x = key;
            double y = data.get(key);
            points.add(new Point(x, y));
        }
        Collections.sort(points, Point.xComparator());
        return points;
    }

    private List<Point> loadFromList(List<Double> data, int startIndex) {
        return IntStream.range(startIndex, data.size() + startIndex)
                .mapToObj(index -> new Point(index, data.get(index)))
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }
}
