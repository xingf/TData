/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Administrator
 */
public class MyJFreeChartXYPlot extends XYPlot{
    public MyJFreeChartXYPlot(XYDataset dataset, ValueAxis domainAxis, ValueAxis rangeAxis,
            XYItemRenderer renderer) {
        super( dataset,  domainAxis,  rangeAxis,
             renderer);  
    }
}
