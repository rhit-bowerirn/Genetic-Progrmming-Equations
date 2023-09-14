package evolution.visualization;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import evolution.simulation.GeneticAlgorithm;

public class SimulationFrame extends JFrame {
    public SimulationFrame(ConstantsController constantsController, Visualization... visualizations) {
        super("Genetic Algorithm Simulation");
        JPanel constantsPanel = new JPanel();
        constantsPanel.add(constantsController);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GeneticAlgorithm geneticAlgorithm = constantsController.startSimulation();
                    startSimulation(geneticAlgorithm, visualizations);
                    // startSimulation(new DefaultVizController(geneticAlgorithm, visualizations));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        constantsPanel.add(constantsController, BorderLayout.CENTER);
        constantsPanel.add(startButton, BorderLayout.SOUTH);
    }

    private void startSimulation(GeneticAlgorithm geneticAlgorithm, Visualization... visualizations) {
        this.removeAll();
        this.add(new SimulationController(new DefaultVizController(visualizations), new DefaultAlgorithmController(geneticAlgorithm)));

    }
}
