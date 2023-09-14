package evolution.visualization;

import javax.swing.JPanel;

import evolution.simulation.GeneticAlgorithm;

public abstract class AlgorithmController extends JPanel {
    protected GeneticAlgorithm geneticAlgorithm;

    public AlgorithmController(GeneticAlgorithm geneticAlgorithm){
        this.geneticAlgorithm = geneticAlgorithm;
    }

    public final void run(int generations) {
        this.geneticAlgorithm.run(generations);
    }

    public final void step() {
        this.geneticAlgorithm.nextGeneration();
    }

    public final void reset() {
        this.geneticAlgorithm.reset();
    }

    public final void logCSV(String filename) {
        this.geneticAlgorithm.logCSV(filename);
    }

}
