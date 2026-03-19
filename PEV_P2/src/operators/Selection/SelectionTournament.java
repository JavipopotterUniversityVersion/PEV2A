package operators.Selection;

import ga.*;
import java.util.*;

public class SelectionTournament {

    public static Individual select(Population pop, int k){

        Random rand = new Random();

        Individual best = null;

        for(int i=0;i<k;i++){

            Individual ind =
                pop.individuals[
                    rand.nextInt(pop.size)
                ];

            if(best == null ||
               ind.fitness < best.fitness)
                best = ind;

        }

        return best;
    }
}