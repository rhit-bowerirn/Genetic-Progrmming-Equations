package evolution.visualization;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import evolution.simulation.GeneticAlgorithm;

public class DefaultAlgorithmController extends AlgorithmController {

    public DefaultAlgorithmController(GeneticAlgorithm geneticAlgorithm) {
        super(geneticAlgorithm);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel generationsLabel = new JLabel("Generations: ");
        JTextField generationsInput = new JTextField("200", 5);

        JButton run = new JButton("Run");
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int generations = Integer.parseInt(generationsInput.getText());
                    geneticAlgorithm.run(generations);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton reset = new JButton("Reset");
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                geneticAlgorithm.reset();
            }
        });

        controlPanel.add(generationsLabel);
        controlPanel.add(generationsInput);
        controlPanel.add(run);
        controlPanel.add(reset);

        this.add(controlPanel);
    }

}
