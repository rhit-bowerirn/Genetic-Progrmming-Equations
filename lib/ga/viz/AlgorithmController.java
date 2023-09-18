package ga.viz;

import javax.swing.JPanel;

import ga.sim.GeneticAlgorithm;

public abstract class AlgorithmController extends JPanel {
    protected GeneticAlgorithm ga;

    public AlgorithmController(GeneticAlgorithm ga) {
        this.ga = ga;
    }

    public final void run(int generations) {
        Thread gaThread = new Thread(new Runnable() {

            @Override
            public void run() {
                ga.run(generations);
            }

        });

        gaThread.start();

    }

    public final void step() {
        this.ga.nextGeneration();
    }

    public final void reset() {
        this.ga.reset();
    }

    public final void logCSV(String filename) {
        this.ga.logCSV(filename);
    }

}
