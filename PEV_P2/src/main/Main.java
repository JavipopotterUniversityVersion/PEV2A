package main;

import astar.AStar;
import ga.*;
import gui.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import map.*;
import model.*;

public class Main {
	
	final static float[] DRONE_SPEEDS = {1.5f, 1.0f, 0.7f, 1.2f, 0.5f};

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> {

            MainWindow window = new MainWindow();

            ControlPanel controls = window.getControlPanel();
            controls.getRunButton().addActionListener(e -> {

                new Thread(() -> {
                	
                	// TODO: Escenario por panel de control
                	int[][] map;
					try {
						map = MapLoader.load("maps/escenario_3.txt");
					} catch (Exception e1) {
						e1.printStackTrace();
						return;
					}

                	window.getMapPanel().setMap(map);

                	// TODO: añadir elitismo
                    GeneticAlgorithm ga = new GeneticAlgorithm(
                    		controls.getPopulation(), 
                    		controls.getGenerations(),
                    		controls.getCrossoverProb(),
                    		controls.getMutationProb(),
							window,
                    		controls.getCrossoverType(),
							controls.getMutationType(),
							controls.getSelectionType());
                    
                	
                	// Generación de drones
                	List<Drone> drones = new ArrayList<>();
                	
                	for(int i=0;i<controls.getDrones();i++){
                	    drones.add(new Drone(i,DRONE_SPEEDS[i]));
                	}
                	
                	
                	// Generación de cámaras
                	CameraGenerator cameraGen = new CameraGenerator();
                	List<Camera> cameras = cameraGen.generate(controls.getCameras(), map, 3000);

                    FitnessCalculator fitnessCalc = new FitnessCalculator(drones, cameras, new Point(1,2), new AStar(map));
                    
                    int chromosomeSize = drones.size() + cameras.size() - 1;
                    Population population = new Population(controls.getPopulation(), chromosomeSize);

                    ga.run(population, fitnessCalc);

                }).start();

            });

        });

    }
}