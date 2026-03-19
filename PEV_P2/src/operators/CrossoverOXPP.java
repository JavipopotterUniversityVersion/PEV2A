package operators;

import java.util.Arrays;
import java.util.Random;

public class CrossoverOXPP {

    public static int[] crossover(int[] parent1, int[] parent2) {
        Random rand = new Random();
        int n = parent1.length;

        int i = rand.nextInt(n);
        int j = rand.nextInt(n);
        while (j == i) {
            j = rand.nextInt(n);
        }
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }

        int[] offspring = new int[n];
        Arrays.fill(offspring, -1);

        for (int k = i; k <= j; k++) {
            offspring[k] = parent1[k];
        }

        int pos = (j + 1) % n;
        int idx = (j + 1) % n;

        int filled = 0;
        int total = n - (j - i + 1);

        while (filled < total) {
            int gene = parent2[idx];

            boolean found = false;
            for (int k = i; k <= j; k++) {
                if (offspring[k] == gene) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                offspring[pos] = gene;
                pos = (pos + 1) % n;
                filled++;
            }

            idx = (idx + 1) % n;
        }

        return offspring;
    }
}