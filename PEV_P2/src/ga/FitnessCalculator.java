package ga;

import java.util.*;

import astar.AStar;
import model.*;

public class FitnessCalculator {

    private List<Drone> drones;
    private List<Camera> cameras;
    private Point base;
    private AStar astar;

    public FitnessCalculator(List<Drone> drones, List<Camera> cameras, Point base, AStar astar){

        this.drones = drones;
        this.cameras = cameras;
        this.base = base;
        this.astar = astar;

    }

    public double evaluate(Individual ind){

        int droneIndex = 0;

        double[] times = new double[drones.size()];

        Point current = base;

        for(int gene : ind.chromosome){

            if(gene > cameras.size()){

                times[droneIndex] += astar.findCost(current, base);
                times[droneIndex] /= drones.get(droneIndex).speed;

                droneIndex++;
                current = base;

                continue;
            }

            Camera cam = cameras.get(gene-1);

            double cost = astar.findCost(current, cam.position);

            times[droneIndex] += cost;

            current = cam.position;

        }

        times[droneIndex] += astar.findCost(current, base);
        times[droneIndex] /= drones.get(droneIndex).speed;

        double max = Arrays.stream(times).max().getAsDouble();
        double min = Arrays.stream(times).min().getAsDouble();

        return max + (max-min)*0.5;

    }

}