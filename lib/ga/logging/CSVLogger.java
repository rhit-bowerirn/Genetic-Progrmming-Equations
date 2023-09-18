package ga.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVLogger extends Logger {
    private static final String CSV_HEADERS = "Generation,Max Fitness,Min Fitness,Avg Fitness";

    public CSVLogger(String filename) {
        super(filename);
        this.writeToFile(CSV_HEADERS);
    }

    @Override
    public void logPopulation(int generation, double maxFitness, double minFitness, double avgFitness) {
        this.writeToFile(generation + "," + maxFitness + "," + minFitness + "," + avgFitness);
    }

    private void writeToFile(String line) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.file, true)));
            writer.println(line);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
