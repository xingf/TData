/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import org.jfree.chart.HashUtilities;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.PublicCloneable;
import model.dataProperty.DataProperty;
import model.timeProperty.TimeProperty;
import model.timeProperty.TimeScope;

/**
 *
 * @author Administrator
 */
public class MyJFreeChartXYStepRenderer extends XYLineAndShapeRenderer
        implements XYItemRenderer, Cloneable, PublicCloneable, Serializable{
     /** For serialization. */
    private static final long serialVersionUID = -8918141928884796108L;

    /**
     * The factor (from 0.0 to 1.0) that determines the position of the
     * step.
     *
     * @since 1.0.10.
     */
    private double stepPoint = 1.0d;

    /**
     * Constructs a new renderer with no tooltip or URL generation.
     */
    public MyJFreeChartXYStepRenderer() {
        this(null, null);
        solidStroke = (BasicStroke)this.getBaseStroke();
            float[] dash = {10.0f, 10.0f};
            dottedStroke = new BasicStroke(solidStroke.getLineWidth(), solidStroke.getEndCap(), solidStroke.getLineJoin(),
            solidStroke.getMiterLimit(),dash,solidStroke.getDashPhase());
    }

    /**
     * Constructs a new renderer with the specified tool tip and URL
     * generators.
     *
     * @param toolTipGenerator  the item label generator (<code>null</code>
     *     permitted).
     * @param urlGenerator  the URL generator (<code>null</code> permitted).
     */
    public MyJFreeChartXYStepRenderer(XYToolTipGenerator toolTipGenerator,
                          XYURLGenerator urlGenerator) {
        super();
        setBaseToolTipGenerator(toolTipGenerator);
        setURLGenerator(urlGenerator);
        setBaseShapesVisible(false);
        
         solidStroke = (BasicStroke)this.getBaseStroke();
            float[] dash = {20.0f, 20.0f};
            dottedStroke = new BasicStroke(solidStroke.getLineWidth(), solidStroke.getEndCap(), solidStroke.getLineJoin(),
            solidStroke.getMiterLimit(),dash,solidStroke.getDashPhase());
            kindOfData = DataProperty.getDataProperty().getDataKind();
    }

    BasicStroke solidStroke;
    BasicStroke dottedStroke;
    String kindOfData;
    /**
     * Returns the fraction of the domain position between two points on which
     * the step is drawn.  The default is 1.0d, which means the step is drawn
     * at the domain position of the second`point. If the stepPoint is 0.5d the
     * step is drawn at half between the two points.
     *
     * @return The fraction of the domain position between two points where the
     *         step is drawn.
     *
     * @see #setStepPoint(double)
     *
     * @since 1.0.10
     */
    public double getStepPoint() {
        return this.stepPoint;
    }

    /**
     * Sets the step point and sends a {@link RendererChangeEvent} to all
     * registered listeners.
     *
     * @param stepPoint  the step point (in the range 0.0 to 1.0)
     *
     * @see #getStepPoint()
     *
     * @since 1.0.10
     */
    public void setStepPoint(double stepPoint) {
        if (stepPoint < 0.0d || stepPoint > 1.0d) {
            throw new IllegalArgumentException(
                    "Requires stepPoint in [0.0;1.0]");
        }
        this.stepPoint = stepPoint;
        fireChangeEvent();
    }

    /**
     * Draws the visual representation of a single data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the area within which the data is being drawn.
     * @param info  collects information about the drawing.
     * @param plot  the plot (can be used to obtain standard color
     *              information etc).
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the vertical axis.
     * @param dataset  the dataset.
     * @param series  the series index (zero-based).
     * @param item  the item index (zero-based).
     * @param crosshairState  crosshair information for the plot
     *                        (<code>null</code> permitted).
     * @param pass  the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, XYItemRendererState state,
            Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot,
            ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset,
            int series, int item, CrosshairState crosshairState, int pass) {

        // do nothing if item is not visible
        if (!getItemVisible(series, item)) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();

        Paint seriesPaint = getItemPaint(series, item);
        Stroke seriesStroke = getItemStroke(series, item);
        g2.setPaint(seriesPaint);
        g2.setStroke(seriesStroke);

        // get the data point...
        double x1 = dataset.getXValue(series, item);
        double y1 = dataset.getYValue(series, item);

        RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
        double transY1 = (Double.isNaN(y1) ? Double.NaN
                : rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation));

        if (pass == 0 && item > 0) {
            // get the previous data point...
            double x0 = dataset.getXValue(series, item - 1);
            double y0 = dataset.getYValue(series, item - 1);
            double transX0 = domainAxis.valueToJava2D(x0, dataArea,
                    xAxisLocation);
            double transY0 = (Double.isNaN(y0) ? Double.NaN
                    : rangeAxis.valueToJava2D(y0, dataArea, yAxisLocation));

            if (orientation == PlotOrientation.HORIZONTAL) {
                if (transY0 == transY1) {
                    // this represents the situation
                    // for drawing a horizontal bar.
                    drawLine(g2, state.workingLine, transY0, transX0, transY1,
                            transX1);
                }
                else {  //this handles the need to perform a 'step'.

                    // calculate the step point
                    double transXs = transX0 + (getStepPoint()
                            * (transX1 - transX0));
                    drawLine(g2, state.workingLine, transY0, transX0, transY0,
                            transXs);
                    drawLine(g2, state.workingLine, transY0, transXs, transY1,
                            transXs);
                    drawLine(g2, state.workingLine, transY1, transXs, transY1,
                            transX1);
                }
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                if (transY0 == transY1) { // this represents the situation
                                          // for drawing a horizontal bar.
                    drawLine(g2, state.workingLine, transX0, transY0, transX1,
                            transY1);
                }
                else {  //this handles the need to perform a 'step'.
                    // calculate the step point
                    double transXs = transX0 + (getStepPoint()
                            * (transX1 - transX0));
                    drawLine(g2, state.workingLine, transX0, transY0, transXs,
                            transY0);
                    drawLine(g2, state.workingLine, transXs, transY0, transXs,
                            transY1);
                    drawLine(g2, state.workingLine, transXs, transY1, transX1,
                            transY1);
                }
            }

            // submit this data item as a candidate for the crosshair point
            int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
            int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
            updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex,
                    rangeAxisIndex, transX1, transY1, orientation);

            // collect entity and tool tip information...
            EntityCollection entities = state.getEntityCollection();
            if (entities != null) {
                addEntity(entities, null, dataset, series, item, transX1,
                        transY1);
            }

        }

        if (pass == 1) {
            // draw the item label if there is one...
            if (isItemLabelVisible(series, item)) {
                double xx = transX1;
                double yy = transY1;
                if (orientation == PlotOrientation.HORIZONTAL) {
                    xx = transY1;
                    yy = transX1;
                }
                drawItemLabel(g2, orientation, dataset, series, item, xx, yy,
                        (y1 < 0.0));
            }
        }
    }

    /**
     * A utility method that draws a line but only if none of the coordinates
     * are NaN values.
     *
     * @param g2  the graphics target.
     * @param line  the line object.
     * @param x0  the x-coordinate for the starting point of the line.
     * @param y0  the y-coordinate for the starting point of the line.
     * @param x1  the x-coordinate for the ending point of the line.
     * @param y1  the y-coordinate for the ending point of the line.
     */
    
    
//    private void drawLine(Graphics2D g2, Line2D line, double x0, double y0,
//            double x1, double y1) {
//        if (Double.isNaN(x0) || Double.isNaN(x1) || Double.isNaN(y0)
//                || Double.isNaN(y1)) {
//            return;
//        }
//        //System.out.println("draw Line");
//        
//        if(this.kindOfData.equals(DataProperty.DATAKIND_EVENT)){
//             if(x0 == x1){
//            //System.out.println("Draw Horizontal Line");
//           g2.setStroke(this.solidStroke);
//           line.setLine(x0, y0, x1, y1);
//           g2.draw(line);
//        }else if(y0 == y1){
//            //System.out.println("Draw Vertical Line");
//            g2.setStroke(this.dottedStroke);
//            //g2.setColor(Color.gray);
//            line.setLine(x0, y0, x1, y1);
//            g2.draw(line);
//        }
//        }else if(this.kindOfData.equals(DataProperty.DATAKIND_STATE)){
//             if(x0 == x1){
//            //System.out.println("Draw Horizontal Line");
//           g2.setStroke(this.dottedStroke);
//           //g2.setColor(Color.gray);
//           line.setLine(x0, y0, x1, y1);
//           g2.draw(line);
//        }else if(y0 == y1){
//            //System.out.println("Draw Vertical Line");
//            g2.setStroke(this.solidStroke);
//            line.setLine(x0, y0, x1, y1);
//            g2.draw(line);
//        }
//        }
//       
//        g2.setStroke(this.solidStroke);
//        
//    }
    
     private void drawLine(Graphics2D g2, Line2D line, double x0, double y0,
            double x1, double y1) {
        if (Double.isNaN(x0) || Double.isNaN(x1) || Double.isNaN(y0)
                || Double.isNaN(y1)) {
            return;
        }
        String tmp = TimeProperty.getTimeProperty().getTimePrimitives();
        if (TimeProperty.getTimeProperty().getTimePrimitives().equals(TimeProperty.TIMEPRIMITIVES_INTERVAL)) {
            line.setLine(x0, y0, x1, y1);
            g2.draw(line);
        }

         
        
    }
    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYLineAndShapeRenderer)) {
            return false;
        }
        MyJFreeChartXYStepRenderer that = (MyJFreeChartXYStepRenderer) obj;
        if (this.stepPoint != that.stepPoint) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return HashUtilities.hashCode(super.hashCode(), this.stepPoint);
    }

    /**
     * Returns a clone of the renderer.
     *
     * @return A clone.
     *
     * @throws CloneNotSupportedException  if the renderer cannot be cloned.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
