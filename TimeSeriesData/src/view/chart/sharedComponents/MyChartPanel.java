/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import toolkit.Toolkit;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseWheelEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ToolTipManager;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.Pannable;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.Zoomable;

/**
 *
 * @author Administrator
 */
public class MyChartPanel extends ChartPanel {

    boolean mouseSwitchOff = false;  //xing
    public ZoomHandler zoomHandler = new ZoomHandler(this);
    
    public MyChartPanel(JFreeChart chart) {

        super(chart);
        this.gestureMask = InputEvent.SHIFT_MASK;
    }

    /**
     * Constructs a panel containing a chart. The <code>useBuffer</code> flag
     * controls whether or not an offscreen <code>BufferedImage</code> is
     * maintained for the chart. If the buffer is used, more memory is consumed,
     * but panel repaints will be a lot quicker in cases where the chart itself
     * hasn't changed (for example, when another frame is moved to reveal the
     * panel). WARNING: If you set the <code>useBuffer</code> flag to false,
     * note that the mouse zooming rectangle will (in that case) be drawn using
     * XOR, and there is a SEVERE performance problem with that on JRE6 on
     * Windows.
     *
     * @param chart the chart.
     * @param useBuffer a flag controlling whether or not an off-screen buffer
     * is used (read the warning above before setting this to
     * <code>false</code>).
     */
    public MyChartPanel(JFreeChart chart, boolean useBuffer) {

        super(chart, useBuffer);

    }

    /**
     * Constructs a JFreeChart panel.
     *
     * @param chart the chart.
     * @param properties a flag indicating whether or not the chart property
     * editor should be available via the popup menu.
     * @param save a flag indicating whether or not save options should be
     * available via the popup menu.
     * @param print a flag indicating whether or not the print option should be
     * available via the popup menu.
     * @param zoom a flag indicating whether or not zoom options should be added
     * to the popup menu.
     * @param tooltips a flag indicating whether or not tooltips should be
     * enabled for the chart.
     */
    public MyChartPanel(JFreeChart chart,
            boolean properties,
            boolean save,
            boolean print,
            boolean zoom,
            boolean tooltips) {

        super(chart, properties, save, print, zoom, tooltips);

    }

    /**
     * Constructs a JFreeChart panel.
     *
     * @param chart the chart.
     * @param width the preferred width of the panel.
     * @param height the preferred height of the panel.
     * @param minimumDrawWidth the minimum drawing width.
     * @param minimumDrawHeight the minimum drawing height.
     * @param maximumDrawWidth the maximum drawing width.
     * @param maximumDrawHeight the maximum drawing height.
     * @param useBuffer a flag that indicates whether to use the off-screen
     * buffer to improve performance (at the expense of memory).
     * @param properties a flag indicating whether or not the chart property
     * editor should be available via the popup menu.
     * @param save a flag indicating whether or not save options should be
     * available via the popup menu.
     * @param print a flag indicating whether or not the print option should be
     * available via the popup menu.
     * @param zoom a flag indicating whether or not zoom options should be added
     * to the popup menu.
     * @param tooltips a flag indicating whether or not tooltips should be
     * enabled for the chart.
     */
    public MyChartPanel(JFreeChart chart, int width, int height,
            int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
            int maximumDrawHeight, boolean useBuffer, boolean properties,
            boolean save, boolean print, boolean zoom, boolean tooltips) {

        super(chart, width, height,
                minimumDrawWidth, minimumDrawHeight, maximumDrawWidth,
                maximumDrawHeight, useBuffer, properties,
                save, print, zoom, tooltips);
    }

    /**
     * Constructs a JFreeChart panel.
     *
     * @param chart the chart.
     * @param width the preferred width of the panel.
     * @param height the preferred height of the panel.
     * @param minimumDrawWidth the minimum drawing width.
     * @param minimumDrawHeight the minimum drawing height.
     * @param maximumDrawWidth the maximum drawing width.
     * @param maximumDrawHeight the maximum drawing height.
     * @param useBuffer a flag that indicates whether to use the off-screen
     * buffer to improve performance (at the expense of memory).
     * @param properties a flag indicating whether or not the chart property
     * editor should be available via the popup menu.
     * @param copy a flag indicating whether or not a copy option should be
     * available via the popup menu.
     * @param save a flag indicating whether or not save options should be
     * available via the popup menu.
     * @param print a flag indicating whether or not the print option should be
     * available via the popup menu.
     * @param zoom a flag indicating whether or not zoom options should be added
     * to the popup menu.
     * @param tooltips a flag indicating whether or not tooltips should be
     * enabled for the chart.
     *
     * @since 1.0.13
     */
    public MyChartPanel(JFreeChart chart, int width, int height,
            int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth,
            int maximumDrawHeight, boolean useBuffer, boolean properties,
            boolean copy, boolean save, boolean print, boolean zoom,
            boolean tooltips) {

        super(chart, width, height,
                minimumDrawWidth, minimumDrawHeight, maximumDrawWidth,
                maximumDrawHeight, useBuffer, properties,
                copy, save, print, zoom,
                tooltips);
        
    }

    private Field getField(String fieldName) {
        try {
            Field field = ChartPanel.class.getDeclaredField(fieldName);
            if(!field.isAccessible()){
                field.setAccessible(true);
            }
            return field;
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Point getPanLast(){
        try {
            Field field = this.getField("panLast");
            return (Point)field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void setPanLast(Point point){
        try {
            this.getField("panLast").set(this, point);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private double getDoubleFieldValue(String doubleFieldName){
         try {
            return this.getField(doubleFieldName).getDouble(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Double.NaN;
    }
    private double getPanW(){
        try {
            return this.getField("panW").getDouble(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Double.NaN;
    }
    
    private void setPanW(double panW){
        try {
            this.getField("panW").setDouble(this, panW);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private double getPanH(){
        try {
            return this.getField("panH").getDouble(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Double.NaN;
    }
    
    private void setPanH(double panH){
        try {
            this.getField("panH").setDouble(this, panH);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private Point2D getZoomPoint(){
        try {
            Field field = this.getField("zoomPoint");
            return (Point2D)field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    private void setZoomPoint(Point2D point){
        try {
            Field field = this.getField("zoomPoint");
            field.set(this, point);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean getUseBuffer(){
        try {
            return this.getField("useBuffer").getBoolean(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Boolean.FALSE;
    }
    private void setUseBuffer(boolean ub){
        try {
            this.getField("useBuffer").setBoolean(this, ub);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setOrientation(PlotOrientation plotOrientation){
        try {
            Field field = this.getField("orientation");
            field.set(this, plotOrientation);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private PlotOrientation getOrientation(){
        try {
            Field field = this.getField("orientation");
            return (PlotOrientation)field.get(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void setZoomRectangle(Rectangle2D rec){
        try {
            Field field = this.getField("zoomRectangle");
            field.set(this, rec);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Rectangle2D getZoomRectangle(){
        try {
            Field field = this.getField("zoomRectangle");
            return (Rectangle2D)(field.get(this));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    private boolean isOwnToolTipDelaysActive() {
        try {
            return this.getField("ownToolTipDelaysActive").getBoolean(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void setOwnToolTipDelaysActive(boolean active){
        try {
            this.getField("ownToolTipDelaysActive").setBoolean(this, active);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int getOriginalToolTipInitialDelay(){
        try {
            return this.getField("originalToolTipInitialDelay").getInt(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.MIN_VALUE;
    }
    private void setOriginalToolTipInitialDelay(int delay){
        try {
            this.getField("originalToolTipInitialDelay").setInt(this, delay);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setOriginalToolTipDismissDelay(int delay){
        try {
            this.getField("originalToolTipDismissDelay").setInt(this, delay);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private int getOriginalToolTipDismissDelay(){
        try {
            return this.getField("originalToolTipDismissDelay").getInt(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.MIN_VALUE;
    }
    private int getOriginalToolTipReshowDelay(){
        try {
            return this.getField("originalToolTipReshowDelay").getInt(this);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.MIN_VALUE;
    }
    private void setOriginalToolTipReshowDelay(int delay){
        try {
            this.getField("originalToolTipReshowDelay").setInt(this, delay);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private int getPanMask(){
        try {
            int panMask = this.getField("panMask").getInt(this);
            //System.out.println("panMask " + panMask);
            //panMask = panMask | 0x0010;
            return panMask;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.MIN_VALUE;
    }
    private void setPanMask(int mask){
        try {
            this.getField("panMask").setInt(this, mask);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void draggedPanningHandler(MouseEvent e){
        if (this.getPanLast() != null) {
            double dx = e.getX() - this.getPanLast().getX();
            double dy = e.getY() - this.getPanLast().getY();
            if (dx == 0.0 && dy == 0.0) {
                return;
            }
            double wPercent = -dx / this.getPanW();
            double hPercent = dy / this.getPanH();
            boolean old = this.getChart().getPlot().isNotify();
            this.getChart().getPlot().setNotify(false);
            Pannable p = (Pannable) this.getChart().getPlot();
            //System.out.println("panning with start point-- position(" + e.getX() + ", " + e.getY() + "), panLast(" + this.getPanLast().x + ", " + this.getPanLast().y + "), panW and panH(" + this.getPanW() + ", " + this.getPanH() + "), orientation(" + this.getOrientation().toString()+")" );
            if (p.getOrientation() == PlotOrientation.VERTICAL) {
                //System.out.println("VERTICAL");
                p.panDomainAxes(wPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
                p.panRangeAxes(hPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
            }
            else {
                //System.out.println("HORIZONTAL");
                p.panDomainAxes(hPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
                p.panRangeAxes(wPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
            }
            
            //this.panLast = e.getPoint();
            this.setPanLast(e.getPoint());
            this.getChart().getPlot().setNotify(old);
           
        }
    }
    
    private void draggedErasePreviousZoomRectangle(Graphics2D g2){
        

        // erase the previous zoom rectangle (if any).  We only need to do
        // this is we are using XOR mode, which we do when we're not using
        // the buffer (if there is a buffer, then at the end of this method we
        // just trigger a repaint)
        if (!this.getUseBuffer()) {
            //System.out.println("erase -- No useBuffer");
            try {
                Method drawZoomRectangleFunction = this.getClass().getDeclaredMethod("drawZoomRectangle", Graphics2D.class, boolean.class);
                //System.out.println("No useBuffer , draw zoom rectangle");
                drawZoomRectangleFunction.invoke(this, g2, true);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //drawZoomRectangle(g2, true);
        }

    }
    
    private int gestureMask = InputEvent.SHIFT_MASK;
    private ArrayList<Point2D.Double> path = new ArrayList<>();
    GeneralPath gPath; // = new GeneralPath(0,0);
    private void draggedSetUpZoomRectangle(Graphics2D g2, MouseEvent e){
        boolean hZoom, vZoom;
        
        //if (this.orientation == PlotOrientation.HORIZONTAL) {
        if (this.getOrientation().equals(PlotOrientation.HORIZONTAL)) {
            hZoom = this.isRangeZoomable();
            vZoom = this.isDomainZoomable();
        } else {
            hZoom = this.isDomainZoomable();
            vZoom = this.isRangeZoomable();
        }

        if ((e.getModifiers() & this.gestureMask) == this.gestureMask) {

            // set dragged path
            if (path.isEmpty()) {
                path.add(new Point2D.Double(e.getX(), e.getY()));

            } else {
                path.add(new Point2D.Double(e.getX(), e.getY()));
            }
            if (path.size() > 1) {
                gPath = new GeneralPath(GeneralPath.WIND_EVEN_ODD, path.size());
                gPath.moveTo(path.get(0).getX(), path.get(0).getY());

                for (int index = 1; index < path.size(); index++) {
                    gPath.lineTo(path.get(index).getX(), path.get(index).getY());
                    //System.out.println("draw path: " + index );
                }
                g2.setPaint(Color.red);
                g2.draw(gPath);
                this.repaint();
                
                
            }
        } else {
            if (true) {
                Rectangle2D scaledDataArea = getScreenDataArea(
                        (int) this.getZoomPoint().getX(), (int) this.getZoomPoint().getY());
                if (hZoom && vZoom) {
                    // selected rectangle shouldn't extend outside the data area...
                    double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
                    double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
                    this.setZoomRectangle(new Rectangle2D.Double(
                            this.getZoomPoint().getX(), this.getZoomPoint().getY(),
                            xmax - this.getZoomPoint().getX(), ymax - this.getZoomPoint().getY()));
                } else if (hZoom) {
                    double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
                    this.setZoomRectangle(new Rectangle2D.Double(
                            this.getZoomPoint().getX(), scaledDataArea.getMinY(),
                            xmax - this.getZoomPoint().getX(), scaledDataArea.getHeight()));
                } else if (vZoom) {
                    double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
                    this.setZoomRectangle(new Rectangle2D.Double(
                            scaledDataArea.getMinX(), this.getZoomPoint().getY(),
                            scaledDataArea.getWidth(), ymax - this.getZoomPoint().getY()));
                }

                // Draw the new zoom rectangle...
//        if (this.useBuffer) {
                if (this.getUseBuffer()) {
                    //System.out.println("draw rectangle -- use Buffer");
                    repaint();
                } else {
                    //System.out.println("draw rectangle-- no UseBuffer");
            // with no buffer, we use XOR to draw the rectangle "over" the
                    // chart...
                    try {
                        Method drawZoomRectangleFunction = this.getClass().getDeclaredMethod("drawZoomRectangle", Graphics2D.class, boolean.class);
                        drawZoomRectangleFunction.invoke(this, g2, true);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //drawZoomRectangle(g2, true);
                }
            }
        }

        
        
        
       


       
    }
    /**
     * Handles a 'mouse dragged' event.
     *
     * @param e  the mouse event.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
         if(mouseSwitchOff){  // xing
            return;
        }
        //System.out.println("mouse dragged");
        // if the popup menu has already been triggered, then ignore dragging...
        if (this.getPopupMenu() != null && this.getPopupMenu().isShowing()) {
            return;
        }

      
        //GestureRecognizer.getGestureRecognitizer().addGesturePoint(e, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);
        
       // handle panning if we have a start point
        if (this.getPanLast() != null) {
            this.draggedPanningHandler(e);
            return;
        }

        // if no initial zoom point was set, ignore dragging...
        if (this.getZoomPoint() == null) {
            return;
        }
        
        Graphics2D g2 = (Graphics2D) getGraphics();
        
        // erase the previous zoom rectangle (if any).  We only need to do
        // this is we are using XOR mode, which we do when we're not using
        // the buffer (if there is a buffer, then at the end of this method we
        // just trigger a repaint)
        //this.draggedErasePreviousZoomRectangle(g2);

        //setup zoom rectangle
        this.draggedSetUpZoomRectangle(g2, e);
        
        //release g2
        g2.dispose();

    }

    
    @Override
    public void mouseEntered(MouseEvent e) {
         if(mouseSwitchOff){  // xing
            return;
        }
        //System.out.println("mouse entered");
//        if (!this.ownToolTipDelaysActive) {
        if (!this.isOwnToolTipDelaysActive()) {
            ToolTipManager ttm = ToolTipManager.sharedInstance();

            //this.originalToolTipInitialDelay = ttm.getInitialDelay();
            this.setOriginalToolTipInitialDelay(ttm.getInitialDelay());
            //ttm.setInitialDelay(this.ownToolTipInitialDelay);
            ttm.setInitialDelay(this.getInitialDelay());
            
//            this.originalToolTipReshowDelay = ttm.getReshowDelay();
//            ttm.setReshowDelay(this.ownToolTipReshowDelay);
            this.setOriginalToolTipInitialDelay(ttm.getReshowDelay());
            ttm.setReshowDelay(this.getReshowDelay());

//            this.originalToolTipDismissDelay = ttm.getDismissDelay();
//            ttm.setDismissDelay(this.ownToolTipDismissDelay);
              this.setOriginalToolTipDismissDelay(ttm.getDismissDelay());
              ttm.setDismissDelay(this.getDismissDelay());

//            this.ownToolTipDelaysActive = true;
            this.setOwnToolTipDelaysActive(true);
        }
    }

    /**
     * Handles a 'mouse exited' event. This method resets the tooltip delays of
     * ToolTipManager.sharedInstance() to their original values in effect before
     * mouseEntered()
     *
     * @param e the mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e) {
         if(mouseSwitchOff){  // xing
            return;
        }
        //System.out.println("mouse exited");
        if (this.isOwnToolTipDelaysActive()) {
            // restore original tooltip dealys
            ToolTipManager ttm = ToolTipManager.sharedInstance();
            ttm.setInitialDelay(this.getOriginalToolTipInitialDelay());
            ttm.setReshowDelay(this.getOriginalToolTipReshowDelay());
            ttm.setDismissDelay(this.getOriginalToolTipDismissDelay());
            this.setOwnToolTipDelaysActive(false);
        }
    }

    
    private void releasedDrawZoomRectangle(MouseEvent e){
        boolean hZoom, vZoom;
            if (this.getOrientation() == PlotOrientation.HORIZONTAL) {
                hZoom = this.isRangeZoomable();
                vZoom = this.isDomainZoomable();
            } else {
                hZoom = this.isDomainZoomable();
                vZoom = this.isRangeZoomable();
            }

            boolean zoomTrigger1 = hZoom && Math.abs(e.getX()
                    - this.getZoomPoint().getX()) >= this.getZoomTriggerDistance();
            boolean zoomTrigger2 = vZoom && Math.abs(e.getY()
                    - this.getZoomPoint().getY()) >= this.getZoomTriggerDistance();
            if (zoomTrigger1 || zoomTrigger2) {
                if ((hZoom && (e.getX() < this.getZoomPoint().getX()))
                        || (vZoom && (e.getY() < this.getZoomPoint().getY()))) {
                    
                     //如果从右向左画矩形框，回复到初始可视化图像
                    restoreAutoBounds();
                } else {
                    double x, y, w, h;
                    Rectangle2D screenDataArea = getScreenDataArea(
                            (int) this.getZoomPoint().getX(),
                            (int) this.getZoomPoint().getY());
                    double maxX = screenDataArea.getMaxX();
                    double maxY = screenDataArea.getMaxY();
                    // for mouseReleased event, (horizontalZoom || verticalZoom)
                    // will be true, so we can just test for either being false;
                    // otherwise both are true
                    if (!vZoom) {
                        x = this.getZoomPoint().getX();
                        y = screenDataArea.getMinY();
                        w = Math.min(this.getZoomRectangle().getWidth(),
                                maxX - this.getZoomPoint().getX());
                        h = screenDataArea.getHeight();
                    } else if (!hZoom) {
                        x = screenDataArea.getMinX();
                        y = this.getZoomPoint().getY();
                        w = screenDataArea.getWidth();
                        h = Math.min(this.getZoomRectangle().getHeight(),
                                maxY - this.getZoomPoint().getY());
                    } else {
                        x = this.getZoomPoint().getX();
                        y = this.getZoomPoint().getY();
                        w = Math.min(this.getZoomRectangle().getWidth(),
                                maxX - this.getZoomPoint().getX());
                        h = Math.min(this.getZoomRectangle().getHeight(),
                                maxY - this.getZoomPoint().getY());
                    }
                    Rectangle2D zoomArea = new Rectangle2D.Double(x, y, w, h);
                    zoom(zoomArea);
                }
                
                this.setZoomPoint(null);
                this.setZoomRectangle(null);
            } else {
                // erase the zoom rectangle
                Graphics2D g2 = (Graphics2D) getGraphics();
                if (this.getUseBuffer()) {
                    repaint();
                } else {
                    try {
                        Method drawZoomRectangleFunction = this.getClass().getDeclaredMethod("drawZoomRectangle", Graphics2D.class, boolean.class);
                        drawZoomRectangleFunction.invoke(this, g2, true);
                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //drawZoomRectangle(g2, true);
                }
                g2.dispose();
                this.setZoomPoint(null);
                this.setZoomRectangle(null);
            }
    }
    /**
     * Handles a 'mouse released' event. On Windows, we need to check if this is
     * a popup trigger, but only if we haven't already been tracking a zoom
     * rectangle.
     *
     * @param e information about the event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
         if(mouseSwitchOff){          // xing
            return;
        }
         //System.out.println("mouse released");
        // if we've been panning, we need to reset now that the mouse is 
        // released...
        //GestureRecognizer.getGestureRecognitizer().addGesturePoint(e, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);
//        System.out.println("mouseReleased: (" + e.getPoint().x + ", " + e.getPoint().y + ")");
//        GestureRecognizer.getGestureRecognitizer().recognize();
        if (this.getPanLast() != null) {
            this.setPanLast(null);
            setCursor(Cursor.getDefaultCursor());
        }//else if ((e.getModifiers() & this.gestureMask) == this.gestureMask) {
        else if ((e.getModifiers() & this.gestureMask) == this.gestureMask) {
            //String tmp = "angle is: ";
            for(int index = 7; index < path.size(); index = index + 7){
               double angle = Toolkit.calAngleToRefPositionByPosition((Point2D)path.get(index), (Point2D)path.get(index - 7));
               //tmp += " " + String.valueOf(angle);
            }
           
            this.path.clear();
        } else if (this.getZoomRectangle() != null) {
            this.releasedDrawZoomRectangle(e);

        } else if (e.isPopupTrigger()) {
            if (this.getPopupMenu() != null) {
                displayPopupMenu(e.getX(), e.getY());
            }
        }

        
    }

   
    /**
     * Receives notification of mouse clicks on the panel. These are
     * translated and passed on to any registered {@link ChartMouseListener}s.
     *
     * @param event  Information about the mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        //System.out.println("mouse Clicked");
        if(mouseSwitchOff){  // xing
            return;
        }
        Insets insets = getInsets();
        int x = (int) ((event.getX() - insets.left) / this.getScaleX());
        int y = (int) ((event.getY() - insets.top) / this.getScaleY());

//        System.out.println("Click Position: (" + event.getX() + ", " + event.getY() + ")" );
//        System.out.println("Source: " + event.getSource().toString());
        
        //this.anchor = new Point2D.Double(x, y);
        this.setAnchor(new Point2D.Double(x, y));
        if (this.getChart() == null) {
            return;
        }
        this.getChart().setNotify(true);  // force a redraw
        // new entity code...
//        Object[] listeners = this.chartMouseListeners.getListeners(
//                ChartMouseListener.class);
        Object[] listeners = this.getListeners(
                ChartMouseListener.class);
        if (listeners.length == 0) {
            return;
        }

        ChartEntity entity = null;
        if (this.getChartRenderingInfo() != null) {
            EntityCollection entities = this.getChartRenderingInfo().getEntityCollection();
            if (entities != null) {
                entity = entities.getEntity(x, y);
            }
        }
        ChartMouseEvent chartEvent = new ChartMouseEvent(getChart(), event,
                entity);
        for (int i = listeners.length - 1; i >= 0; i -= 1) {
            ((ChartMouseListener) listeners[i]).chartMouseClicked(chartEvent);
        }

    }

    /**
     * Implementation of the MouseMotionListener's method.
     *
     * @param e the event.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
         if(mouseSwitchOff){  // xing
            return;
        }
        //System.out.println("mouse moved");
        Graphics2D g2 = (Graphics2D) getGraphics();
        if (this.getHorizontalAxisTrace()) {
            try {
                Method drawHorizontalAxisTraceFunction = this.getClass().getDeclaredMethod("drawHorizontalAxisTrace", Graphics2D.class, int.class);
                drawHorizontalAxisTraceFunction.invoke(this, g2, e.getX());
                //drawHorizontalAxisTrace(g2, e.getX());
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVerticalAxisTrace()) {
            try {
                Method drawVerticalAxisTraceFunction = this.getClass().getDeclaredMethod("drawVerticalAxisTrace", Graphics2D.class, int.class);
                drawVerticalAxisTraceFunction.invoke(this, g2, e.getY());
                //drawVerticalAxisTrace(g2, e.getY());
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        g2.dispose();

        Object[] listeners = this.getListeners(
                ChartMouseListener.class);
        if (listeners.length == 0) {
            return;
        }
        Insets insets = getInsets();
        int x = (int) ((e.getX() - insets.left) / this.getScaleX());
        int y = (int) ((e.getY() - insets.top) / this.getScaleY());

        ChartEntity entity = null;
        if (this.getChartRenderingInfo() != null) {
            EntityCollection entities = this.getChartRenderingInfo().getEntityCollection();
            if (entities != null) {
                entity = entities.getEntity(x, y);
            }
        }

        // we can only generate events if the panel's chart is not null
        // (see bug report 1556951)
        if (this.getChart() != null) {
            ChartMouseEvent event = new ChartMouseEvent(getChart(), e, entity);
            for (int i = listeners.length - 1; i >= 0; i -= 1) {
                ((ChartMouseListener) listeners[i]).chartMouseMoved(event);
            }
        }

    }
    
    /**
     * Handles a 'mouse pressed' event.
     * <P>
     * This event is the popup trigger on Unix/Linux.  For Windows, the popup
     * trigger is the 'mouse released' event.
     *
     * @param e  The mouse event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
         if(mouseSwitchOff){  // xing
            return;
        }
        //System.out.println("mouse pressed");
        if (this.getChart() == null) {
            return;
        }
        //Plot plot = this.getChart().getPlot();
        XYPlot plot = (XYPlot)this.getChart().getPlot();
        int mods = e.getModifiers();
        //System.out.println("mousePressed-- mods: " + mods);
//        GestureRecognizer.getGestureRecognitizer().clear();
//        GestureRecognizer.getGestureRecognitizer().addGesturePoint(e, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);
//        
        if ((mods & this.getPanMask()) == this.getPanMask()) {
            //System.out.println("mousePressd-- panmask");
            // can we pan this plot?
            if (plot instanceof Pannable) {
                Pannable pannable = (Pannable) plot;
                if (pannable.isDomainPannable() || pannable.isRangePannable()) {
                    //System.out.println("domainPannable or rangePannable");
                    Rectangle2D screenDataArea = getScreenDataArea(e.getX(),
                            e.getY());
                    if (screenDataArea != null && screenDataArea.contains(
                            e.getPoint())) {
                        //this.panW = screenDataArea.getWidth();
                        this.setPanW(screenDataArea.getWidth());
                        //this.panH = screenDataArea.getHeight();
                        this.setPanH(screenDataArea.getHeight());
                        //this.panLast = e.getPoint();
                        this.setPanLast(e.getPoint());
                        setCursor(Cursor.getPredefinedCursor(
                                Cursor.MOVE_CURSOR));
                    }
                }
                // the actual panning occurs later in the mouseDragged() 
                // method
            }
        }
        else if (this.getZoomRectangle() == null) {
            //System.out.println("panmask else");
            Rectangle2D screenDataArea = getScreenDataArea(e.getX(), e.getY());
            if (screenDataArea != null) {
//               this.zoomPoint = getPointInRectangle(e.getX(), e.getY(),
//                        screenDataArea);
                try {
                    Method getPointInRectangleFunction = ChartPanel.class.getDeclaredMethod("getPointInRectangle", int.class, int.class, Rectangle2D.class);
                    getPointInRectangleFunction.setAccessible(true);
                     this.setZoomPoint((Point2D)getPointInRectangleFunction.invoke(this, e.getX(), e.getY(), screenDataArea));
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
//                this.setZoomPoint(getPointInRectangle(e.getX(), e.getY(),
//                        screenDataArea));
            }
            else {
                //this.zoomPoint = null;
                this.setZoomPoint(null);
            }
            if (e.isPopupTrigger()) {
                if (this.getPopupMenu() != null) {
                    displayPopupMenu(e.getX(), e.getY());
                }
            }
        } 
    }

   
    public void panStart(int x, int y){              // xing
        XYPlot plot = (XYPlot) this.getChart().getPlot();
        System.out.println("mousePressd-- panmask");
        // can we pan this plot?
        if (plot instanceof Pannable) {
            Pannable pannable = (Pannable) plot;
            if (pannable.isDomainPannable() || pannable.isRangePannable()) {
                //System.out.println("domainPannable or rangePannable");
                Rectangle2D screenDataArea = getScreenDataArea(x,
                        y);
                if (screenDataArea != null && screenDataArea.contains(
                        new Point(x, y))) {
                    //this.panW = screenDataArea.getWidth();
                    this.setPanW(screenDataArea.getWidth());
                    //this.panH = screenDataArea.getHeight();
                    this.setPanH(screenDataArea.getHeight());
                    //this.panLast = e.getPoint();
                    this.setPanLast(new Point(x, y));
                    setCursor(Cursor.getPredefinedCursor(
                            Cursor.MOVE_CURSOR));
                }
            }
                
        }
    }
    
    
     public void draggedPanningHandler(int x, int y){      // xing
        if (this.getPanLast() != null) {
            double dx = x - this.getPanLast().getX();
            double dy = y - this.getPanLast().getY();
            if (dx == 0.0 && dy == 0.0) {
                return;
            }
            double wPercent = -dx / this.getPanW();
            double hPercent = dy / this.getPanH();
            boolean old = this.getChart().getPlot().isNotify();
            this.getChart().getPlot().setNotify(false);
            Pannable p = (Pannable) this.getChart().getPlot();
            //System.out.println("panning with start point-- position(" + e.getX() + ", " + e.getY() + "), panLast(" + this.getPanLast().x + ", " + this.getPanLast().y + "), panW and panH(" + this.getPanW() + ", " + this.getPanH() + "), orientation(" + this.getOrientation().toString()+")" );
            if (p.getOrientation() == PlotOrientation.VERTICAL) {
                //System.out.println("VERTICAL");
                p.panDomainAxes(wPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
                p.panRangeAxes(hPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
            }
            else {
                //System.out.println("HORIZONTAL");
                p.panDomainAxes(hPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
                p.panRangeAxes(wPercent, this.getChartRenderingInfo().getPlotInfo(),
                        this.getPanLast());
            }
            
            //this.panLast = e.getPoint();
            this.setPanLast(new Point(x, y));
            this.getChart().getPlot().setNotify(old);
           
        }
    }
    
    public void panning(int x, int y){                              //xing
         if (this.getPanLast() != null) {
            this.draggedPanningHandler(x, y);
        }
    }

    public void panFinished(){
         if (this.getPanLast() != null) {
            this.setPanLast(null);
           
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    
    public class ZoomHandler{
        /** The chart panel. */
    private ChartPanel chartPanel;

    /** The zoom factor. */
    double zoomFactor;

    /**
     * Creates a new instance for the specified chart panel.
     *
     * @param chartPanel  the chart panel (<code>null</code> not permitted).
     */
    public ZoomHandler(ChartPanel chartPanel) {
        this.chartPanel = chartPanel;
        //this.zoomFactor = 0.10;
        this.zoomFactor = 0.010;
        //this.chartPanel.addMouseWheelListener(this);
    }

    /**
     * Returns the current zoom factor.  The default value is 0.10 (ten
     * percent).
     *
     * @return The zoom factor.
     *
     * @see #setZoomFactor(double)
     */
    public double getZoomFactor() {
        return this.zoomFactor;
    }

    /**
     * Sets the zoom factor.
     *
     * @param zoomFactor  the zoom factor.
     *
     * @see #getZoomFactor()
     */
    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }

    /**
     * Handles a mouse wheel event from the underlying chart panel.
     *
     * @param e  the event.
     */
    
    // in = 1, out = -1
    public void pinchMoved(Point p, int in) {
        JFreeChart chart = this.chartPanel.getChart();
        if (chart == null) {
            return;
        }
        Plot plot = chart.getPlot();
        if (plot instanceof Zoomable) {
            Zoomable zoomable = (Zoomable) plot;
            handleZoomable(zoomable, p, in);
        }
        else if (plot instanceof PiePlot) {
            PiePlot pp = (PiePlot) plot;
            //pp.handleMouseWheelRotation(e.getWheelRotation());
            pp.handleMouseWheelRotation(1);
        }
    }

    /**
     * Handle the case where a plot implements the {@link Zoomable} interface.
     *
     * @param zoomable  the zoomable plot.
     * @param e  the mouse wheel event.
     */
    //private void handleZoomable(Zoomable zoomable, MouseWheelEvent e) {
    private void handleZoomable(Zoomable zoomable, Point centerPoint, int in) {     //xing
        // don't zoom unless the mouse pointer is in the plot's data area
        ChartRenderingInfo info = this.chartPanel.getChartRenderingInfo();
        PlotRenderingInfo pinfo = info.getPlotInfo();
        Point2D p = this.chartPanel.translateScreenToJava2D(centerPoint);
        if (!pinfo.getDataArea().contains(p)) {
            return;
        }

        Plot plot = (Plot) zoomable;
        // do not notify while zooming each axis
        boolean notifyState = plot.isNotify();
        plot.setNotify(false);
        int clicks = in;
        double zf = 1.0 + this.zoomFactor;
        if (clicks < 0) {
            zf = 1.0 / zf;
        }
        if (chartPanel.isDomainZoomable()) {
            zoomable.zoomDomainAxes(zf, pinfo, p, true);
        }
        if (chartPanel.isRangeZoomable()) {
            zoomable.zoomRangeAxes(zf, pinfo, p, true);
        }
        plot.setNotify(notifyState);  // this generates the change event too
    }

    }
    
    
//    public void handleSelectOrRestore(javafx.scene.input.MouseEvent e){
//        Graphics2D g2 = (Graphics2D) getGraphics();
//        
//        
//        this.draggedSetUpZoomRectangleJFX(g2, e);
//    }
//    
    
//    private void draggedSetUpZoomRectangleJFX(Graphics2D g2, javafx.scene.input.MouseEvent e){
//        boolean hZoom, vZoom;
//        
//        //if (this.orientation == PlotOrientation.HORIZONTAL) {
//        if (this.getOrientation().equals(PlotOrientation.HORIZONTAL)) {
//            hZoom = this.isRangeZoomable();
//            vZoom = this.isDomainZoomable();
//        } else {
//            hZoom = this.isDomainZoomable();
//            vZoom = this.isRangeZoomable();
//        }
//
//        //if ((e.getModifiers() & this.gestureMask) == this.gestureMask) {
//        if (e.isShiftDown()) {
//            // set dragged path
//            if (path.isEmpty()) {
//                path.add(new Point2D.Double(e.getX(), e.getY()));
//
//            } else {
//                path.add(new Point2D.Double(e.getX(), e.getY()));
//            }
//            if (path.size() > 1) {
//                gPath = new GeneralPath(GeneralPath.WIND_EVEN_ODD, path.size());
//                gPath.moveTo(path.get(0).getX(), path.get(0).getY());
//
//                for (int index = 1; index < path.size(); index++) {
//                    gPath.lineTo(path.get(index).getX(), path.get(index).getY());
//                    //System.out.println("draw path: " + index );
//                }
//                g2.setPaint(Color.red);
//                g2.draw(gPath);
//                this.repaint();
//            }
//        } else {
//            if (true) {
//                Rectangle2D scaledDataArea = getScreenDataArea(
//                        (int) this.getZoomPoint().getX(), (int) this.getZoomPoint().getY());
//                if (hZoom && vZoom) {
//                    // selected rectangle shouldn't extend outside the data area...
//                    double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
//                    double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
//                    this.setZoomRectangle(new Rectangle2D.Double(
//                            this.getZoomPoint().getX(), this.getZoomPoint().getY(),
//                            xmax - this.getZoomPoint().getX(), ymax - this.getZoomPoint().getY()));
//                } else if (hZoom) {
//                    double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
//                    this.setZoomRectangle(new Rectangle2D.Double(
//                            this.getZoomPoint().getX(), scaledDataArea.getMinY(),
//                            xmax - this.getZoomPoint().getX(), scaledDataArea.getHeight()));
//                } else if (vZoom) {
//                    double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
//                    this.setZoomRectangle(new Rectangle2D.Double(
//                            scaledDataArea.getMinX(), this.getZoomPoint().getY(),
//                            scaledDataArea.getWidth(), ymax - this.getZoomPoint().getY()));
//                }
//
//                // Draw the new zoom rectangle...
////        if (this.useBuffer) {
//                if (this.getUseBuffer()) {
//                    //System.out.println("draw rectangle -- use Buffer");
//                    repaint();
//                } else {
//                    //System.out.println("draw rectangle-- no UseBuffer");
//            // with no buffer, we use XOR to draw the rectangle "over" the
//                    // chart...
//                    try {
//                        Method drawZoomRectangleFunction = this.getClass().getDeclaredMethod("drawZoomRectangle", Graphics2D.class, boolean.class);
//                        drawZoomRectangleFunction.invoke(this, g2, true);
//                    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//                        Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    //drawZoomRectangle(g2, true);
//                }
//            }
//        }
//       
//    }
//    
    
    
//    public void mousePressedJFX(javafx.scene.input.MouseEvent e) {
//         if(mouseSwitchOff){  // xing
//            return;
//        }
//        //System.out.println("mouse pressed");
//        if (this.getChart() == null) {
//            return;
//        }
//        //Plot plot = this.getChart().getPlot();
//        XYPlot plot = (XYPlot)this.getChart().getPlot();
//        //int mods = e.getModifiers();
//        //System.out.println("mousePressed-- mods: " + mods);
////        GestureRecognizer.getGestureRecognitizer().clear();
////        GestureRecognizer.getGestureRecognitizer().addGesturePoint(e, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);
////        
//        //if ((mods & this.getPanMask()) == this.getPanMask()) {
//        if (e.isControlDown()) {
//            System.out.println("mousePressd-- panmask");
//            // can we pan this plot?
//            if (plot instanceof Pannable) {
//                Pannable pannable = (Pannable) plot;
//                if (pannable.isDomainPannable() || pannable.isRangePannable()) {
//                    //System.out.println("domainPannable or rangePannable");
//                    Rectangle2D screenDataArea = getScreenDataArea((int)e.getX(),
//                            (int)e.getY());
//                    if (screenDataArea != null && screenDataArea.contains(
//                            new Point((int)e.getSceneX(), (int)e.getSceneY()))) {
//                        //this.panW = screenDataArea.getWidth();
//                        this.setPanW(screenDataArea.getWidth());
//                        //this.panH = screenDataArea.getHeight();
//                        this.setPanH(screenDataArea.getHeight());
//                        //this.panLast = e.getPoint();
//                        this.setPanLast(new Point((int)e.getSceneX(), (int)e.getSceneY()));
//                        setCursor(Cursor.getPredefinedCursor(
//                                Cursor.MOVE_CURSOR));
//                    }
//                }
//                // the actual panning occurs later in the mouseDragged() 
//                // method
//            }
//        }
//        else if (this.getZoomRectangle() == null) {
//            System.out.println("panmask else");
//            Rectangle2D screenDataArea = getScreenDataArea((int)e.getX(), (int)e.getY());
//            if (screenDataArea != null) {
////               this.zoomPoint = getPointInRectangle(e.getX(), e.getY(),
////                        screenDataArea);
//                try {
//                    Method getPointInRectangleFunction = ChartPanel.class.getDeclaredMethod("getPointInRectangle", int.class, int.class, Rectangle2D.class);
//                    getPointInRectangleFunction.setAccessible(true);
//                     this.setZoomPoint((Point2D)getPointInRectangleFunction.invoke(this, e.getX(), e.getY(), screenDataArea));
//                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//                    Logger.getLogger(MyChartPanel.class.getName()).log(Level.SEVERE, null, ex);
//                }
////                this.setZoomPoint(getPointInRectangle(e.getX(), e.getY(),
////                        screenDataArea));
//            }
//            else {
//                //this.zoomPoint = null;
//                this.setZoomPoint(null);
//            }
//            if (e.isPopupTrigger()) {
//                if (this.getPopupMenu() != null) {
//                    displayPopupMenu((int)e.getX(), (int)e.getY());
//                }
//            }
//        } 
//    }
    
}
