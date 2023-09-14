package evolution.visualization;

import java.awt.GridLayout;

public class DefaultVizController extends VisualizationController {

    public DefaultVizController(Visualization... visualizations) {
        super(visualizations);

        this.setLayout(new GridLayout(1, 2));
        this.setLayout(new GridLayout(1, 2));
        this.add(new VisualizationSelector(this.visualizations.length > 0 ? this.visualizations[0] : null, this.visualizations));
        this.add(new VisualizationSelector(this.visualizations.length > 1 ? this.visualizations[1] : null, this.visualizations));
    }

}
