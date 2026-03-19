package operators;

import java.util.*;

public class CrossoverERX {

    public static int[] crossover(int[] p1, int[] p2){

        int size = p1.length;

        Map<Integer, Set<Integer>> map = buildMap(p1, p2);

        Random rand = new Random();

        int[] child = new int[size];

        int current = p1[0];

        for(int i = 0; i < size; i++){

            child[i] = current;

            removeFromMap(map, current);

            Set<Integer> neighbors = map.get(current);

            if(neighbors != null && !neighbors.isEmpty()){

                current = selectNeighborWithFewestEdges(map, neighbors);

            } else {

                current = randomUnused(child, p1, rand);

            }
        }

        return child;
    }

    // Construir mapa de aristas
    private static Map<Integer, Set<Integer>> buildMap(int[] p1, int[] p2){

        Map<Integer, Set<Integer>> map = new HashMap<>();

        buildEdges(map, p1);
        buildEdges(map, p2);

        return map;
    }

    private static void buildEdges(Map<Integer, Set<Integer>> map, int[] parent){

        int size = parent.length;

        for(int i = 0; i < size; i++){

            int gene = parent[i];

            map.putIfAbsent(gene, new HashSet<>());

            int left = parent[(i - 1 + size) % size];
            int right = parent[(i + 1) % size];

            map.get(gene).add(left);
            map.get(gene).add(right);
        }
    }

    // Eliminar referencias a un nodo ya usado
    private static void removeFromMap(Map<Integer, Set<Integer>> map, int value){

        for(Set<Integer> neighbors : map.values()){
            neighbors.remove(value);
        }
    }

    // Elegir vecino con menor número de conexiones
    private static int selectNeighborWithFewestEdges(
            Map<Integer, Set<Integer>> map,
            Set<Integer> neighbors){

        int best = -1;
        int minEdges = Integer.MAX_VALUE;

        for(int n : neighbors){

            int edges = map.get(n).size();

            if(edges < minEdges){

                minEdges = edges;
                best = n;
            }
        }

        return best;
    }

    // Elegir gen aleatorio no usado
    private static int randomUnused(int[] child, int[] parent, Random rand){

        List<Integer> unused = new ArrayList<>();

        for(int gene : parent){

            if(!contains(child, gene))
                unused.add(gene);
        }

        return unused.get(rand.nextInt(unused.size()));
    }

    private static boolean contains(int[] arr, int val){

        for(int v : arr)
            if(v == val)
                return true;

        return false;
    }
}