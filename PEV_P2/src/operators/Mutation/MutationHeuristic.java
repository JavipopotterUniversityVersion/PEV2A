package operators.Mutation;

import java.util.*;

public class MutationHeuristic {

    public static void mutate(int[] chrom, double[][] distMatrix) {
        Random rand = new Random();

        int pos = rand.nextInt(chrom.length);
        int city = chrom[pos];

        int n = chrom.length;
        int[] temp = new int[n - 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (i != pos) temp[idx++] = chrom[i];
        }

        double bestCost = Double.MAX_VALUE;
        int bestPos = 0;

        for (int i = 0; i <= temp.length; i++) {
            int prev = temp[(i - 1 + temp.length) % temp.length];
            int next = temp[i % temp.length];

            double cost = distMatrix[prev][city] + distMatrix[city][next]
                        - distMatrix[prev][next];

            if (cost < bestCost) {
                bestCost = cost;
                bestPos = i;
            }
        }

        idx = 0;
        for (int i = 0; i < n; i++) {
            if (i == bestPos) {
                chrom[i] = city;
            } else {
                chrom[i] = temp[idx++];
            }
        }
    }
}