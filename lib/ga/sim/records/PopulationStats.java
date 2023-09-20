package ga.sim.records;

import java.util.List;

import fileIO.Logger;
import ga.sim.Genome;
import ga.sim.PopulationUtil;

public class PopulationStats {
    private List<Genome> population;
    private int generation;

    public PopulationStats(List<Genome> population, int generation) {
        this.population = population;
        this.generation = generation;
    }

    public void log(Logger logger) throws Exception {
        logger.log(new String[] {
                Integer.toString(this.generation),
                Double.toString(this.maxFitness()),
                Double.toString(this.minFitness()),
                Double.toString(this.averageFitness()),
                this.fittestGenome().toString()
        });
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
