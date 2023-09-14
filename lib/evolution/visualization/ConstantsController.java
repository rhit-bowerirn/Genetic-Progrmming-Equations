package evolution.visualization;

import javax.swing.JPanel;

import evolution.simulation.GeneticAlgorithm;
public abstract class ConstantsController extends JPanel {
    protected SimulationCreator simCreator;
    protected GenomeCreator genomeCreator;

    public ConstantsController(SimulationCreator simCreator, GenomeCreator genomeCreator) {
        this.simCreator = simCreator;
        this.genomeCreator = genomeCreator;
    }

    public GeneticAlgorithm startSimulation() throws Exception {
        return this.simCreator.initializeSimulation(this.genomeCreator);
    }
}
