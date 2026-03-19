package gui;

import java.awt.Dimension;

import javax.swing.*;
import org.math.plot.Plot2DPanel;

public class PlotPanel extends JPanel {

    Plot2DPanel plot;

    public PlotPanel(){

        plot = new Plot2DPanel();

        setLayout(new java.awt.BorderLayout());
        setPreferredSize(new Dimension(750, 600));

        add(plot);

    }

    public void plotFitness(
    		double[] bestOfAllHistory, 
    		double[] bestOfGenHistory, 
    		double[] averageHistory
    		){
    	
//    	for(double v : best){
//    	    if(Double.isNaN(v) || Double.isInfinite(v))
//    	        return;
//    	}
    	
        plot.removeAllPlots();

        // crear eje X (0,1,2,...)
        double[] x = new double[bestOfAllHistory.length];
        for(int i=0;i<bestOfAllHistory.length;i++)
            x[i] = i;

        plot.addLinePlot("Mejor absoluto", x, bestOfAllHistory);
        plot.addLinePlot("Mejor de la generación", x, bestOfGenHistory);
        plot.addLinePlot("Media de la generación", x, averageHistory);

//        plot.revalidate();
        plot.repaint();
    }

}