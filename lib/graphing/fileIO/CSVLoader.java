package graphing.fileIO;

import java.io.File;

public class CSVLoader extends FileHandler implements Loader {

    public CSVLoader(String filename) throws Exception {
        super(filename);
    }

    public CSVLoader(File file) throws Exception {
        super(file);
    }
    
}
