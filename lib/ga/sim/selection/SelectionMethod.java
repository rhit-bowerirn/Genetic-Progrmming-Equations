package ga.sim.selection;

import java.util.List;
import java.util.Random;

import ga.sim.alg.Genome;

public interface SelectionMethod {
    static final String PROPORTIONAL_SELECTION = "Proportional Selection";
    static final String RANKED_SELECTION = "Ranked Selection";
    static final String ROULETTE_SELECTION = "Roulette Selection";
    static final String TOURNAMENT_SELECTION = "Tournament Selection";
    static final String TRUNCATION_SELECTION = "Truncation Selection";

    List<Genome> nextGeneration(List<Genome> population, Random rand);
    String toString();
}
