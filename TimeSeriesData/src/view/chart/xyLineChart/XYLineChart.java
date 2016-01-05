/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.xyLineChart;

import view.chart.sharedComponents.MyJFreeChartXYLineAndShapeRenderer;
import view.chart.sharedComponents.MyJFreeChartNumberAxis;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import model.Metadata;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import model.GlobalData;
import model.SeriesData;
import model.dataProperty.DataProperty;
import model.timeProperty.TimeGranularity;
import model.timeProperty.TimeProperty;
import view.AbstractChart;
import view.chart.sharedComponents.MyChartPanel;


/**
 *
 * @author Administrator
 */
public class XYLineChart extends AbstractChart {
    
    JFreeChart chart;
    ChartPanel chartPanel;
    XYSeriesCollection dataset = new XYSeriesCollection();
    TimeProperty timeProperty ;
    TimeGranularity granularity;
    
    DataProperty dataProperty; 
    String dataDimensionality;
    XYPlot plot;
    MyJFreeChartNumberAxis xAxis;
    MyJFreeChartNumberAxis yAxis;
    

    public XYLineChart(){
        
        
        
        
        chart = createChart("XYLineChart", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, true);
        
        
        
        // ini plot, xAxis, yAxis
        plot = chart.getXYPlot();
        xAxis = (MyJFreeChartNumberAxis) plot.getDomainAxis();
        yAxis = (MyJFreeChartNumberAxis) plot.getRangeAxis();
        
        xAxis.setAutoRangeIncludesZero(false);
        yAxis.setAutoRangeIncludesZero(false);
        //xAxis.setTickLabelFont(null);
        
        // set ticklabel to vertical 
        //xAxis.setVerticalTickLabels(true);
        
        //xAxis.setTickAngle(45);
//        xAxis.setFixedDimension(100);
//        System.out.println("FixedDimension: " + xAxis.getFixedDimension());
        //Set pannable
        this.setPannable();
      
       //Set Visiable of the Base Shape
       this.getRenderer().setBaseShapesVisible(true);
       
       //Set The Plot Line to Dotted Line
       //this.setBasePlotLineToDotted(true);
       
       //Set curData corresponding shape to circle
       this.setBaseShapetoCircle();
       
       //Set the fill paint of curData point
       //this.setBaseShapeFilledPaint(Color.YELLOW);
       
       //set the visiable of curData point outliner
       this.setSeriesShapeOutlineStrokeVisiable(0,false);
       
       
        chartPanel = new MyChartPanel(chart);
        chartPanel.setPopupMenu(null);
        chartPanel.setRefreshBuffer(true);
        //chartPanel.setMouseZoomable(false);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDoubleBuffered(false); 
        this.setContent(chartPanel);
        this.setManaged(true);
    }
    
    private JFreeChart createChart(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {

        ParamChecks.nullNotPermitted(orientation, "orientation");
        MyJFreeChartNumberAxis xAxis = new MyJFreeChartNumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        MyJFreeChartNumberAxis yAxis = new MyJFreeChartNumberAxis(yAxisLabel);
        XYItemRenderer renderer = new MyJFreeChartXYLineAndShapeRenderer(true, false);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);

        return chart;

    }
    
    private MyJFreeChartNumberAxis getRangeAxis(){
        return (MyJFreeChartNumberAxis) this.getPlot().getRangeAxis();
    }
    
    private XYPlot getPlot(){
        XYPlot plot = this.chart.getXYPlot();
        return plot;
    }
    
    private void setPannable(){
       XYPlot plot = (XYPlot)chart.getXYPlot();
       plot.setDomainPannable(true);
       plot.setRangePannable(true);
    }
    
    private void setBaseShapetoCircle(){
       this.getRenderer().setBaseShape(new Ellipse2D.Double(-3,-3,6,6));
       this.getRenderer().setAutoPopulateSeriesShape(false);
    }
    private void setSeriesShapeOutlineStrokeVisiable(int series,boolean visiable){
        if(!visiable){
            this.getRenderer().setSeriesOutlineStroke(series, new BasicStroke(0));
            
        }else{
            this.getRenderer().setSeriesOutlineStroke(series, new BasicStroke(1.0f));
        }
        this.getRenderer().setAutoPopulateSeriesStroke(false);
    }
    //Set shape filled paint
    private void setBaseShapeFilledPaint(Paint filledPaint){
       this.getRenderer().setUseFillPaint(true);
       this.getRenderer().setBaseFillPaint(filledPaint);
    }
    
    private MyJFreeChartXYLineAndShapeRenderer getRenderer(){
        XYPlot plot = this.chart.getXYPlot();
        MyJFreeChartXYLineAndShapeRenderer renderer = (MyJFreeChartXYLineAndShapeRenderer)plot.getRenderer();
        return renderer;
    }
    
    private void setBasePlotLineToDotted(boolean dottedLine){
       
       MyJFreeChartXYLineAndShapeRenderer renderer = this.getRenderer();
       BasicStroke oldStroke = (BasicStroke)renderer.getBaseStroke();
       float[] dash = {10.0f, 10.0f};
       BasicStroke newStroke = new BasicStroke(oldStroke.getLineWidth(), oldStroke.getEndCap(), oldStroke.getLineJoin(),
       oldStroke.getMiterLimit(),dash,oldStroke.getDashPhase());
       renderer.setAutoPopulateSeriesStroke(false);
       renderer.setBaseStroke(newStroke);
       
    }

    @Override
    public void showData(GlobalData tsData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showData(GlobalData globalData, Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showExpectionOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showGlobalMaxValueOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showGlobalMinValueOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showLocalMinValueOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showLocalMaxValueOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata hightLightData(Metadata tsData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
//    @Override
//    public Object getEventSource() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public int getSeriesIndex(double x, double y) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int getItemIndex(double x, double y) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean isInDataArea(double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setBasicData(GlobalData tsData){
        ArrayList<String> seriesNames = tsData.getSeriesNames();
        for(String seriesName: seriesNames){
            SeriesData seriesData = tsData.getSeriesData(seriesName);
            XYSeries curTimeSeries = new XYSeries(seriesName);
            this.dataset.addSeries(curTimeSeries);

        }
    }
    
    @Override
    public Metadata showDistributionOfSeries(Metadata metadata) {
        this.chart.setTitle("Distribution for Series");
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName:seriesNames){
            XYSeries curSeries = new XYSeries(seriesName);
            int valueSize = metadata.getValuesSize(seriesName);
            for(int i= 0; i < valueSize; i++){
                curSeries.add(Double.valueOf(metadata.getValueKey(seriesName, i)), Integer.valueOf(metadata.getValue(seriesName, i)));
            }
            this.dataset.addSeries(curSeries);
        }
        this.xAxis.setLabel("Value");
        this.yAxis.setLabel("Count");
        return metadata;
    }

//    @Override
//    public String getY(double localYPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public Metadata showValueAboveOfSeries(Metadata metadata, String comparedValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showValueBelowOfSeries(Metadata metadata, String comparedValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showValueBetweenOfSeries(Metadata metadata, String smallerValue, String biggerValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public String getValue(Point2D localPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String getTime(Point2D localPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String getSeriesKey(Point2D localPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void addHightlightTimeSeriesData(String seriesKey, String value, String time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearHightlightTimeSeriesData(String seriesKey, String value, String time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearAllHightlightTimeSeriesData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public Metadata getHightlightData() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean timeSelected(Point2D p2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public boolean valueSelected(Point2D p2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

  

//    @Override
//    public String getX(double localXPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean isIntersectionWithItemData(Point2D p2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public String getItemDataTime(Point2D p2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String getItemDataValue(Point2D p2) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String getYValue(Point2D localPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public String getXValue(Point2D localPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public String getIntersectedItemDataTime(Point2D localPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getIntersectedItemDataValue(Point2D localPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIntersectedWithYAxisTick(Point2D localPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIntersectedWithXAxisTick(Point2D localPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNearestYValue(Point2D localPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNearestXValue(Point2D localPosition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GlobalData getDataRange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showTrendencyOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showDataAfter(Metadata metadata, String comparedTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showDataBefore(Metadata metadata, String comparedTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showDataBetween(Metadata metadata, String smallTime, String biggerTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
