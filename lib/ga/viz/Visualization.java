package ga.viz;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import ga.sim.GeneticAlgorithm;
import ga.sim.Observer;

public abstract class Visualization implements Observer {
    private List<Component> watchers;

    public Visualization() {
        this.watchers = new ArrayList<Component>();
    }

    public void watch(Component watcher) {
        this.watchers.add(watcher);
    }

    private void updateWatchers() {
        this.watchers.forEach(watcher -> {watcher.revalidate(); watcher.repaint(); });
    }

    @Override
    public final void update(GeneticAlgorithm ga) {
        this.updateComponent(ga);
        this.updateWatchers();
    }

    @Override
    public final void reset(GeneticAlgorithm ga) {
        this.clearComponent();
        this.update(ga);
    }

    public abstract JComponent visualization();

    public abstract String name();

    protected abstract void updateComponent(GeneticAlgorithm ga);

    protected void clearComponent() {
        //default do nothing
    };

}
