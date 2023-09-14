package evolution.visualization;

import evolution.simulation.GeneticAlgorithm;
import javax.swing.JPanel;

public abstract class SimulationCreator extends JPanel {
    public abstract GeneticAlgorithm initializeSimulation(GenomeCreator genomeCreator) throws Exception;
}
