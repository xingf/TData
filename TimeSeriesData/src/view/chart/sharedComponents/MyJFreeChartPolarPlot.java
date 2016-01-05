/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.axis.DateTick;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author Administrator
 */
public class MyJFreeChartPolarPlot extends PolarPlot {

//    @Override
//    protected List refreshAngleTicks() {
//        List<NumberTick> ticks = new ArrayList<NumberTick>();
////        List<DateTick> ticks = new ArrayList<DateTick>();
//        int delta = (int) this.getAngleTickUnit().getSize();
//        for (int t = 0; t < 360; t += delta) {
//            int tp = (360 + 90 - t) % 360;
//            NumberTick tick = new NumberTick(
//                    Double.valueOf(t), String.valueOf(tp),
//                    TextAnchor.CENTER, TextAnchor.CENTER, 0.0);
////            DateTick tick = new DateTick();
//            ticks.add(tick);
//        }
//        return ticks;
//    }
 
//    @Override
//    protected AxisState drawAxis(ValueAxis axis, PolarAxisLocation location, java.awt.Graphics2D g2, java.awt.geom.Rectangle2D plotArea){
//        return null;
//    }
    
    boolean pan = true;

    public boolean isPannable() {
        if (this.pan == true) {
            return true;
        } else {
            return false;
        }
    }

    public void panDomainAxes(double percent, PlotRenderingInfo info,
            Point2D source) {
        if (!isPannable()) {
            return;
        }

        int rangeAxisCount = this.getAxisCount();
        for (int i = 0; i < rangeAxisCount; i++) {
            ValueAxis axis = this.getAxis(i);
            if (axis == null) {
                continue;
            }
            if (axis.isInverted()) {
                percent = -percent;
            }
            axis.pan(percent);
        }
    }

}
