package operators.Mutation;

import java.util.*;

public class MutationInversion {

    public static void mutate(int[] chrom) {
        Random rand = new Random();

        // Select two distinct random positions
        int i = rand.nextInt(chrom.length);
        int j = rand.nextInt(chrom.length);
        while (j == i) {
            j = rand.nextInt(chrom.length);
        }

        // Ensure i < j
        if (i > j) {
            int temp = i;
            i = j;
            j = temp;
        }

        // Reverse the subarray between i and j (inclusive)
        while (i < j) {
            int temp = chrom[i];
            chrom[i] = chrom[j];
            chrom[j] = temp;
            i++;
            j--;
        }
    }

}

// 1. Se seleccionan dos posiciones aleatorias `i` y `j` distintas.
// 2. Se ordenan para garantizar que `i < j`.
// 3. Se invierte el subsegmento del cromosoma comprendido entre `i` y `j` (inclusive).