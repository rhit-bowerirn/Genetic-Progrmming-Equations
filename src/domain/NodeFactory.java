package domain;

import java.util.Random;


public class NodeFactory {
    private static double FUNCTION_RATE = 0.5;

    public static Node randomTerminalNode(Random rand) {
        return new TerminalNode(rand);
    }

    public static Node randomFunctionalNode(int height, Random rand) {
        return new FunctionalNode(height, rand);
    }

    public static Node randomNode(int height, Random rand) {
        if(rand.nextDouble() < FUNCTION_RATE) {
            return randomFunctionalNode(height, rand);
        } else {
            return randomTerminalNode(rand);
        }
    }
    
}
