package evolution.visualization;

import javax.swing.JPanel;

public abstract class VisualizationController extends JPanel {
    protected Visualization[] visualizations;

    public VisualizationController(Visualization... visualizations) {
        this.visualizations = visualizations;
    }
}
