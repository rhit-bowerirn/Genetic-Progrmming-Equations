package ga.sim;

public class IndefiniteRunner extends AlgorithmRunner {

    public IndefiniteRunner(GeneticAlgorithm ga) {
        super(ga);
    }

    @Override
    public void run() {
        while (this.isRunning) {
            this.ga.nextGeneration();
        }
    }
    
}
