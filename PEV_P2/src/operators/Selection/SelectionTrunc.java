package operators.Selection;

import ga.*;
import java.util.*;

public class SelectionTrunc {

    public static Individual select(Population pop, float k){
        Individual[] sorted = pop.individuals.clone();

        Arrays.sort(sorted, Comparator.comparingDouble(ind -> ind.fitness));

        return sorted[
            (int)(Math.random() * k)
        ];
    }
}