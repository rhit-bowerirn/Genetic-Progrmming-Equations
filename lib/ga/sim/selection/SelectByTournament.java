package ga.sim.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ga.sim.Genome;
import ga.sim.PopulationUtil;

public class SelectByTournament implements SelectionMethod {

    @Override
    public List<Genome> nextGeneration(List<Genome> population, Random rand) {
        List<Genome> children = new ArrayList<Genome>(population.size());

        for (int i = 0; i < population.size() / 2; i++) {
            Genome firstParent = tournament(population, rand);

            // we don't want to crossover with the same parent
            population.remove(firstParent);
            Genome secondParent = tournament(population, rand);

            // add it back for the next run
            population.add(firstParent);

            children.add(firstParent.crossover(secondParent));
            children.add(secondParent.crossover(firstParent));
        }

        return children;
    }

    private Genome tournament(List<Genome> population, Random rand) {
        // fastest way to randomly select
        Collections.shuffle(population, rand);

        // ensures we have at least 2
        int numCompetitors = (int) (2.5 + population.size() / 100); // 2.5 to manually Math.round()

        return PopulationUtil.fittestGenome(population.subList(0, numCompetitors));
    }

    @Override
    public String toString() {
        return TOURNAMENT_SELECTION;
    }

}