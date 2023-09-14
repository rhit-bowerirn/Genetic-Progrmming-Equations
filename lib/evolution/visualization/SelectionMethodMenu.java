package evolution.visualization;

import javax.swing.JComboBox;

import evolution.simulation.SelectByProportion;
import evolution.simulation.SelectByRank;
import evolution.simulation.SelectByRoulette;
import evolution.simulation.SelectByTournament;
import evolution.simulation.SelectByTruncation;
import evolution.simulation.SelectionMethod;

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
