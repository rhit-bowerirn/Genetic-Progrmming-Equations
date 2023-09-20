package presentation;

import java.util.List;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.GPTree;
import ga.sim.Genome;
import ga.viz.instantiation.GenomeCreator;
import ga.viz.instantiation.LabeledComponent;
import graphing.data.Dataset;
import graphing.data.InteractiveTable;
import graphing.data.Point;

public class GPTreeCreator extends GenomeCreator {

    private JTextField maxDepthInput;
    private JTextField sizeLimitInput;
    private InteractiveTable dataInput;
    private static final int TEXT_FIELD_LENGTH = 5;

    public GPTreeCreator() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        this.maxDepthInput = new JTextField("5", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Max Tree Starting Depth: ", this.maxDepthInput, true));

        this.sizeLimitInput = new JTextField("12", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Tree Size Limit: ", this.sizeLimitInput, true));

        this.dataInput = new InteractiveTable();
        this.add(new LabeledComponent("Data: ", this.dataInput, false));

        JButton loadExampleButton = new JButton("Load Example Data");
        loadExampleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataInput.enterDataset(new Dataset(
                        List.of(new Point(0.389, 87.77),
                                new Point(0.724, 224.70),
                                new Point(1.0, 365.25),
                                new Point(1.524, 686.95),
                                new Point(5.20, 4332.62),
                                new Point(9.510, 10759.2))));
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(loadExampleButton);
        this.add(panel);
    }

    @Override
    public Genome creatGenome(Random rand) throws Exception {

        int maxDepth;
        int sizeLimit;
        Dataset data;

        try {
            maxDepth = Integer.parseInt(this.maxDepthInput.getText());
            if (maxDepth <= 0) {
                throw new IllegalArgumentException("Max depth must be at least 1");
            }
        } catch (Exception e) {
            throw new Exception("Max Depth must be an integer");
        }

        try {
            sizeLimit = Integer.parseInt(this.sizeLimitInput.getText());
            if (sizeLimit <= 0) {
                throw new IllegalArgumentException("Size Limit must be at least 1");
            }
        } catch (Exception e) {
            throw new Exception("Size Limit must be an integer");
        }

        try {
            data = this.dataInput.toDataset();
        } catch (Exception e) {
            throw new Exception("Data must all be numbers");
        }

        return new GPTree(maxDepth, sizeLimit, data, rand);
    }

}
