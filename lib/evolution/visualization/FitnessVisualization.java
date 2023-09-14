package evolution.visualization;

import java.awt.Color;

import javax.swing.JComponent;

import evolution.simulation.GeneticAlgorithm;
import evolution.simulation.PopulationHistory;
import evolution.simulation.PopulationStats;
import graphing.CoordinatePlaneWindow;
import graphing.LinePlot;
import graphing.Plot;
import graphing.PlotComponent;

public class FitnessVisualization extends Visualization {
    private PlotComponent plotComponent;
    private CoordinatePlaneWindow plane;
    private Plot maxFitnessPlot;
    private Plot minFitnessPlot;
    private Plot avgFitnessPlot;

    public FitnessVisualization(GeneticAlgorithm geneticAlgorithm) {
        super(geneticAlgorithm);
        this.maxFitnessPlot = new LinePlot("Max", new Color(40, 158, 35));
        this.minFitnessPlot = new LinePlot("Min", Color.RED);
        this.avgFitnessPlot = new LinePlot("Average", Color.BLUE);

        this.plane = new CoordinatePlaneWindow();
        this.plotComponent = new PlotComponent("Fitness vs Time", "Generation", "Fitness", this.plane);
        this.plotComponent.addPlots(maxFitnessPlot, avgFitnessPlot, minFitnessPlot);

        this.update();
    }

    @Override
    public void update() {
        PopulationHistory history = this.geneticAlgorithm.history();
        PopulationStats stats = history.latest();
        this.maxFitnessPlot.dataset().addDataPoint(stats.maxFitness());
        this.minFitnessPlot.dataset().addDataPoint(stats.minFitness());
        this.avgFitnessPlot.dataset().addDataPoint(stats.averageFitness());

        this.plane.updateBounds(0, history.generations(),
                history.lowestEverFitness(), history.highestEverFitness());

        this.plotComponent.repaint();
    }

    @Override
    public JComponent visualization() {
        return this.plotComponent;
    }

    @Override
    public String name() {
       return "Fitness Plot";
    }

}
