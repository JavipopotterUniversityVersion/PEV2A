package operators.Selection;

import ga.*;
import java.util.*;

public class SelectionRank {

    public static Individual select(Population pop){
        Random rand = new Random();

        Individual best = null;

        for(int i=0;i<pop.size;i++){

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