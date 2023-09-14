package evolution.simulation;

import java.util.List;

import evolution.logging.CSVLogger;
import evolution.logging.Logger;

import java.util.LinkedList;

public class PopulationHistory {
    private LinkedList<PopulationStats> history;
    private double highestEverFitness;
    private double lowestEverFitness;

    public PopulationHistory() {
        this.history = new LinkedList<PopulationStats>();
        this.highestEverFitness = 0;
        this.lowestEverFitness = 0;
    }

    public boolean addRecord(List<Genome> population) {
        PopulationStats stats = new PopulationStats(population, this.history.size());
        this.highestEverFitness = Math.max(stats.maxFitness(), this.highestEverFitness);
        this.lowestEverFitness = Math.min(stats.minFitness(), this.lowestEverFitness);
        return this.history.add(stats);
    }

    public PopulationStats latest() {
        return this.history.getLast();
    }

    public PopulationStats generation(int generation) {
        return this.history.get(generation);
    }

    public int generations() {
        return this.history.size() - 1;
    }

    public double highestEverFitness() {
        return this.highestEverFitness;
    }

    public double lowestEverFitness() {
        return this.lowestEverFitness;
    }

    public void toCSV(String filename) {
        Logger logger = new CSVLogger(filename);
        this.logHistory(logger);
    }

    // public void toExcel(String filename) {
    // Logger logger = new ExcelLogger(filename);
    // this.logHistory(logger);
    // }

    private void logHistory(Logger logger) {
        for (PopulationStats stats : this.history) {
            stats.log(logger);
        }
    }
}