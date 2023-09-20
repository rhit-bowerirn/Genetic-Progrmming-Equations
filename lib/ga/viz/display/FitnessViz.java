package ga.viz.display;

import java.awt.Color;

import javax.swing.JComponent;

import ga.sim.GeneticAlgorithm;
import ga.sim.records.PopulationStats;
import graphing.plotting.CoordinatePlaneWindow;
import graphing.plotting.LinePlot;
import graphing.plotting.Plot;
import graphing.plotting.PlotComponent;

public class FitnessViz extends Visualization {
    private PlotComponent plotComponent;
    private CoordinatePlaneWindow plane;
    private Plot maxFitnessPlot;
    private Plot minFitnessPlot;
    private Plot avgFitnessPlot;

    public FitnessViz() {
        super();
        this.maxFitnessPlot = new LinePlot("Max", new Color(40, 158, 35));
        this.minFitnessPlot = new LinePlot("Min", Color.RED);
        this.avgFitnessPlot = new LinePlot("Average", Color.BLUE);

        this.plane = new CoordinatePlaneWindow();
        this.plotComponent = new PlotComponent("Fitness vs Time", "Generation", "Fitness", this.plane);
        this.plotComponent.addPlots(maxFitnessPlot, avgFitnessPlot, minFitnessPlot);
    }

    @Override
    public void updateComponent(GeneticAlgorithm ga) {
        PopulationStats stats = ga.latest();
        this.maxFitnessPlot.dataset().add(stats.maxFitness());
        this.minFitnessPlot.dataset().add(stats.minFitness());
        this.avgFitnessPlot.dataset().add(stats.averageFitness());

        this.plane.updateBounds(0, ga.generations(),
                ga.lowestEverFitness(), ga.highestEverFitness());

        this.plotComponent.repaint();
    }

    @Override
    public void clearComponent() {
        this.maxFitnessPlot.clearData();
        this.minFitnessPlot.clearData();
        this.avgFitnessPlot.clearData();
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
