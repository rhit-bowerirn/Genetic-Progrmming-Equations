package domain;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ga.sim.Genome;
import graphing.data.Dataset;
import graphing.data.Point;

public class GPTree extends Genome {
    private Node root;
    private Dataset data;
    private int sizeLimit;
    private double fitness;

    public GPTree(int maxDepth, int sizeLimit, Dataset data, Random rand) {
        super(rand);
        this.data = data;
        this.sizeLimit = sizeLimit;
        this.root = NodeFactory.randomNode(maxDepth, rand);
        this.updateFitness();
    }

    public GPTree(Node root, int sizeLimit, Dataset data, Random rand) {
        super(rand);
        this.root = root;
        this.data = data;
        this.sizeLimit = sizeLimit;
        this.updateFitness();
    }

    public Node root() {
        return this.root;
    }

    public int size() {
        return this.root.size();
    }

    public Dataset targetData() {
        return this.data;
    }

    public Dataset predictions() {
        return this.data = this.data.transform(p -> new Point(p.x, this.predict(p.x)));
    }

    public Dataset imputeData() {
        List<Point> imputations = IntStream.range(0, (int) (this.targetData().maxX()+ 1.5))
                .mapToObj(this::prediction)
                .collect(Collectors.toList());
        return new Dataset(imputations);
    }

    public double score() {
        double sum = 0;
        Iterator<Point> iterator = this.data.iterator();
        while (iterator.hasNext()) {
            Point p = iterator.next();
            sum += Math.pow(this.predict(p.x) - p.y, 2);
        }

        double accuracy = 1 / (1 + Math.sqrt(sum));
        double sizePenalty = 0;
        if (this.size() > this.sizeLimit) {
            // at sizeLimit the fitness will be penalized by .1 and grow quadratically
            sizePenalty = this.size() * this.size() / (double) (100 * this.sizeLimit * this.sizeLimit);
        }
        return accuracy - sizePenalty;
    }

    public double predict(double x) {
        return this.root.evaluate(x);
    }

    public Point prediction(double x) {
        return new Point(x, this.root.evaluate(x));
    }

    public GPTree deepCopy() {
        return new GPTree(this.root.deepCopy(), this.sizeLimit, this.data, this.rand);
    }

    public Node get(int index) {
        return this.root.get(index);
    }

    public void replace(int index, Node node) {
        this.root = this.root.replace(index, node);
        this.updateFitness();
    }

    public void updateFitness() {
        this.fitness = this.score();
    }

    @Override
    public Genome crossover(Genome other) {
        GPTree child = ((GPTree) other).deepCopy();
        int crossoverIndex = this.rand.nextInt(this.size());
        int replacementIndex = this.rand.nextInt(child.size());
        child.replace(replacementIndex, this.get(crossoverIndex).deepCopy());
        return child;
    }

    @Override
    public double fitness() {
        return this.fitness;
    }

    @Override
    public void mutate(double mutationRate) {
        if (this.rand.nextDouble() < mutationRate) {
            this.get(this.rand.nextInt(this.size())).mutate();
        }
        this.updateFitness();
    }

    @Override
    public String toString() {
        return this.root.fullExpression();
    }
}
