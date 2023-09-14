package presentation;

import java.awt.Color;

import javax.swing.JComponent;

import domain.GPTree;
import evolution.simulation.GeneticAlgorithm;
import evolution.visualization.Visualization;
import graphing.CoordinatePlaneWindow;
import graphing.LinePlot;
import graphing.Plot;
import graphing.PlotComponent;
import graphing.ScatterPlot;

public class EquationPlotVisualization extends Visualization {
    private PlotComponent plotComponent;
    private CoordinatePlaneWindow plane;
    private Plot targetData;
    private Plot predictions;

    public EquationPlotVisualization(GeneticAlgorithm geneticAlgorithm) {
        super(geneticAlgorithm);
        this.targetData = new ScatterPlot("Target Data", Color.BLACK);
        this.predictions = new LinePlot("Predictions", Color.BLUE);

        this.plane = new CoordinatePlaneWindow();
        this.plotComponent = new PlotComponent("Best Evolved Equation", "x data", "y data", this.plane);
        this.plotComponent.addPlots(targetData, predictions);

        this.update();
    }

    @Override
    public void update() {
        GPTree fittest = (GPTree) this.geneticAlgorithm.history().latest().fittestGenome();
        this.predictions.setData(fittest.predictions());
        this.targetData.setData(fittest.targetData());

        this.plane.updateBounds(this.targetData);
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
