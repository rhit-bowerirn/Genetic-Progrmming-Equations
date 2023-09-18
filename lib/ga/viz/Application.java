package ga.viz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ga.sim.GeneticAlgorithm;

public class Application extends JPanel {
    private ConstantsController constController;
    private VisualizationController vizController;

    public Application(ConstantsController constController, VisualizationController vizController) {

        this.constController = constController;
        this.vizController = vizController;
        this.setLayout(new BorderLayout());

        this.reset();
    }

    public void reset() {
        this.removeAll();
        this.add(constController, BorderLayout.CENTER);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GeneticAlgorithm geneticAlgorithm = constController.startSimulation();
                    startSimulation(geneticAlgorithm);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startPanel.add(startButton);
        this.add(startPanel, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    private void startSimulation(GeneticAlgorithm geneticAlgorithm) {
        this.removeAll();

        JButton back = new JButton("Back");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(back);

        this.add(vizController, BorderLayout.CENTER);
        this.add(new DefaultAlgorithmController(geneticAlgorithm), BorderLayout.SOUTH);
        //this.add(buttonPanel, BorderLayout.SOUTH);

        this.vizController.beginListening(geneticAlgorithm);
        this.revalidate();
        this.repaint();
    }
}
