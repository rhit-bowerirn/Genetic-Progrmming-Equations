package evolution.visualization;
import javax.swing.JComponent;

import evolution.simulation.GeneticAlgorithm;
import evolution.simulation.Observer;

public abstract class Visualization extends JComponent implements Observer {
    protected GeneticAlgorithm geneticAlgorithm;

    public Visualization(GeneticAlgorithm geneticAlgorithm) {
        this.geneticAlgorithm = geneticAlgorithm;

        this.geneticAlgorithm.subscribe(this);
    }

    public abstract void update();

    public abstract JComponent visualization();

    public abstract String name();
}
