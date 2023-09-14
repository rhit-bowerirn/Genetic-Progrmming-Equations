package evolution.simulation;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm {
    private Random rand;
    private SelectionMethod selectionMethod;
    private int eliteCount;
    private double mutationRate;

    private List<Genome> population;
    private List<Genome> initialPopulation;
    private int survivingChildren;
    private PopulationHistory history;
    private List<Observer> observers;

    public GeneticAlgorithm(List<Genome> initialPopulation, SelectionMethod selectionMethod, double mutationRate,
            int eliteCount, Random rand) {
        this.initialPopulation = initialPopulation;
        this.rand = rand;
        this.eliteCount = eliteCount;
        this.mutationRate = mutationRate;
        this.selectionMethod = selectionMethod;
        this.survivingChildren = initialPopulation.size() - eliteCount; // make it even to avoid out of bounds
        this.observers = new ArrayList<Observer>();
        this.reset();
    }

    public List<Genome> population() {
        return this.population;
    }

    public PopulationHistory history() {
        return this.history;
    }

    public boolean subscribe(Observer observer) {
        return this.observers.add(observer);
    }

    public void nextGeneration() {
        List<Genome> nextGeneration = new ArrayList<Genome>(this.population.size());
        PopulationUtil.partialSort(this.population, this.eliteCount, this.rand);
        nextGeneration.addAll(this.population.subList(0, this.eliteCount));

        List<Genome> children = this.selectionMethod.nextGeneration(this.population, this.rand);
        PopulationUtil.mutateAll(children, this.mutationRate);
        PopulationUtil.partialSort(children, this.survivingChildren, this.rand);

        nextGeneration.addAll(children.subList(0, this.survivingChildren));
        this.history.addRecord(nextGeneration);
        this.population = nextGeneration;

        this.updateObservers();
    }

    public void run(int generations) {
        for (int i = 0; i < generations; i++) {
            this.nextGeneration();
        }
    }

    public void reset() {
        this.population = this.initialPopulation;
        this.history = new PopulationHistory();
        this.history.addRecord(this.initialPopulation);
    }

    public void logCSV(String filename) {
        this.history.toCSV(filename);
    }

    private void updateObservers() {
        this.observers.forEach(observer -> observer.update());
    }
    

}
