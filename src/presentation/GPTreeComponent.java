package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;

import domain.GPTree;
import domain.Node;
import java.awt.Point;

public class GPTreeComponent extends JComponent {
    private GPTree tree;
    private Map<Node, Point> nodePositions;

    public GPTreeComponent() {
        this.nodePositions = new HashMap<Node, Point>();
        this.setPreferredSize(new Dimension(500, 500));
    }

    public void setTree(GPTree tree) {
        this.tree = tree;
        this.nodePositions.clear();
    }

    private void calculateNodePositions(Node node, int level, int left, int right) {
        if (node == null) {
            return;
        }

        int x = left + (right - left) / 2;
        int y = level * 50; // Adjust the vertical spacing as needed

        nodePositions.put(node, new Point(x, y));

        int childWidth = (right - left) * (level / 5 + 1) / 2; // Adjust the divisor for narrower spacing

        if (node.hasLeft()) {
            calculateNodePositions(node.left(), level + 1, x - childWidth, x);
        }

        if (node.hasRight()) {
            calculateNodePositions(node.right(), level + 1, x, x + childWidth);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.calculateNodePositions(this.tree.root(), 0, 0, getWidth());
        this.drawTree(g, this.tree.root());
    }

    private void drawTree(Graphics g, Node node) {
        Point position = nodePositions.get(node);

        if (position == null) {
            return;
        }

        g.setColor(Color.BLACK);
        g.drawString(node.toString(), position.x - 4, position.y + 15);

        if (node.hasLeft() && !node.toString().equals("\u221A")) {
            Point leftPosition = nodePositions.get(node.left());
            if (leftPosition != null) {
                g.drawLine(position.x, position.y + 20, leftPosition.x, leftPosition.y);
                drawTree(g, node.left());
            }
        }

        if (node.hasRight()) {
            Point rightPosition = nodePositions.get(node.right());
            if (rightPosition != null) {
                g.drawLine(position.x, position.y + 20, rightPosition.x, rightPosition.y);
                drawTree(g, node.right());
            }
        }
    }
}
