package ga.logging;

import java.io.File;

public abstract class Logger {
    protected File file;

    public Logger(String filename) {
        this.file = new File(filename);
    }
    
    public abstract void logPopulation(int generation, double maxFitness, double minFitness, double avgFitness);
}
