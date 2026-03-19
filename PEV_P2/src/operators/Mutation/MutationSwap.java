package operators.Mutation;

import java.util.*;

public class MutationSwap {

    public static void mutate(int[] chrom) {
        Random rand = new Random();

        // Select two distinct random positions
        int i = rand.nextInt(chrom.length);
        int j = rand.nextInt(chrom.length);
        while (j == i) {
            j = rand.nextInt(chrom.length);
        }

        // Swap the genes at positions i and j
        int temp = chrom[i];
        chrom[i] = chrom[j];
        chrom[j] = temp;
    }

}

// 1. Se seleccionan dos posiciones aleatorias `i` y `j` distintas.
// 2. Se intercambian los genes en dichas posiciones.
