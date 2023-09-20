package presentation;

import java.awt.Color;

import javax.swing.JComponent;

import domain.GPTree;
import ga.sim.GeneticAlgorithm;
import ga.viz.display.Visualization;
import graphing.plotting.CoordinatePlaneWindow;
import graphing.plotting.LinePlot;
import graphing.plotting.Plot;
import graphing.plotting.PlotComponent;
import graphing.plotting.ScatterPlot;

public class EquationPlotVisualization extends Visualization {
    private PlotComponent plotComponent;
    private CoordinatePlaneWindow plane;
    private Plot targetData;
    private Plot predictions;

    public EquationPlotVisualization() {
        super();
        this.targetData = new ScatterPlot("Target Data", Color.BLACK);
        this.predictions = new LinePlot("Predictions", Color.BLUE);

        this.plane = new CoordinatePlaneWindow();
        this.plotComponent = new PlotComponent("Best Evolved Equation", "x data", "y data", this.plane);
        this.plotComponent.addPlots(targetData, predictions);
    }

    @Override
    public void updateComponent(GeneticAlgorithm ga) {
        GPTree fittest = (GPTree) ga.latest().fittestGenome();
        this.predictions.setData(fittest.imputeData());
        this.targetData.setData(fittest.targetData());

        this.plane.updateBounds(predictions.dataset().minX(),
                                Math.max(predictions.dataset().maxX(), targetData.dataset().maxX()),
                                Math.min(predictions.dataset().minY(), targetData.dataset().minY()),
                                Math.max(predictions.dataset().maxY(), targetData.dataset().maxY()));
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
