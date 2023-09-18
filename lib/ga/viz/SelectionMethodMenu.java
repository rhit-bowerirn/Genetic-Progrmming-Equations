package ga.viz;

import javax.swing.JComboBox;

import ga.sim.SelectByProportion;
import ga.sim.SelectByRank;
import ga.sim.SelectByRoulette;
import ga.sim.SelectByTournament;
import ga.sim.SelectByTruncation;
import ga.sim.SelectionMethod;

public class SelectionMethodMenu extends JComboBox<SelectionMethod> {

    public SelectionMethodMenu() {
        this.addItem(new SelectByProportion());
        this.addItem(new SelectByRank());
        this.addItem(new SelectByRoulette());
        this.addItem(new SelectByTournament());
        this.addItem(new SelectByTruncation());
    }

    public SelectionMethod selectionMethod() {
        return (SelectionMethod) this.getSelectedItem();
    }
}
