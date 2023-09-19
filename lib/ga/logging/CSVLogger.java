package ga.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVLogger extends Logger {
    private static final String CSV_HEADERS = "Generation,Max Fitness,Min Fitness,Avg Fitness,Fittest Genome";

    public CSVLogger(String filename) throws Exception {
        super(filename);
        this.writeToFile(CSV_HEADERS);
    }

    public CSVLogger(File file) throws Exception {
        super(file);
        this.writeToFile(CSV_HEADERS);
    }

    @Override
    public void logPopulation(int generation, double maxFitness, double minFitness, double avgFitness,
            String fittestGenome) throws Exception {
        this.writeToFile(generation + "," + maxFitness + "," + minFitness + "," + avgFitness + ", " + fittestGenome);
    }

    private void writeToFile(String line) throws Exception {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.file, true)));
        writer.println(line);
        writer.close();
    }

}
