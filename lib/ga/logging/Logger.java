package ga.logging;

import java.io.File;

public abstract class Logger {
    protected File file;

    public Logger(String filename) throws Exception {
        this.file = new File(filename);
    }

    public Logger(File file) throws Exception {
        this.file = file;
    }

    public abstract void logPopulation(int generation, double maxFitness, double minFitness, double avgFitness,
            String fittestGenome) throws Exception;
}
