package presentation;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import domain.GPTree;
import evolution.simulation.GeneticAlgorithm;
import evolution.simulation.Genome;
import evolution.simulation.SelectByRank;
import evolution.visualization.DefaultVizController;
import evolution.visualization.FitnessVisualization;
import evolution.visualization.Visualization;
import evolution.visualization.VisualizationController;
import graphing.Dataset;
import graphing.Point;

public class Main {
    public static void main(String[] args) {
        Dataset KEPLER_DATA = new Dataset(List.of(
                new Point(0.389, 87.77),
                new Point(0.724, 224.70),
                new Point(1.0, 365.25),
                new Point(1.524, 686.95),
                new Point(5.20, 4332.62),
                new Point(9.510, 10759.2)));

        Random rand = new Random(7);
        // Assuming you have a GeneticAlgorithm object called 'geneticAlgorithm'
        JFrame frame = new JFrame("Population Fitness Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 600);

        List<Genome> population = new ArrayList<Genome>();
        for (int i = 0; i < 1000; i++) {
            population.add(new GPTree(5, 12, KEPLER_DATA, rand));
        }
        GeneticAlgorithm ga = new GeneticAlgorithm(population, new SelectByRank(), 0.09, 10, rand);

        Visualization fitnessView = new FitnessVisualization(ga);
        Visualization equation = new EquationPlotVisualization(ga);
        Visualization tree = new TreeVisualization(ga);

        //VisualizationSelector vizChooser = new VisualizationSelector(List.of(fitnessView, equation));
        VisualizationController vizController = new DefaultVizController(fitnessView, equation, tree);
        //SimulationConstantsMenu menu = new SimulationConstantsMenu();
        frame.add(vizController);
        frame.setVisible(true);
        
        ga.run(2000);
        System.out.println(ga.history().latest().fittestGenome().toString());

    }
}
