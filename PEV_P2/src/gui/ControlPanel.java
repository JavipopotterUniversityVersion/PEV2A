package gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    // Escenario
    JComboBox<String> scenarioBox;

    // Semilla
    JSpinner seedSpinner;

    // Número de drones
    JSpinner droneSpinner;
    
    // Número de cámaras
    JSpinner cameraSpinner;

    // Parámetros AG
    JSpinner populationSpinner;
    JSpinner generationSpinner;

    // Probabilidades
    JSpinner crossoverSpinner;
    JSpinner mutationSpinner;

    // Operadores
    JComboBox<String> crossoverBox;
    JComboBox<String> mutationBox;
    JComboBox<String> selectionBox;
    
    // Elitismo
    JSpinner elitismSpinner;

    // Botón
    JButton runButton;

    public ControlPanel(){

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setPreferredSize(new Dimension(250, 600));

        //Escenario
        scenarioBox = new JComboBox<>(new String[]{"Museo","Pasillos","Supermercado"});
        add(row("Mapa", scenarioBox));

        // Seed
        seedSpinner = new JSpinner(new SpinnerNumberModel(1,0,Integer.MAX_VALUE,1));
        add(row("Semilla", seedSpinner));

        // Drones
        droneSpinner = new JSpinner(new SpinnerNumberModel(3,1,5,1));
        add(row("Drones", droneSpinner));
        
        // Cámaras
        cameraSpinner = new JSpinner(new SpinnerNumberModel(5, 1, Integer.MAX_VALUE, 1));
        add(row("Cámaras", cameraSpinner));

        // Poblacion
        populationSpinner = new JSpinner(new SpinnerNumberModel(100,10,1000,10));
        add(row("Población", populationSpinner));


        // Generaciones
        generationSpinner = new JSpinner(new SpinnerNumberModel(200,10,5000,50));
        add(row("Generaciones", generationSpinner));

        // Prob cruce
        crossoverSpinner = new JSpinner(new SpinnerNumberModel(0.8,0.0,1.0,0.05));
        add(row("Prob Cruce", crossoverSpinner));

        // Prob mutacion
        mutationSpinner = new JSpinner(new SpinnerNumberModel(0.2,0.0,1.0,0.05));
        add(row("Prob Mutación", mutationSpinner));

        // Tipo cruce
        crossoverBox = new JComboBox<>(new String[]{"PMX", "OX", "OXPP","CX","CO","ERX"});
        add(row("Cruce", crossoverBox));

        // Tipo mutacion
        mutationBox = new JComboBox<>(new String[]{"Inserción","Intercambio","Inversión", "Heurística"});
        add(row("Mutación", mutationBox));

        // Tipo seleccion
        selectionBox = new JComboBox<>(new String[]{"Torneo","Ruleta", "Estocástico", "Restos", "Truncamiento"});
        add(row("Selección", selectionBox));
        
        // Elitismo
        elitismSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 1.0, 0.01));
        add(row("Elitismo", elitismSpinner));


        runButton = new JButton("Ejecutar");

        add(Box.createVerticalStrut(10));
        add(runButton);
    }

    // ---------- GETTERS -------------

    public JButton getRunButton(){
        return runButton;
    }

    public String getScenario(){
        return (String)scenarioBox.getSelectedItem();
    }

    public long getSeed() {
        return ((Number)seedSpinner.getValue()).longValue();
    }

    public int getDrones() {
        return (int)droneSpinner.getValue();
    }
    
    public int getCameras() {
    	return (int)cameraSpinner.getValue();
    }

    public int getPopulation() {
        return (int)populationSpinner.getValue();
    }

    public int getGenerations() {
        return (int)generationSpinner.getValue();
    }

    public double getCrossoverProb() {
        return ((Number)crossoverSpinner.getValue()).doubleValue();
    }

    public double getMutationProb() {
        return ((Number)mutationSpinner.getValue()).doubleValue();
    }

    public String getCrossoverType() {
        return (String)crossoverBox.getSelectedItem();
    }

    public String getMutationType() {
        return (String)mutationBox.getSelectedItem();
    }

    public String getSelectionType() {
        return (String)selectionBox.getSelectedItem();
    }
    
    public double getElitismRate() {
    	return ((Number)elitismSpinner.getValue()).doubleValue();
    }
    
    
    // --------- METODOS AUX -----------
    
    private JPanel row(String label, JComponent comp){

        JPanel p = new JPanel(new BorderLayout());
        comp.setMaximumSize((new Dimension(100, comp.getPreferredSize().height)));
        p.add(new JLabel(label), BorderLayout.WEST);
        p.add(comp, BorderLayout.CENTER);

        return p;
    }

}