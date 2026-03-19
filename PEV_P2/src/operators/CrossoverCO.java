package operators;

import java.util.*;

public class CrossoverCO {

    public static int[] crossover(int[] p1,int[] p2){

        Random rand = new Random();

        int size = p1.length;

        int cut = rand.nextInt(size);

        int[] child = new int[size];

        for(int i=0;i<cut;i++)
            child[i] = p1[i];

        for(int i=cut;i<size;i++)
            child[i] = p2[i];

        return child;
    }

}