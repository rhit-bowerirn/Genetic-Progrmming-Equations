package ga.viz;

import javax.swing.JPanel;

import ga.sim.GeneticAlgorithm;

public abstract class SimulationCreator extends JPanel {
    public abstract GeneticAlgorithm initializeSimulation(GenomeCreator genomeCreator) throws Exception;
}
