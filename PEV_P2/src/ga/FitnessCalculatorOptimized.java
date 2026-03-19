package ga;

import java.util.*;
import model.*;
import astar.DistanceMatrix;

public class FitnessCalculatorOptimized {

    private List<Drone> drones;
    private DistanceMatrix dist;
    private int cameras;

    public FitnessCalculatorOptimized(
        List<Drone> drones,
        DistanceMatrix dist,
        int cameras){

        this.drones = drones;
        this.dist = dist;
        this.cameras = cameras;
    }

    public double evaluate(Individual ind){

        double[] times =
            new double[drones.size()];

        int droneIndex = 0;

        int current = 0; // base

        for(int gene : ind.chromosome){

            if(gene > cameras){

                times[droneIndex] +=
                    dist.matrix[current][0] /
                    drones.get(droneIndex).speed;

                droneIndex++;
                current = 0;
                continue;
            }

            times[droneIndex] +=
                dist.matrix[current][gene] /
                drones.get(droneIndex).speed;

            current = gene;

        }

        times[droneIndex] +=
            dist.matrix[current][0] /
            drones.get(droneIndex).speed;

        double max = Arrays.stream(times).max().getAsDouble();
        double min = Arrays.stream(times).min().getAsDouble();

        return max + (max-min)*0.5;
    }
}