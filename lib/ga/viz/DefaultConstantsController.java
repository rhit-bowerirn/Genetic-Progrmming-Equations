package ga.viz;
import java.awt.GridLayout;

public class DefaultConstantsController extends ConstantsController {

    public DefaultConstantsController(SimulationCreator simCreator, GenomeCreator genomeCreator) {
        super(simCreator, genomeCreator);
        this.setLayout(new GridLayout(1, 2));
        this.add(simCreator);
        this.add(genomeCreator);
    }

}
