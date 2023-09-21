package presentation;

import java.awt.Color;

import javax.swing.JComponent;

import domain.GPTree;
import ga.sim.alg.GeneticAlgorithm;
import ga.viz.display.Visualization;
import graphing.plotting.PlotComponent;
import graphing.plotting.canvas.AutoScalingPlane;
import graphing.plotting.plots.LinePlot;
import graphing.plotting.plots.Plot;
import graphing.plotting.plots.ScatterPlot;

public class EquationPlotViz extends Visualization {
    private PlotComponent plotComponent;
    private Plot targetData;
    private Plot predictions;

    public EquationPlotViz() {
        super();
        this.targetData = new ScatterPlot("Target Data", Color.BLACK);
        this.predictions = new LinePlot("Predictions", Color.BLUE);

        this.plotComponent = new PlotComponent("Best Evolved Equation", "x data", "y data", new AutoScalingPlane());
        this.plotComponent.addPlots(targetData, predictions);
    }

    @Override
    public void updateComponent(GeneticAlgorithm ga) {
        GPTree fittest = (GPTree) ga.latest().fittestGenome();
        this.predictions.setData(fittest.imputeData());
        this.targetData.setData(fittest.targetData());
        this.plotComponent.repaint();
    }

    @Override
    public JComponent visualization() {
        return this.plotComponent;
    }

    @Override
    public String name() {
        return "Equation Plot";
    }

}
