package graphing.fileIO;

import java.io.File;

public abstract class Logger {
    protected File file;

    public Logger(String filename) throws Exception {
        this.file = new File(filename);
    }

    public Logger(File file) throws Exception {
        this.file = file;
    }

    public abstract void logPoint(double x, double y) throws Exception;
}
