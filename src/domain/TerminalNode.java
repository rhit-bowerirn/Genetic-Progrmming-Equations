package domain;

import java.util.Random;

public class TerminalNode extends Node {

    private int value;
    private static int MIN_VALUE = -20;
    private static int MAX_VALUE = 20;
    private static double VARIABLE_RATE = 0.4;

    public TerminalNode(Random rand) {
        super(rand);
        this.size = 1;
        this.mutate();
    }

    public TerminalNode(int value, Random rand) {
        super(rand);
        this.value = value;
        this.size = 1;
    }

    @Override
    public void mutate() {
        if (this.rand.nextDouble() < VARIABLE_RATE) {
            this.value = Integer.MAX_VALUE;
        } else {
            this.value = rand.nextInt(MIN_VALUE, MAX_VALUE + 1);
        }
    }

    @Override
    public Node deepCopy() {
        return new TerminalNode(this.value, this.rand);
    }

    @Override
    public double evaluate(double x) {
        return this.value > MAX_VALUE ? x : this.value;
    }

    @Override
    public Node get(int index) {
        return this;
    }

    @Override
    public Node replace(int index, Node node) {
        return node;
    }

    @Override
    public String fullExpression() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.value > MAX_VALUE ? "x" : Integer.toString(this.value);
    }

}
