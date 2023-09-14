package domain;

import java.util.Random;

public abstract class Node {
    protected Random rand;
    protected int size;
    protected Node left;
    protected Node right;

    public Node(Random rand) {
        this.rand = rand;
        this.left = null;
        this.right = null;
    }

    public int size() {
        return this.size;
    }

    public Node left() {
        return this.left;
    }
    
    public Node right() {
        return this.right;
    }

    public boolean hasLeft() {
        return this.left != null;
    }
    
    public boolean hasRight() {
        return this.right != null;
    }

    public abstract Node get(int index);

    public abstract Node replace(int index, Node node);

    public abstract void mutate();

    public abstract Node deepCopy();

    public abstract double evaluate(double x);

    public abstract String fullExpression();

    public abstract String toString();



    
}
