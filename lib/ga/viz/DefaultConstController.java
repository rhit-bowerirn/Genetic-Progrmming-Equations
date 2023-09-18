package ga.viz;
import java.awt.GridLayout;

public class DefaultConstController extends ConstantsController {

    public DefaultConstController(SimulationCreator simCreator, GenomeCreator genomeCreator) {
        super(simCreator, genomeCreator);
        this.setLayout(new GridLayout(1, 2));
        this.add(simCreator);
        this.add(genomeCreator);
    }

}
