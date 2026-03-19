package gui;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import model.Point;

public class MapPanel extends JPanel {

    public int cell = 25;

    public int[][] map;

    public List<Point> path = new ArrayList<>();

    public void setMap(int[][] map){
        this.map = map;
    }
    
    public void setPath(List<Point> p){
        this.path = p;
        repaint();
    }

    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        if(map == null) return;

        for(int i=0;i<map.length;i++)
            for(int j=0;j<map[0].length;j++){

                switch(map[i][j]){

                    case 1: g.setColor(Color.WHITE); break;
                    case 5: g.setColor(Color.YELLOW); break;
                    case 15: g.setColor(Color.PINK); break;
                    case 20: g.setColor(Color.RED); break;
                    default: g.setColor(Color.BLACK);

                }

                g.fillRect(
                    j*cell,
                    i*cell,
                    cell,
                    cell);

                g.setColor(Color.GRAY);

                g.drawRect(
                    j*cell,
                    i*cell,
                    cell,
                    cell);

            }

        g.setColor(Color.BLUE);

        for(Point p : path){

            g.fillOval(
                p.y*cell,
                p.x*cell,
                10,
                10);

        }

    }

}