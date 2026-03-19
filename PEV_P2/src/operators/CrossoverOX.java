package operators;

import java.util.Arrays;
import java.util.Random;

public class CrossoverOX {

    public static int[] crossover(int[] parent1, int[] parent2){

        Random rand = new Random();

        int size = parent1.length;

        int cut1 = rand.nextInt(size);
        int cut2 = rand.nextInt(size);

        if(cut1 > cut2){
            int tmp = cut1;
            cut1 = cut2;
            cut2 = tmp;
        }

        int[] child = new int[size];
        Arrays.fill(child, -1);

        for(int i = cut1; i <= cut2; i++){
            child[i] = parent1[i];
        }

        int indexChild = (cut2 + 1) % size;

        for(int i = 0; i < size; i++){

            int indexParent = (cut2 + 1 + i) % size;
            int gene = parent2[indexParent];

            if(!contains(child, gene)){

                child[indexChild] = gene;

                indexChild = (indexChild + 1) % size;
            }
        }

        return child;
    }

    private static boolean contains(int[] arr, int val){

        for(int v : arr)
            if(v == val)
                return true;

        return false;
    }
}