package evolution.visualization;

import evolution.simulation.Genome;
import javax.swing.JPanel;

public abstract class GenomeCreator extends JPanel {
    public abstract Genome creatGenome() throws Exception;
}
