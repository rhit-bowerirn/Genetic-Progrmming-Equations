package ga.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectByProportion implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        // sort the genomes so we can use their rank
        population.sort(Genome::compareTo);
        List<Genome> children = new ArrayList<Genome>();

        // so we can index based on fitness
        double totalFitness = PopulationUtil.totalFitness(population);

        //we need to shift it so 0 is the min fitness
        double minFitness = Math.abs(PopulationUtil.minFitness(population));
        totalFitness += (minFitness * (double) population.size());

        // assume we get the full population
        for (int i = 0; i < population.size() / 2; i++) {
            // choose a random index based on the fitnesses
            double firstIndex = rand.nextDouble(totalFitness);

            // stochastic universal sampling for second index
            double secondIndex = firstIndex + (totalFitness / 2);
            if (secondIndex > totalFitness) {
                secondIndex -= totalFitness; // wrap to the beginning
            }
            
            Genome firstParent = null;
            Genome secondParent = null;

            for(Genome genome : population) {
                firstIndex -= (genome.fitness() + minFitness);
                secondIndex -= (genome.fitness() + minFitness);

                if(firstIndex <= 0 && firstParent == null) {
                    firstParent = genome;
                    if(firstIndex > secondIndex) {
                        break;
                    }
                }

                if(secondIndex <= 0 && secondParent == null) {
                    secondParent = genome;
                    if(secondIndex > firstIndex) {
                        break;
                    }
                }
            }

            children.add(firstParent.crossover(secondParent));
            children.add(secondParent.crossover(firstParent));
        }

        return children;
    }

    @Override
    public String toString() {
        return PROPORTIONAL_SELECTION;
    }

}
