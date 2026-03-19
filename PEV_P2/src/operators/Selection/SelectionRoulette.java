package operators.Selection;

import ga.*;
import java.util.*;

public class SelectionRoulette {

    public static Individual select(Population pop){
        double totalFitness = 0;

        for(int i=0;i<pop.size;i++)
            totalFitness += 1.0 / pop.individuals[i].fitness;

        double r = Math.random() * totalFitness;

        double sum = 0;

        for(int i=0;i<pop.size;i++){
            sum += 1.0 / pop.individuals[i].fitness;
            if(sum >= r)
                return pop.individuals[i];
        }
        
        return pop.individuals[pop.individuals.length - 1];
    }
}