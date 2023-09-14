package evolution.visualization;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class SimulationController extends JPanel {

    public SimulationController(VisualizationController vizController, AlgorithmController algController) {
        this.add(vizController, BorderLayout.CENTER);
        this.add(algController, BorderLayout.SOUTH);
    }
    
}
