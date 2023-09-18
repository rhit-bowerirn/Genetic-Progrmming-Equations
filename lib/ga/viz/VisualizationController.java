package ga.viz;

import java.awt.Component;

import javax.swing.JPanel;

import ga.sim.GeneticAlgorithm;

public abstract class VisualizationController extends JPanel {
    protected Visualization[] visualizations;

    public VisualizationController(Visualization... visualizations) {
        this.visualizations = visualizations;

    }

    public void watch(Component watcher) {
        for (Visualization viz : this.visualizations) {
            viz.watch(watcher);
        }
    }

    public void beginListening(GeneticAlgorithm ga) {
        for (Visualization viz : this.visualizations) {
            ga.subscribe(viz);
        }
    }

}
