package presentation;


import javax.swing.*;

import domain.GPTree;
import evolution.simulation.GeneticAlgorithm;
import evolution.visualization.Visualization;


public class TreeVisualization extends Visualization {
    private GPTreeComponent treeComponent;

    public TreeVisualization(GeneticAlgorithm geneticAlgorithm) {
        super(geneticAlgorithm);
        this.treeComponent = new GPTreeComponent();
    }

    @Override
    public void update() {
        GPTree fittest = (GPTree) this.geneticAlgorithm.history().latest().fittestGenome();
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
