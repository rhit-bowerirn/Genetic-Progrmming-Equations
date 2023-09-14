package evolution.simulation;

import java.util.List;
import java.util.Collections;
import java.util.Random;

public class PopulationUtil {

    public static void mutateAll(List<Genome> population, double mutationRate) {
        population.stream().forEach(genome -> genome.mutate(mutationRate));
    }

    public static Genome fittestGenome(List<Genome> population) {
        return Collections.max(population);
    }

    public static int fittestGenomeIndex(List<Genome> population) {
        return population.indexOf(Collections.max(population));
    }

    public static double totalFitness(List<Genome> population) {
        return population.stream().mapToDouble(Genome::fitness).sum();
    }

    public static double maxFitness(List<Genome> population) {
        return Collections.max(population).fitness();
    }

    public static double minFitness(List<Genome> population) {
        return Collections.min(population).fitness();
    }

    public static double averageFitness(List<Genome> population) {
        return population.stream().mapToDouble(Genome::fitness).average().orElse(0.0);
    }

    public static void partialSort(List<Genome> population, int numToSelect, Random rand) {
        if (numToSelect <= 0) {
            return;
        }
        
        // Ensures linear time for trivial case
        if (numToSelect == 1) {
            Collections.swap(population, PopulationUtil.fittestGenomeIndex(population), 0);
            return;
        }

        int left = 0;
        int right = population.size() - 1;

        // condition won't ever be met but is there for safety
        while (left != right) {

            // move a random pivot to the right of the array
            int pivotIndex = left + rand.nextInt(right - left + 1);
            Genome pivot = population.get(pivotIndex);
            Collections.swap(population, pivotIndex, right);

            //move all individuals fitter than the pivot to the front
            int selectionIndex = left;
            for (int i = selectionIndex; i < right; i++) {
                if (population.get(i).compareTo(pivot) >= 0) {
                    Collections.swap(population, selectionIndex++, i);
                }
            }
            Collections.swap(population, selectionIndex, right); // put the pivot in its sorted position

            // iterate on the less fit partition if we need to select more
            if (selectionIndex + 1 < numToSelect) {
                left = selectionIndex + 1;
            }
            // iterate on the fitter partition if we selected too many
            else if (selectionIndex > numToSelect) {
                right = selectionIndex - 1;
            }
            // stop if the fitter partition is the right size
            else {
                return;
            }
        }
    }

}
