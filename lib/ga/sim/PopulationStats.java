package ga.sim;

import java.util.List;

import ga.logging.Logger;

public class PopulationStats {
    private List<Genome> population;
    private int generation;


    public PopulationStats(List<Genome> population, int generation) {
        this.population = population;
        this.generation = generation;
    }

    public void log(Logger logger) throws Exception {
        logger.logPopulation(this.generation, this.maxFitness(), this.minFitness(), this.averageFitness());
    }

    public List<Genome> population() {
        return this.population;
    }

    public int generation() {
        return this.generation;
    }

    public double maxFitness() {
        return PopulationUtil.maxFitness(population);
    }

    public double minFitness() {
        return PopulationUtil.minFitness(this.population);
    }

    public double averageFitness() {
        return PopulationUtil.averageFitness(this.population);
    }

    public Genome fittestGenome() {
        return PopulationUtil.fittestGenome(this.population);
    }

}
