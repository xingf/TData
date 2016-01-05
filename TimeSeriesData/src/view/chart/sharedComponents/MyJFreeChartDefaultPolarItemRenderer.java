/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Administrator
 */
public class MyJFreeChartDefaultPolarItemRenderer extends DefaultPolarItemRenderer {

    
//    @Override
//    public void drawSeries(java.awt.Graphics2D g2, java.awt.geom.Rectangle2D dataArea, PlotRenderingInfo info, PolarPlot plot, XYDataset dataset, int seriesIndex) {
//
//        System.out.println("drawSeries");
//        int numPoints = dataset.getItemCount(seriesIndex);
//        System.out.println("numPoints: " + numPoints);
//        for (int i = 0; i < numPoints; i++) {
//
//            
//            double radius = dataset.getXValue(seriesIndex, i);
//            double theta = dataset.getYValue(seriesIndex, i);
//            System.out.println(i + ": theta- " + theta + ", radius-" + radius);
//            Point p = plot.translateToJava2D(theta, radius, plot.getAxis(),
//                    dataArea);
//            
//     
//            //Rectangle2D el = new Rectangle2D.Float(p.x,p.y, 5, 5);
//            
//            Ellipse2D el = new Ellipse2D.Double(p.x, p.y, 5, 5);
//            //g2.rotate(theta);
//           
//            g2.fill(el);
//            g2.draw(el);
//            g2.setPaint(Color.red);
//            
//        }
//    }
//    
//    
//     /**
//     * Draw the radial gridlines - the rings.
//     *
//     * @param g2  the drawing surface (<code>null</code> not permitted).
//     * @param plot  the plot (<code>null</code> not permitted).
//     * @param radialAxis  the radial axis (<code>null</code> not permitted).
//     * @param ticks  the ticks (<code>null</code> not permitted).
//     * @param dataArea  the data area.
//     */
//    @Override
//    public void drawRadialGridLines(Graphics2D g2, PolarPlot plot, 
//            ValueAxis radialAxis, List ticks, Rectangle2D dataArea) {
//
//        ParamChecks.nullNotPermitted(radialAxis, "radialAxis");
//        g2.setFont(radialAxis.getTickLabelFont());
//        g2.setPaint(plot.getRadiusGridlinePaint());
//        g2.setStroke(plot.getRadiusGridlineStroke());
//
//        double centerValue;
//        if (radialAxis.isInverted()) {
//            centerValue = radialAxis.getUpperBound();
//        } else {
//            centerValue = radialAxis.getLowerBound();
//        }
//        Point center = plot.translateToJava2D(0, centerValue, radialAxis, dataArea);
//
//        Iterator iterator = ticks.iterator();
//        while (iterator.hasNext()) {
//            ValueTick tick = (ValueTick) iterator.next();
//            double angleDegrees = plot.isCounterClockwise() 
//                    ? plot.getAngleOffset() : -plot.getAngleOffset();
//            Point p = plot.translateToJava2D(angleDegrees,
//                    tick.getValue(), radialAxis, dataArea);
//            int r = p.x - center.x;
//            int upperLeftX = center.x - r;
//            int upperLeftY = center.y - r;
//            int d = 2 * r;
//            Ellipse2D ring = new Ellipse2D.Double(upperLeftX, upperLeftY, d, d);
//            g2.setPaint(plot.getRadiusGridlinePaint());
//            g2.draw(ring);
//        }
//    }

}
