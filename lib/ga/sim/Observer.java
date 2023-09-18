package ga.sim;

public interface Observer {
    void update(GeneticAlgorithm ga);
    void reset(GeneticAlgorithm ga);
}
