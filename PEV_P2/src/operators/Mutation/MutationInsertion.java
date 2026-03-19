package operators.Mutation;

import java.util.*;

public class MutationInsertion {

    public static void mutate(int[] chrom) {
        Random rand = new Random();

        int i = rand.nextInt(chrom.length);
        int j = rand.nextInt(chrom.length);
        while (j == i) {
            j = rand.nextInt(chrom.length);
        }

        int gene = chrom[i];

        if (i < j) {
            for (int k = i; k < j; k++) {
                chrom[k] = chrom[k + 1];
            }
        } else {
            for (int k = i; k > j + 1; k--) {
                chrom[k] = chrom[k - 1];
            }
        }

        chrom[j < i ? j + 1 : j] = gene;
    }

}

// 1. Se seleccionan dos posiciones aleatorias `i` y `j` distintas.
// 2. El gen en la posición `i` se extrae y se guarda.
// 3. Los elementos entre `i` y `j` se desplazan para cerrar el hueco.
// 4. El gen extraído se inserta justo después de la posición `j`.