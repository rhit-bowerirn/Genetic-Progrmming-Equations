package domain;

import java.util.Random;
import java.util.function.BiFunction;

public class FunctionalNode extends Node {

    private BiFunction<Double, Double, Double> biFunction;
    private int rank;


    public FunctionalNode(int height, Random rand) {
        super(rand);
        this.biFunction = FunctionFactory.randomFunction(rand);

        if (height > 1) {
            this.left = NodeFactory.randomNode(height - 1, rand);
            this.right = NodeFactory.randomNode(height - 1, rand);
            this.rank = this.left.size();
        } else {
            this.left = NodeFactory.randomTerminalNode(rand);
            this.right = NodeFactory.randomTerminalNode(rand);
            this.rank = 1;
        }

        this.size = this.rank + this.right.size() + 1;
    }

    public FunctionalNode(Node left, Node right, BiFunction<Double, Double, Double> biFunction, Random rand) {
        super(rand);
        this.left = left;
        this.right = right;
        this.biFunction = biFunction;
        this.rank = this.left.size();
        this.size = this.rank + this.right.size() + 1;
    }

    @Override
    public void mutate() {
        this.biFunction = FunctionFactory.randomFunction(this.rand);
    }

    @Override
    public Node deepCopy() {
        return new FunctionalNode(this.left.deepCopy(), this.right.deepCopy(), this.biFunction, this.rand);
    }

    @Override
    public double evaluate(double x) {
        return this.biFunction.apply(this.left.evaluate(x), this.right.evaluate(x));
    }

    @Override
    public Node replace(int index, Node node) {
        if (index == this.rank) {
            return node;
        }

        if (index < this.rank) {
            this.left = this.left.replace(index, node);
            this.rank = this.left.size();
        }

        if (index > this.rank) {
            this.right = this.right.replace(index - (this.rank + 1), node);
        }

        this.size = this.rank + this.right.size() + 1;

        return this;
    }

    @Override
    public Node get(int index) {
        if (index < this.rank) {
            return this.left.get(index);
        }

        if (index > this.rank) {
            return this.right.get(index - (this.rank + 1));
        }

        return this;
    }

    @Override
    public String fullExpression() {
        String functionName = this.toString();
        if (!functionName.equals("\u221A")) {
            functionName = this.left.fullExpression() + " " + functionName;
        }

        return " (" + functionName + " " + this.right.fullExpression() + ")";
    }

    @Override 
    public String toString() {
        return FunctionFactory.binaryFunctionName(this.biFunction);
    }

}
