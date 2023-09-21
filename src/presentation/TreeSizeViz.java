package presentation;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;

import ga.sim.alg.GeneticAlgorithm;
import ga.viz.display.Visualization;
import graphing.data.Dataset;
import graphing.plotting.PlotComponent;
import graphing.plotting.canvas.UpScalingPlane;
import graphing.plotting.plots.Histogram;
import graphing.plotting.plots.Plot;
import domain.GPTree;

public class TreeSizeViz extends Visualization {
    private PlotComponent plotComponent;
    private Plot treeHist;

    public TreeSizeViz() {
        this.plotComponent = new PlotComponent("Tree Sizes", "Tree Size", "Number of Trees", new UpScalingPlane());
        this.treeHist = new Histogram("Tree Size", Color.ORANGE, 5);
        this.plotComponent.addPlot(treeHist);
    }

    @Override
    public JComponent visualization() {
        return this.plotComponent;
    }

    @Override
    public String name() {
        return "Tree Sizes";
    }

    @Override
    protected void updateComponent(GeneticAlgorithm ga) {
        List<Double> treeSizes = ga.population().stream()
                .mapToDouble(genome -> (double) ((GPTree) genome).size())
                .boxed()
                .collect(Collectors.toList());

        this.treeHist.setData(new Dataset(treeSizes, true));
        this.plotComponent.repaint();
    }

}
