package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    MapPanel mapPanel;
    PlotPanel plotPanel;
    ControlPanel controlPanel;

    public MainWindow(){

        setTitle("mTSP Drones");

        setSize(1000,700);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        mapPanel = new MapPanel();
        plotPanel = new PlotPanel();
        controlPanel = new ControlPanel();

        
        setLayout(new BorderLayout());
        
        add(controlPanel,BorderLayout.WEST);
        add(mapPanel,BorderLayout.CENTER);
        add(plotPanel,BorderLayout.EAST);

        setVisible(true);

    }

    public MapPanel getMapPanel(){
        return mapPanel;
    }

    public PlotPanel getPlotPanel(){
        return plotPanel;
    }

    public ControlPanel getControlPanel(){
        return controlPanel;
    }

}