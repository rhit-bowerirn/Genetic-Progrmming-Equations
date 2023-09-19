package graphing.fileIO;

import java.io.File;

public abstract class FileHandler {
    protected File file;

    public FileHandler(String filename) throws Exception {
        this.file = new File(filename);
    }

    public FileHandler(File file) throws Exception {
        this.file = file;
    }
}
