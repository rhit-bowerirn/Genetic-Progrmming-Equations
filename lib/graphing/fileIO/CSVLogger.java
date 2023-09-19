package graphing.fileIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVLogger extends FileHandler implements Logger {
    private static final String CSV_HEADERS = "x,y";

    public CSVLogger(String filename) throws Exception {
        super(filename);
        this.writeToFile(CSV_HEADERS);
    }

    public CSVLogger(File file) throws Exception {
        super(file);
        this.writeToFile(CSV_HEADERS);
    }

    @Override
    public void logPoint(double x, double y) throws Exception {
        this.writeToFile(x + "," + y + ",");
    }

    private void writeToFile(String line) throws Exception {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(this.file, true)));
        writer.println(line);
        writer.close();

    }
}
