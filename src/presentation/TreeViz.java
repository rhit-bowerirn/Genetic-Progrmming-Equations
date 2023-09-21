package presentation;

import javax.swing.JComponent;

import domain.GPTree;
import ga.sim.alg.GeneticAlgorithm;
import ga.viz.display.Visualization;


public class TreeViz extends Visualization {
    private GPTreeComponent treeComponent;

    public TreeViz() {
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
