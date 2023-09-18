package ga.sim;

public interface Observer {
    void update(GeneticAlgorithm geneticAlgorithm);
    void reset(GeneticAlgorithm geneticAlgorithm);
}
