package presentation;

import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextField;

import domain.GPTree;
import ga.sim.Genome;
import ga.viz.GenomeCreator;
import ga.viz.LabeledComponent;
import graphing.data.Dataset;

public class GPTreeCreator extends GenomeCreator {

    private JTextField maxDepthInput;
    private JTextField sizeLimitInput;
    private PointEntryTable dataInput;
    private static final int TEXT_FIELD_LENGTH = 5;

    public GPTreeCreator() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        this.maxDepthInput = new JTextField("5", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Max Tree Starting Depth: ", this.maxDepthInput, true));

        this.sizeLimitInput = new JTextField("12", TEXT_FIELD_LENGTH);
        this.add(new LabeledComponent("Tree Size Limit: ", this.sizeLimitInput, true));

        this.dataInput = new PointEntryTable();
        this.add(new LabeledComponent("Data: ", this.dataInput, true));
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
