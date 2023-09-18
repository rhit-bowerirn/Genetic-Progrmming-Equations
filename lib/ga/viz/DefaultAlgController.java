package ga.viz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ga.sim.GeneticAlgorithm;

public class DefaultAlgController extends AlgorithmController {

    public DefaultAlgController(GeneticAlgorithm ga) {
        super(ga);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        JLabel generationsLabel = new JLabel("Generations: ");
        JTextField generationsInput = new JTextField("1000", 5);

        JButton run = new JButton("Run");
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int generations = Integer.parseInt(generationsInput.getText());
                    run(generations);
                } catch(Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        controlPanel.add(generationsLabel);
        controlPanel.add(generationsInput);
        controlPanel.add(run);
        controlPanel.add(reset);

        this.add(controlPanel);
    }

}
