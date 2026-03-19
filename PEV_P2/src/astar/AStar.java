package astar;

import java.util.*;
import model.Point;

public class AStar {

    private int[][] map;

    public AStar(int[][] map){
        this.map = map;
    }

    public double findCost(Point start, Point goal){

        PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));

        boolean[][] closed = new boolean[map.length][map[0].length];

        Node startNode = new Node(start.x,start.y);

        open.add(startNode);

        while(!open.isEmpty()){

            Node current = open.poll();

            if(current.x == goal.x && current.y == goal.y){
                return current.g;
            }

            closed[current.x][current.y] = true;

            int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

            for(int[] d : dirs) {

                int nx = current.x + d[0];
                int ny = current.y + d[1];

                if(nx<0 || ny<0 || nx>=map.length || ny>=map[0].length)
                    continue;

                if(map[nx][ny] == 0) // muro
                    continue;
                
                // TODO añadir penalizacion

                if(closed[nx][ny])
                    continue;

                double cost = (double) map[nx][ny];

                Node n = new Node(nx,ny);

                n.g = current.g + cost;

                n.h = Math.abs(goal.x-nx) + Math.abs(goal.y-ny);

                n.f = n.g + n.h;

                n.parent = current;

                open.add(n);
            }
        }

        return Double.MAX_VALUE;

    }
}