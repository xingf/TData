/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.embed.swing.SwingNode;
import view.chart.timeSeriesDataChart.TimeSeriesDataChart;
import view.chart.xyLineChart.XYLineChart;



/**
 *
 * @author Administrator
 */
public class ChartFactory {
    public static AbstractChart createXYLineChart(){
        AbstractChart view = new XYLineChart();
        return view;
    }
    public static SwingNode createCyclicChart(){
        return null;
    }
    public static AbstractChart createBasicView(){
        System.out.println("ViewFactory -- createBasicView");
        AbstractChart view = new TimeSeriesDataChart();
        return view;
    }
    
    public static AbstractChart creatLineView(){
        AbstractChart view = new XYLineChart();
        return view;
    }
}
