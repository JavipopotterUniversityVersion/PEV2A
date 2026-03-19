package ga;

import java.util.Random;

public class Individual {

    public int[] chromosome;
    public double fitness;

    public Individual(int size) {

        chromosome = new int[size];

    }
    
    public void randomize(Random rand) {

        int n = chromosome.length;

        for(int i=0;i<n;++i)
            chromosome[i] = i+1;

        // mezclar genes de manera uniforme
        for(int i=n-1;i>0;i--) {
            int j = rand.nextInt(i+1);

            int tmp = chromosome[i];
            chromosome[i] = chromosome[j];
            chromosome[j] = tmp;
        }
    }

    public Individual copy() {

        Individual i = new Individual(chromosome.length);

        System.arraycopy(chromosome,0,i.chromosome,0,chromosome.length);

        i.fitness = fitness;

        return i;
    }

}