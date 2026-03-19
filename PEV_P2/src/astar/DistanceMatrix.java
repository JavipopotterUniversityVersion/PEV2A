package astar;

import java.util.*;
import model.*;

public class DistanceMatrix {

    public double[][] matrix;

    public DistanceMatrix(
        List<Camera> cameras,
        Point base,
        AStar astar){

        int n = cameras.size() + 1;

        matrix = new double[n][n];

        List<Point> nodes = new ArrayList<>();

        nodes.add(base);

        for(Camera c : cameras)
            nodes.add(c.position);

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){

                if(i==j) continue;

                matrix[i][j] =
                    astar.findCost(
                        nodes.get(i),
                        nodes.get(j));

            }

    }

}