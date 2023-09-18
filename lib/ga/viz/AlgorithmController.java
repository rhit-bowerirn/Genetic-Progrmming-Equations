package ga.viz;

import javax.swing.JPanel;

import ga.sim.GeneticAlgorithm;

public abstract class AlgorithmController extends JPanel {
    protected GeneticAlgorithm geneticAlgorithm;

    public AlgorithmController(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;
    }

    public final void run(int generations) {
        Thread gaThread = new Thread(new Runnable() {

            @Override
            public void run() {
                geneticAlgorithm.run(generations);
            }

        });

        gaThread.start();

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
