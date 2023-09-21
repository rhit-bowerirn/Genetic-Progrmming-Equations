package presentation;

import javax.swing.JFrame;

import ga.viz.control.Application;
import ga.viz.control.ConstantsController;
import ga.viz.control.DefaultConstController;
import ga.viz.control.DefaultVizController;
import ga.viz.control.VisualizationController;
import ga.viz.display.FitnessViz;
import ga.viz.display.Visualization;
import ga.viz.instantiation.DefaultSimCreator;

public class Main {
    public static void main(String[] args) {
        ConstantsController constController = new DefaultConstController(new DefaultSimCreator(),
                new GPTreeCreator());

        Visualization fitness = new FitnessViz();
        Visualization equation = new EquationPlotViz();
        Visualization tree = new TreeViz();
        Visualization treeSize = new TreeSizeViz();

        VisualizationController vizController = new DefaultVizController(fitness, equation, tree, treeSize);
        Application app = new Application(constController, vizController);


        JFrame frame = new JFrame("Genetic Programming Tree Simulation");
        frame.setSize(1400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.add(app);
        frame.setVisible(true);

    }
}
