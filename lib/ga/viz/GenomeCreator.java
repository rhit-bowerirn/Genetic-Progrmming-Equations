package ga.viz;

import java.util.Random;

import javax.swing.JPanel;

import ga.sim.Genome;

public abstract class GenomeCreator extends JPanel {
    public abstract Genome creatGenome(Random rand) throws Exception;
}
