/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.LogTick;
import org.jfree.chart.axis.TickType;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.axis.ValueTick;
import org.jfree.chart.util.AttrStringUtils;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleEdge;
import model.DataModel;
import model.timeProperty.TimeProperty;
import model.timeProperty.TimeScope;

/**
 *
 * @author Administrator
 */
public abstract class MyJFreeChartValueAxis extends ValueAxis{

    protected MyJFreeChartValueAxis(String label, TickUnitSource standardTickUnits){
        super(label, standardTickUnits);
        yPosMap = new LinkedHashMap();  //xing
        xPosMap = new LinkedHashMap();
    }
    
    private String timeScope = DataModel.getDataModel().getTimeProperty().getScope().getTimeScope();
    
//    public void setTimeScope(String timeScope){
//        this.timeScope = timeScope;
//    }
    
    public String getTimeScope(){
        return this.timeScope;
    }
    
    private String timeScale = DataModel.getDataModel().getTimeProperty().getScale();
//    public void setTimeScale(String scale){
//        this.timeScale = scale;
//    }
    public String getTimeScale(){
        return this.timeScale;
    }
    
    
    private double tickAngle = Double.MIN_VALUE;
    
    public void setTickAngle(double tickAngle){
        this.tickAngle = tickAngle;
    }
    public double getTickAngle(){
        return this.tickAngle;
    }
    
    boolean firstTimeY = true;
    private LinkedHashMap<Float, Double> yPosMap;
    private LinkedHashMap<Float, Double> xPosMap;
    public double getYValue(Float yPosition){
        Set<Float> keySet =  yPosMap.keySet();
        float lastKey = Float.MIN_VALUE;
        float curKey = Float.MIN_VALUE;
        Iterator itr = keySet.iterator();
        if(itr.hasNext()){
            lastKey = (float) itr.next();
        }
        if(yPosition >= lastKey){
            return yPosMap.get(yPosition);
        }else{
            while(itr.hasNext()){
                curKey = (float) itr.next();
                if(yPosition < curKey){
                    lastKey = curKey;
                }else if(yPosition > curKey){
                    if(  lastKey - yPosition > yPosition - curKey){
                        return yPosMap.get(curKey);
                    }else{
                        return yPosMap.get(lastKey);
                    }
                }else if(yPosition == curKey){
                    return yPosMap.get(yPosition);
                }
            }
        }
        return Double.MIN_VALUE;
    }
    
    public double getXValue(Float xPosition){
        return 0;
    }
    
    LinkedHashMap<Double, Point2D> tickMarkCoordinates = new LinkedHashMap();      //xing
    private void addTickMarkCoordinate(double value, double x, double y){  //xing
         if(tickMarkCoordinates.containsKey(value)){
             tickMarkCoordinates.replace(value, new Point2D.Double(x, y));
         }else{
             tickMarkCoordinates.put(value, new Point2D.Double(x, y));
         }
    }
    
    public LinkedHashMap<Double, Point2D> getTickMarkCoordinates(){
        return this.tickMarkCoordinates;
    }
    
    
    
//    public LinkedHashMap<Double, Point2D> getTickMarkPosition(){
//        return this.tickMarkCoordinates;
//    }
    
    public double getTickValue(Point2D position){  //xing
        Set<Double> keySet = this.tickMarkCoordinates.keySet();
        Iterator<Double> itr = keySet.iterator();
        while(itr.hasNext()){
            double curKey = itr.next();
            Point2D curPoint = this.tickMarkCoordinates.get(curKey);
            double x = curPoint.getX();
            double y = curPoint.getY();
            if(Math.abs(position.getX() - x) < 10 && Math.abs(position.getY() - y) < 10){
                return curKey;
            }
        }
        return Double.MIN_VALUE;
    }
    
    public boolean  intersectsWithTickLabel(Point2D position){
        if(this.getTickValue(position) < Double.MIN_VALUE + 1){
            return false;
        }
        return true;
    }
    
    protected AxisState drawTickMarksAndLabels(Graphics2D g2,
            double cursor, Rectangle2D plotArea, Rectangle2D dataArea,
            RectangleEdge edge) {

        AxisState state = new AxisState(cursor);
        //System.out.println("First getMax: " + dataArea.getX() + "   " + dataArea.getMaxX());
        if (isAxisLineVisible()) {
            drawAxisLine(g2, cursor, dataArea, edge);
        }
        List ticks = refreshTicks(g2, state, dataArea, edge);
        state.setTicks(ticks);
        g2.setFont(getTickLabelFont());
        Object saved = g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, 
                RenderingHints.VALUE_STROKE_NORMALIZE);
        Iterator iterator = ticks.iterator();
        
        double lastX0 = Double.MIN_VALUE;
        double lastY0 = Double.MIN_VALUE;
        //System.out.println("AxisState -- "+ state.getMax() + " margin: " + 0.05 * state.getMax());
        double x0;
        double y0;
        boolean first = false;
        
        this.tickMarkCoordinates.clear();  //xing
        while (iterator.hasNext()) {
            ValueTick tick = (ValueTick) iterator.next();
            
            if (isTickLabelsVisible()) {
                g2.setPaint(getTickLabelPaint());
                float[] anchorPoint = calculateAnchorPoint(tick, cursor,
                        dataArea, edge);
                if (tick instanceof LogTick) {
                    LogTick lt = (LogTick) tick;
                    if (lt.getAttributedLabel() == null) {
                        continue;
                    }
                    AttrStringUtils.drawRotatedString(lt.getAttributedLabel(), 
                            g2, anchorPoint[0], anchorPoint[1], 
                            tick.getTextAnchor(), tick.getAngle(), 
                            tick.getRotationAnchor());
                } else {
                    if (tick.getText() == null) {
                        continue;
                    }
//                    TextUtilities.drawRotatedString(tick.getText(), g2,
//                            anchorPoint[0], anchorPoint[1], 
//                            tick.getTextAnchor(), tick.getAngle(), 
//                            tick.getRotationAnchor());
                    
                    this.addTickMarkCoordinate(tick.getValue(), anchorPoint[0], anchorPoint[1] /*+ 2.5 */);  //xing
                    if(this.tickAngle == Double.MIN_VALUE){
                    TextUtilities.drawRotatedString(tick.getText(), g2, anchorPoint[0], anchorPoint[1], tick.getTextAnchor(), tick.getAngle(),tick.getRotationAnchor());
                    }else{
                        TextUtilities.drawRotatedString(tick.getText(), g2, anchorPoint[0], anchorPoint[1], tick.getTextAnchor(), this.getTickAngle(),tick.getRotationAnchor());
                    }
                
                }
            }

            if ((isTickMarksVisible() && tick.getTickType().equals(
                    TickType.MAJOR)) || (isMinorTickMarksVisible()
                    && tick.getTickType().equals(TickType.MINOR))) {

                double ol = (tick.getTickType().equals(TickType.MINOR)) 
                        ? getMinorTickMarkOutsideLength()
                        : getTickMarkOutsideLength();

                double il = (tick.getTickType().equals(TickType.MINOR)) 
                        ? getMinorTickMarkInsideLength()
                        : getTickMarkInsideLength();

                //System.out.println("xx: " + tick.getValue());
                float xx = (float) valueToJava2D(tick.getValue(), dataArea,
                        edge);
                //System.out.println("plotarea " + valueToJava2D(plotArea.getX(), dataArea, edge));
                //Line2D mark = null;
                Shape mark = null;
                g2.setStroke(getTickMarkStroke());
                g2.setPaint(getTickMarkPaint());
                 //System.out.println("cursor( " +  cursor + ") ol(" + ol + ") xx(" + xx + ") il(" + il + ")");
                //System.out.println("TickMarkOutsideLength: " + this.getTickMarkOutsideLength() + "  TickMarkInsideLength: " + this.getTickMarkInsideLength());
                 if (edge == RectangleEdge.LEFT) {
                   //System.out.println("left");
                    mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
                    //mark = new Rectangle2D.Double(cursor - ol, xx, cursor + il, xx);
                    
                    if(firstTimeY){         //xing
                        yPosMap.clear();
                        //xPosMap.clear();
                        firstTimeY = false;
                    }
                    yPosMap.put(xx, tick.getValue());  //xing
                    
                    g2.draw(mark);
                }
                else if (edge == RectangleEdge.RIGHT) {
                    //System.out.println("right");
                            
                    mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
                    //mark = new Rectangle2D.Double(cursor + ol, xx, cursor - il, xx);
                    g2.draw(mark);
                }
                else if (edge == RectangleEdge.TOP) {
                    //System.out.println("top");
                    //mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
                    mark = new Rectangle2D.Double(xx, cursor - ol, xx, cursor + il);
                    x0 = xx;
                    y0 = cursor - ol;
                    g2.draw(mark);
                }
                else if (edge == RectangleEdge.BOTTOM) {
                    //System.out.println("bottom");
                    if(this.timeScale.equals(TimeProperty.SCALE_DISCRETE)){
                            mark = new Ellipse2D.Double(xx - 2.5, cursor + ol - 4.1, 5, 5);
                            Color oldColor = g2.getColor();
                            
                            Stroke oldStroke = g2.getStroke();
                            g2.setStroke(new BasicStroke(1));
                            g2.setColor(Color.black);
                            g2.draw(mark);
                            g2.setColor(Color.white);
                            g2.fill(mark);
                            g2.setColor(oldColor);
                            g2.setStroke(oldStroke);
                        }else if(this.timeScale.equals(TimeProperty.SCALE_CONTINUOUS)){
                            mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
                            g2.draw(mark);
                        }else if(this.timeScale.equals(TimeProperty.SCALE_ORDINAL)){
                            
                        }
                    //System.out.println("LowerMargin: " + this.getLowerMargin() + " UpperMargin: "+ this.getUpperMargin());
                    if (this.getTimeScope().equals(TimeScope.SCOPE_POINT)) {
                       
                       // System.out.println("scope_point");
//                        if(this.timeScale.equals(TimeProperty.SCALE_DISCRETE)){
                            mark = new Ellipse2D.Double(xx - 2.5, cursor + ol - 4.1, 5, 5);
//                            Color oldColor = g2.getColor();
//                            
//                            Stroke oldStroke = g2.getStroke();
//                            g2.setStroke(new BasicStroke(1));
//                            g2.setColor(Color.black);
                            g2.draw(mark);
//                            g2.setColor(Color.white);
//                            g2.fill(mark);
//                            g2.setColor(oldColor);
//                            g2.setStroke(oldStroke);
//                        }else if(this.timeScale.equals(TimeProperty.SCALE_CONTINUOUS)){
//                            mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
//                            g2.draw(mark);
//                        }else if(this.timeScale.equals(TimeProperty.SCALE_ORDINAL)){
//                            
//                        }
                        
                    } else if (this.getTimeScope().equals(TimeScope.SCOPE_INTERVAL)) {
                        
                        //System.out.println("scope_interval");
                        
                        if (lastX0 == Double.MIN_VALUE) {
                            //System.out.println("lower margin: " + this.getLowerMargin() + "  maxX:" + dataArea.getMaxX() + "  getx: " + dataArea.getX());
                            //double w = xx - this.getLowerMargin() *( dataArea.getMaxX() - dataArea.getX());
                            mark = new Rectangle2D.Double(dataArea.getMinX(), cursor + ol, xx-2 - dataArea.getMinX() , ol);
                            //System.out.println("w is: " + w);
                            g2.draw(mark);
                            g2.setColor(Color.GRAY);
                            g2.fill(mark);
                            lastX0 = xx;
                            lastY0 = cursor + ol;
                        } else {
                            if(!first){
                                Shape mark0 = new Rectangle2D.Double(lastX0 + 2, lastY0, xx - lastX0 - 2, ol);
                                g2.draw(mark0);
                                g2.setColor(Color.GRAY);
                                g2.fill(mark0);
                                first = true;
                            }
                            
                            mark = new Rectangle2D.Double(xx + 2, cursor + ol, xx - lastX0 - 2, ol);
                            
                            g2.draw(mark);
                            g2.setColor(Color.GRAY);
                            g2.fill(mark);
                            lastX0 = xx;
                            lastY0 = cursor + ol;
                        }
                        
                    }
                    
                   // System.out.println("x0:" + xx + " y0:" + (cursor + ol) + " x1:" + xx + " y1: " + (cursor - il));
                    
                }
                //g2.draw(mark);
            }
        }
        
        firstTimeY = true;  //xing
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, saved);
        
        // need to work out the space used by the tick labels...
        // so we can update the cursor...
        double used = 0.0;
        if (isTickLabelsVisible()) {
            if (edge == RectangleEdge.LEFT) {
                //System.out.println("TickLabel LEFT");
                used += findMaximumTickLabelWidth(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorLeft(used);
            } else if (edge == RectangleEdge.RIGHT) {
                //System.out.println("TickLabel RIGHT");
                used = findMaximumTickLabelWidth(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorRight(used);
            } else if (edge == RectangleEdge.TOP) {
                //System.out.println("TickLabel TOP");
                used = findMaximumTickLabelHeight(ticks, g2, plotArea,
                        isVerticalTickLabels());
                state.cursorUp(used);
            } else if (edge == RectangleEdge.BOTTOM) {
                
                used = findMaximumTickLabelHeight(ticks, g2, plotArea,
                        isVerticalTickLabels());
                //System.out.println("TickLabel BOTTOM   used: " + used);
                state.cursorDown(used );
            }
        }

        return state;
    }
}
