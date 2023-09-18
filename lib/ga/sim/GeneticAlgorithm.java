package ga.sim;

import java.util.Random;
import java.util.function.Function;

import graphing.data.Dataset;

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
        this.survivingChildren = initialPopulation.size() - eliteCount;

        this.history = new PopulationHistory();
        this.observers = new ArrayList<Observer>();
        this.reset();
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
        this.history.reset();
        this.history.addRecord(this.initialPopulation);
        this.resetObservers();
    }

    public PopulationHistory history() {
        return this.history;
    }

    public PopulationStats latest() {
        return this.history.latest();
    }

    public PopulationStats generation(int generation) {
        return this.history.generation(generation);
    }

    public int generations() {
        return this.history.generations();
    }

    public double highestEverFitness() {
        return this.history.highestEverFitness();
    }

    public double lowestEverFitness() {
        return this.history.lowestEverFitness();
    }

    public Dataset timeSeries(Function<? super PopulationStats, ? extends Number> stat) {
        return this.history.timeSeries(stat);
    }

    public void logCSV(String filename) {
        this.history.toCSV(filename);
    }

    public void subscribe(Observer observer) {
        this.observers.add(observer);
        observer.update(this);
    }

    private void updateObservers() {
        this.observers.forEach(observer -> observer.update(this));
    }

    private void resetObservers() {
        this.observers.forEach(observer -> observer.reset(this));
    }

}
