package presentation;

import javax.swing.JFrame;

import ga.viz.Application;
import ga.viz.ConstantsController;
import ga.viz.DefaultConstController;
import ga.viz.DefaultSimCreator;
import ga.viz.DefaultVizController;
import ga.viz.FitnessViz;
import ga.viz.Visualization;
import ga.viz.VisualizationController;

public class Main {
    public static void main(String[] args) {
        ConstantsController constController = new DefaultConstController(new DefaultSimCreator(),
                new GPTreeCreator());

        Visualization fitness = new FitnessViz();
        Visualization equation = new EquationPlotVisualization();
        Visualization tree = new TreeVisualization();

        VisualizationController vizController = new DefaultVizController(fitness, equation, tree);
        Application app = new Application(constController, vizController);


        JFrame frame = new JFrame("Genetic Programming Tree Simulation");
        frame.setSize(1400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(app);
        frame.setVisible(true);

    }
}
