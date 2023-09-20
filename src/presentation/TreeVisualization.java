package presentation;


import javax.swing.*;

import domain.GPTree;
import ga.sim.GeneticAlgorithm;
import ga.viz.display.Visualization;


public class TreeVisualization extends Visualization {
    private GPTreeComponent treeComponent;

    public TreeVisualization() {
        super();
        this.treeComponent = new GPTreeComponent();
    }

    @Override
    public void updateComponent(GeneticAlgorithm ga) {
        GPTree fittest = (GPTree) ga.latest().fittestGenome();
        this.treeComponent.setTree(fittest);
        this.treeComponent.repaint();
    }

    @Override
    public JComponent visualization() {
        return this.treeComponent;
    }

    @Override
    public String name() {
        return "Tree Visualization";
    }
}
