/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.timeSeriesDataChart;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import model.Metadata;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.time.Day;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;
import model.SeriesData;
import model.ItemData;
import model.TimeFormat;
import model.GlobalData;
import model.datafile.FileInformation;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeriesDataItem;
import view.AbstractChart;
import view.chart.sharedComponents.MyChartPanel;
import view.chart.sharedComponents.MyJFreeChartDateAxis;
import view.chart.sharedComponents.MyJFreeChartNumberAxis;
import view.chart.sharedComponents.MyJFreeChartXYLineAndShapeRenderer;


/**
 *
 * @author Administrator
 */
public class TimeSeriesDataChart extends AbstractChart{

    private JFreeChart chart;
    private MyChartPanel chartPanel;
    private final TimeSeriesCollection dataset = new TimeSeriesCollection();
    //private Shape customisedShape = new Ellipse2D.Double(-3,-3,6,6);
    
    public TimeSeriesDataChart() {
        this.iniChart();
        this.iniChartPanel();
        this.setOnEventHandler();
    }

    private JFreeChart creatChart(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls){
        ParamChecks.nullNotPermitted(orientation, "orientation");
        //System.out.println("create basic view");
        MyJFreeChartDateAxis xAxis = new MyJFreeChartDateAxis(xAxisLabel);
        MyJFreeChartNumberAxis yAxis = new MyJFreeChartNumberAxis(yAxisLabel);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        MyJFreeChartXYLineAndShapeRenderer renderer = new MyJFreeChartXYLineAndShapeRenderer();
//        renderer.setBaseShape(null);
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        //MyJFreeChartXYPlot plot = new MyJFreeChartXYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        JFreeChart aChart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return aChart;

    }
    
    private void iniChart(){
        chart = this.creatChart(
                "Time Series Data Chart", // title
                "time", // x-axis label
                "value", // y-axis label
                this.dataset, // curData
                PlotOrientation.VERTICAL,
                true, // create legend?
                true, // generate tooltips?
                false // generate URLs?
        );
       
       //Set pannable
        this.setPannable(true);
  
       //Set Visiable of the Base Shape
       //this.getRenderer().setBaseShapesVisible(true);
       
       //Set The Plot Line to Dotted Line
       //this.setBasePlotLineToDotted(true);
       
       //Set curData corresponding shape to circle
       //this.setBaseShapetoCircle();
       
       //Set the fill paint of curData point
       //this.setBaseShapeFilledPaint(Color.YELLOW);
       
       //set the visiable of curData point outliner
       //this.setSeriesShapeOutlineStrokeVisiable(0,false);
       
    }
    
    private MyJFreeChartNumberAxis getRangeAxis(){
        return  (MyJFreeChartNumberAxis) this.getPlot().getRangeAxis();
    } 
    
    private MyJFreeChartDateAxis getDomainAxis(){
        return (MyJFreeChartDateAxis) this.getPlot().getDomainAxis();
    }
    
    private XYPlot getPlot(){
       XYPlot plot =  this.chart.getXYPlot();
        return plot;
    }
    
    private void setPannable(boolean pannable){
        XYPlot plot = (XYPlot) chart.getXYPlot();
        plot.setDomainPannable(pannable);
        plot.setRangePannable(pannable);
    }
    
    
    public void setBaseShapetoCircle(boolean flag){
       if(flag){
           this.getRenderer().setBaseShape(new Ellipse2D.Double(-3,-3,6,6));
           this.getRenderer().setAutoPopulateSeriesShape(false);
           
       }else{
           this.getRenderer().setAutoPopulateSeriesShape(true);
       }
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
        XYPlot plot =  this.chart.getXYPlot();
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
    
    private void iniChartPanel(){
        chartPanel = new MyChartPanel(chart);
        chartPanel.setPopupMenu(null);
        chartPanel.setRefreshBuffer(true);
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setDoubleBuffered(false); 
        this.setContent(chartPanel);
        this.setManaged(true);
    }
    
    @Override
    public void showData(GlobalData tsData){
        this.setBasicData(tsData);
        this.setBaseShapetoCircle(true);
        
    }
 
    private void setBasicData(GlobalData tsData){
        ArrayList<String> seriesNames = tsData.getSeriesNames();
        for(String seriesName: seriesNames){
            SeriesData seriesData = tsData.getSeriesData(seriesName);
            TimeSeries curTimeSeries = this.toTimeSeries(seriesData);
            this.dataset.addSeries(curTimeSeries);
//            int seriesIndex = this.dataset.getSeriesIndex(curTimeSeries.getKey());
//            this.getRenderer().setSeriesShape(seriesIndex, this.customisedShape);
        }
    }
    
    
    
    private RegularTimePeriod toRegularTimePeriod(String textTime, TimeFormat timeFormat){
        String[] timeComponents = textTime.split("-");
        RegularTimePeriod rtp = null;
        if(timeFormat.getGranularity().equals(TimeFormat.DAY)){
            if(timeFormat.containsYear() && timeFormat.containsMonth() && timeFormat.containsDay()){
                rtp = new Day(Integer.valueOf(timeComponents[2]), Integer.valueOf(timeComponents[1]), Integer.valueOf(timeComponents[0]));
            }
        }else if(timeFormat.getGranularity().equals(TimeFormat.YEAR)){
            if(timeFormat.containsYear()){
                rtp = new Year(Integer.valueOf(timeComponents[0]));
            }
        }else if(timeFormat.getGranularity().equals(TimeFormat.SECOND)){
           if(timeFormat.containsYear() && timeFormat.containsMonth() && timeFormat.containsDay() && timeFormat.containsHour() && timeFormat.containsMinute() && timeFormat.containsSecond()){
               rtp = new Second(Integer.valueOf(timeComponents[5]), Integer.valueOf(timeComponents[4]), Integer.valueOf(timeComponents[3]), Integer.valueOf(timeComponents[2]), Integer.valueOf(timeComponents[1]), Integer.valueOf(timeComponents[0]));
           }
        }else if(timeFormat.getGranularity().equals(TimeFormat.MILLISECOND)){
            if(timeFormat.containsYear() && timeFormat.containsMonth() && timeFormat.containsDay() && timeFormat.containsHour() && timeFormat.containsMinute() && timeFormat.containsSecond() && timeFormat.containsMillisecond()){
               rtp = new Millisecond(Integer.valueOf(timeComponents[6]),Integer.valueOf(timeComponents[5]), Integer.valueOf(timeComponents[4]), Integer.valueOf(timeComponents[3]), Integer.valueOf(timeComponents[2]), Integer.valueOf(timeComponents[1]), Integer.valueOf(timeComponents[0]));
           }
        }
        return rtp;
    }
    
    private TimeSeries toTimeSeries(SeriesData seriesData){
        TimeSeries timeSeries = new TimeSeries(seriesData.getSeriesKey());
        ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
        for(ItemData singleData: seriesDataInArrayList){
            timeSeries.add(this.toRegularTimePeriod(singleData.getTime(), singleData.getTimeFormat()), Double.valueOf(singleData.getValue()));
        }
        return timeSeries;
    }
    
    private void setSeriesShape(int seriesIndex, Shape shape){
        this.getRenderer().setSeriesShape(seriesIndex, shape);
    }

    private void setSeriesShape(TimeSeries timeSeries, Shape shape){
        //this.getRenderer().setBaseShape(null);
        int seriesIndex = this.dataset.getSeriesIndex(timeSeries.getKey());
        this.getRenderer().setSeriesShape(seriesIndex, shape);
    }
    
    @Override
    public Metadata showGlobalMaxValueOfSeries(Metadata metadata) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries(seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat().equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                }
            }
            this.dataset.addSeries(timeSeries);
//            int seriesIndex = this.dataset.getSeriesIndex(timeSeries.getKey());
//            this.getRenderer().setSeriesShape(seriesSize, customisedShape);
//            this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Global Maximum Chart");
        return metadata;
    }

    @Override
    public Metadata showGlobalMinValueOfSeries(Metadata metadata) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat().equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Global Minimum Chart");
        return metadata;
    }
    
    @Override
    public Metadata showExpectionOfSeries(Metadata metadata) {

       ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
       for(String seriesName:seriesNames){
           TimeSeries timeSeries = new TimeSeries(seriesName);
           String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
           TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
           String value = metadata.getValue(seriesName, 0);
           
           if(seriesValueFormat.equals("double")){
               SeriesData seriesData = metadata.getRawData().getSeriesData(seriesName);
               ArrayList<ItemData> seriesDataInArrayList = seriesData.getDataInArrayList();
               for(ItemData itemData:seriesDataInArrayList){
                   timeSeries.add(this.toRegularTimePeriod(itemData.getTime(), seriesTimeFormat), Double.valueOf(value));
               }
           }
           //this.addSeriesWithPointShape(timeSeries, customisedShape);
           this.dataset.addSeries(timeSeries);
//           int seriesIndex = this.dataset.getSeriesIndex(timeSeries.getKey());
//           this.getRenderer().setSeriesShape(seriesIndex, this.customisedShape);
       }
       //this.getRenderer().setBaseShapesVisible(false);
       this.setBaseShapetoCircle(true);
       this.setChartTitle("Expectation Chart");
       return metadata;
    }
    @Override
    public Metadata showLocalMinValueOfSeries(Metadata metadata) {
        
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Local Minimum Chart");
        return metadata;
    }

    @Override
    public Metadata showLocalMaxValueOfSeries(Metadata metadata) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Local Maximum Chart");
        return metadata;
    }

    public String getChartTitle() {
        return this.chart.getTitle().toString();
    }

    public void setChartTitle(String name) {
        this.chart.setTitle(name);
    }

    
    public void removeAllSeries() {
        this.dataset.removeAllSeries();
    }

    @Override
    public Metadata hightLightData(Metadata metadata) {
        this.getRenderer().clearAllHightlightData();
        ArrayList<String> seriesNames = metadata.getValuesKeys();
        this.getRenderer().setHightlight(true);
        //System.out.println("Hightlight Data Start -----------------------");
        for(String seriesName: seriesNames){
            int seriesIndex = this.dataset.getSeriesIndex(seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                //System.out.println(seriesName + "  " + metadata.getValueKey(seriesName, i) + "  " + metadata.getValue(seriesName, i));
                RegularTimePeriod rtp = this.toRegularTimePeriod(metadata.getValueKey(seriesName, i), seriesTimeFormat);
                int itemIndex = this.dataset.getSeries(seriesName).getIndex(rtp);
                this.getRenderer().addHightlightDataAndPaint(seriesIndex, itemIndex, true);
            }
        }
        //System.out.println("Hightlight Data End -----------------------");
        //System.out.println("----------------------------------------------------------------------------");
        return metadata;
    }

//    @Override
//    public double getX(int series, int item) {
//        return this.dataset.getXValue(series, item);
//        
//    }
//
//    @Override
//    public double getY(int series, int item) {
//        return this.dataset.getYValue(series, item);
//    }
    
    public String getY(Point2D localPoint){
        //该函数应该是错的
        //return String.valueOf(this.getRangeAxis().getYValue((float)localYPosition));
        this.getRangeAxis().getTickValue(localPoint);
        return null;
    }

//    @Override
//    public Object getEventSource() {
//        return this.chartPanel;
//    }   

    
    public int getSeriesIndex(double localX, double localY) {
        int seriesCount = this.dataset.getSeriesCount();
        for(int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++){
            TimeSeries curSeries = this.dataset.getSeries(seriesIndex);
            int itemCount = curSeries.getItemCount();
            double xStep = 0.0;
            double yStep = 0.0;
            
            
            for(int itemIndex = 0; itemIndex < itemCount; itemIndex++){
                Shape itemShape = this.getItemShape(seriesIndex, itemIndex);
                Rectangle2D rec = itemShape.getBounds2D();
                if(rec.contains(localX, localY)){
                    return seriesIndex;
                }
            }
        }
        return -1;
    }

   
    private int getItemIndex(double localX, double localY) {
         int seriesCount = this.dataset.getSeriesCount();
        for(int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++){
            TimeSeries curSeries = this.dataset.getSeries(seriesIndex);
            int itemCount = curSeries.getItemCount();
            
            for(int itemIndex = 0; itemIndex < itemCount; itemIndex++){
                Shape itemShape = this.getItemShape(seriesIndex, itemIndex);
                Rectangle2D rec = itemShape.getBounds2D();
                if(rec.contains(localX, localY)){
                    return itemIndex;
                }
            }
        }
        return -1;
    }

    public Shape getItemShape(int seriesIndex, int itemIndex){
        //Point2D p2 = this.getRenderer().getItemShape(dataset, this.getPlot(), seriesIndex, itemIndex, this.getDomainAxis(), this.getRangeAxis());
        LinkedHashMap<Integer, LinkedHashMap<Integer, Shape>> map = this.getRenderer().getAllItemShapes();
        if(!map.containsKey(seriesIndex)){
            return null;
        }else{
            if(!map.get(seriesIndex).containsKey(itemIndex)){
                return null;
            }else{
                Shape shape = map.get(seriesIndex).get(itemIndex);
                return shape;
            }
        }
        
    }
    
    @Override
    public boolean isInDataArea(double x, double y) {
        boolean greaterOrEqualThanMinX = false;
        boolean greaterOrEqualThanMinY = false;
        boolean smallerOrEqualThanMaxX = false;
        boolean smallerOrEqualThanMaxY = false;
        for(int seriesIndex = 0; seriesIndex < this.dataset.getSeriesCount(); seriesIndex++){
           for(int itemIndex = 0; itemIndex < this.dataset.getItemCount(seriesIndex); itemIndex++){
               Shape itemShape = this.getItemShape(seriesIndex, itemIndex);
               //Point2D position = this.getItemShape(seriesIndex, itemIndex);//this.getRenderer().getItemShape(dataset, this.getPlot(), seriesIndex, itemIndex, this.getDomainAxis(), this.getRangeAxis());
               if(x > (itemShape.getBounds2D().getMinX() - 5)){
                   greaterOrEqualThanMinX = true;
               }
               if(y > (itemShape.getBounds2D().getMinY() - 5)){
                   greaterOrEqualThanMinY = true;
               }
               if(x < (itemShape.getBounds2D().getMaxX() + 5)){
                   smallerOrEqualThanMaxX = true;
               }
               if(y < (itemShape.getBounds2D().getMaxY() + 5)){
                   smallerOrEqualThanMaxY = true;
               }
               if(greaterOrEqualThanMinX && greaterOrEqualThanMinY && smallerOrEqualThanMaxX && smallerOrEqualThanMaxY){
                   return true;
               }
           }
        }
        return false;
    }

    @Override
    public void showData(GlobalData globalData, Metadata metadata) {
        this.dataset.removeAllSeries();
        this.showData(globalData);
        if(metadata.getOperation().equals(Metadata.EXPECTION_FORSERIES)){
            this.showExpectionOfSeries(metadata);
        }else if(metadata.getOperation().equals(Metadata.GLOBALMAX_FORSERIES)){
            this.showGlobalMaxValueOfSeries(metadata);
        }else if(metadata.getOperation().equals(Metadata.GLOBALMIN_FORSERIES)){
            this.showGlobalMinValueOfSeries(metadata);
        }else if(metadata.getOperation().equals(Metadata.LOCALMAX_FORSERIES)){
            this.showLocalMaxValueOfSeries(metadata);
        }else if(metadata.getOperation().equals(Metadata.LOCALMIN_FORSERIES)){
            this.showLocalMinValueOfSeries(metadata);
        }
    }

    @Override
    public Metadata showDistributionOfSeries(Metadata metadata) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Metadata showValueAboveOfSeries(Metadata metadata, String comparedValue) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Values above(including) " + comparedValue + " Chart");
        return metadata;
    }

    @Override
    public Metadata showValueBelowOfSeries(Metadata metadata, String comparedValue) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Values below(including) " + comparedValue + " Chart");
        return metadata;
    }

    @Override
    public Metadata showValueBetweenOfSeries(Metadata metadata, String smallerValue, String biggerValue) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Values between(including) " + smallerValue + " and " + biggerValue + " Chart");
        return metadata;
    }

//    @Override
//    public String getValue(Point2D localPosition) {
//        int seriesIndex = this.getSeriesIndex(localPosition.getX(), localPosition.getY());
//        int itemIndex = this.getItemIndex(localPosition.getX(), localPosition.getY());
//        if(seriesIndex == -1 || itemIndex == -1){
//            return null;
//        }
//        Number number = this.dataset.getSeries(seriesIndex).getDataItem(itemIndex).getValue();
//        String value = number.toString();
//        return value;
//    }

//    @Override
//    public String getTime(Point2D localPosition) {
//        int seriesIndex = this.getSeriesIndex(localPosition.getX(), localPosition.getY());
//        int itemIndex = this.getItemIndex(localPosition.getX(), localPosition.getY());
//        if(seriesIndex == -1 || itemIndex == -1){
//            return null;
//        }
//        RegularTimePeriod rtp = this.dataset.getSeries(seriesIndex).getDataItem(itemIndex).getPeriod();
//        long millisecond = rtp.getFirstMillisecond();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
//        String time = formatter.format(millisecond);
//        return time;
//    }
//
//    @Override
//    public String getSeriesKey(Point2D localPosition) {
//        return null;
//        
//    }

    @Override
    public void addHightlightTimeSeriesData(String seriesKey, String value, String time) {
        //public void addData(String operation, String value, String valuesKey, String valueKey)
        this.hightlightData.addData(Metadata.HIGHTLIGHT, value, seriesKey, time);
    }

    @Override
    public void clearHightlightTimeSeriesData(String seriesKey, String value, String time) {
        //public void removeData(String value, String valuesKey, String valueKey)
        this.hightlightData.removeData(value, seriesKey, time);
    }

    @Override
    public void clearAllHightlightTimeSeriesData() {
        this.hightlightData.removeAllData();
    }

//    @Override
//    public Metadata getHightlightData() {
//        return this.hightlightData;
//    }
//
//    @Override
//    public boolean timeSelected(Point2D p2) {
//        return this.getDomainAxis().intersectsWithTickLabel(p2);
//    }
//
//    @Override
//    public boolean valueSelected(Point2D p2) {
//        return this.getRangeAxis().intersectsWithTickLabel(p2);
//    }

//    @Override
//    public String getSelectedTickMarkTime(Point2D p2) {
//        if(timeSelected(p2)){
//            double longtime = this.getDomainAxis().getTickValue(p2);
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
//            String time = formatter.format(longtime);
//            return time;
//        }
//        
//        return null;
//    }
//
//    @Override
//    public String getSelectedTickMarkValue(Point2D p2) {
//        if(valueSelected(p2)){
//            double value = this.getRangeAxis().getTickValue(p2);
//            return String.valueOf(value);
//        }
//        return null;
//    }

//    @Override
//    public String getXValue(Point2D localPosition) {
//        //该函数可能是错的
//        Point2D p2 = this.chartPanel.translateScreenToJava2D(new Point((int)localPosition.getX(), (int)localPosition.getY()));
//        return null;
//    }

    @Override
    public boolean isIntersectionWithItemData(Point2D p2) {
        Point2D translatedPoint = this.chartPanel.translateScreenToJava2D(new Point((int)p2.getX(), (int)p2.getY()));
        int seriesCount = this.dataset.getSeriesCount();
        for(int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++){
            TimeSeries series = this.dataset.getSeries(seriesIndex);
            int itemCount = series.getItemCount();
            for(int itemIndex = 0; itemIndex < itemCount; itemIndex++){
                try{
                Shape itemShape = this.getItemShape(seriesIndex, itemIndex);
                if(itemShape != null){
                    Rectangle2D rec = itemShape.getBounds2D();
                    if (rec.contains(translatedPoint)) {
                        return true;
                    }
                }
                }catch(Exception e){
                    System.out.println("SeriesIndex: " + seriesIndex + " ItemIndex: " + itemIndex);
                } 
            }
        }
        return false;
    }

    @Override
    public String getIntersectedItemDataTime(Point2D p2) {
        Point2D translatedPoint = this.chartPanel.translateScreenToJava2D(new Point((int)p2.getX(), (int)p2.getY()));
        int seriesCount = this.dataset.getSeriesCount();
        for(int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++){
            TimeSeries series = this.dataset.getSeries(seriesIndex);
            int itemCount = series.getItemCount();
            for(int itemIndex = 0; itemIndex < itemCount; itemIndex++){
                try{
                Shape itemShape = this.getItemShape(seriesIndex, itemIndex);
                if(itemShape != null){
                    Rectangle2D rec = itemShape.getBounds2D();
                    if (rec.contains(translatedPoint)) {
                        TimeSeriesDataItem tsdi = this.dataset.getSeries(seriesIndex).getDataItem(itemIndex);
                        long longTime = tsdi.getPeriod().getFirstMillisecond();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
                        String time = formatter.format(longTime);
                        return time;
                    }
                }
                }catch(Exception e){
                    System.out.println("SeriesIndex: " + seriesIndex + " ItemIndex: " + itemIndex);
                } 
            }
        }
        return null;
    }

    @Override
    public String getIntersectedItemDataValue(Point2D p2) {
        Point2D translatedPoint = this.chartPanel.translateScreenToJava2D(new Point((int)p2.getX(), (int)p2.getY()));
        int seriesCount = this.dataset.getSeriesCount();
        for(int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++){
            TimeSeries series = this.dataset.getSeries(seriesIndex);
            int itemCount = series.getItemCount();
            for(int itemIndex = 0; itemIndex < itemCount; itemIndex++){
                try{
                Shape itemShape = this.getItemShape(seriesIndex, itemIndex);
                if(itemShape != null){
                    Rectangle2D rec = itemShape.getBounds2D();
                    if (rec.contains(translatedPoint)) {
                        TimeSeriesDataItem tsdi = this.dataset.getSeries(seriesIndex).getDataItem(itemIndex);
                        return tsdi.getValue().toString();
                    }
                }
                }catch(Exception e){
                    System.out.println("SeriesIndex: " + seriesIndex + " ItemIndex: " + itemIndex);
                } 
            }
        }
        return null;
    }

//    @Override
//    public String getYValue(Point2D localPosition) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean isIntersectedWithYAxisTick(Point2D localPosition) {
        LinkedHashMap<Double, Point2D> tickMarkCoordinates = this.getRangeAxis().getTickMarkCoordinates();
        Collection<Point2D> coordinates =  tickMarkCoordinates.values();
        Point2D java2dPoint = this.chartPanel.translateScreenToJava2D(new Point((int)localPosition.getX(), (int)localPosition.getY()));
        Set<Double> keySet = tickMarkCoordinates.keySet();
//        double minYDistance = Double.MAX_VALUE;
//        double minKey = Double.MAX_VALUE;
        Iterator<Double> itr = keySet.iterator();
//        while(itr.hasNext()){
//            double key = itr.next();
//            Point2D cord = tickMarkCoordinates.get(key);
//            double curYDistance = Math.abs(cord.getY() - java2dPoint.getY());
//            if(curYDistance < minYDistance){
//                minYDistance = curYDistance;
//                minKey = key;
//            }
//        }
       while(itr.hasNext()){
           double key = itr.next();
           if(java2dPoint.getX() < tickMarkCoordinates.get(key).getX() + 15){
               return true;
           }
       } 
        
        return false;
    }

    @Override
    public boolean isIntersectedWithXAxisTick(Point2D localPosition) {
        LinkedHashMap<Double, Point2D> tickMarkCoordinates = this.getDomainAxis().getTickMarkCoordinates();
        Collection<Point2D> coordinates =  tickMarkCoordinates.values();
        Point2D java2dPoint = this.chartPanel.translateScreenToJava2D(new Point((int)localPosition.getX(), (int)localPosition.getY()));
        Set<Double> keySet = tickMarkCoordinates.keySet();

        Iterator<Double> itr = keySet.iterator();

       while(itr.hasNext()){
           double key = itr.next();
           if(java2dPoint.getY() < tickMarkCoordinates.get(key).getY() + 15){
               return true;
           }
       } 
        
        return false;
    }

    @Override
    public String getNearestYValue(Point2D localPosition) {
        LinkedHashMap<Double, Point2D> tickMarkCoordinates = this.getRangeAxis().getTickMarkCoordinates();
        Collection<Point2D> coordinates =  tickMarkCoordinates.values();
        Point2D java2dPoint = this.chartPanel.translateScreenToJava2D(new Point((int)localPosition.getX(), (int)localPosition.getY()));
        Set<Double> keySet = tickMarkCoordinates.keySet();
        double minYDistance = Double.MAX_VALUE;
        double minKey = Double.MAX_VALUE;
        Iterator<Double> itr = keySet.iterator();
        while(itr.hasNext()){
            double key = itr.next();
            Point2D cord = tickMarkCoordinates.get(key);
            double curYDistance = Math.abs(cord.getY() - java2dPoint.getY());
            if(curYDistance < minYDistance){
                minYDistance = curYDistance;
                minKey = key;
            }
        }
        return String.valueOf(minKey);
    }

    @Override
    public String getNearestXValue(Point2D localPosition) {
        LinkedHashMap<Double, Point2D> tickMarkCoordinates = this.getDomainAxis().getTickMarkCoordinates();
        Collection<Point2D> coordinates =  tickMarkCoordinates.values();
        Point2D java2dPoint = this.chartPanel.translateScreenToJava2D(new Point((int)localPosition.getX(), (int)localPosition.getY()));
        Set<Double> keySet = tickMarkCoordinates.keySet();
        double minXDistance = Double.MAX_VALUE;
        double minKey = Double.MAX_VALUE;
        Iterator<Double> itr = keySet.iterator();
        while(itr.hasNext()){
            double key = itr.next();
            Point2D cord = tickMarkCoordinates.get(key);
            double curXDistance = Math.abs(cord.getX() - java2dPoint.getX());
            if(curXDistance < minXDistance){
                minXDistance = curXDistance;
                minKey = key;
            }
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        String time = formatter.format(minKey);
        return time;
    }
    
    /*
     GlobalData globalData = new GlobalData();
        ArrayList<String> seriesNames = FileFilter.getFilter().getSeriesNames();
        for(String seriesName: seriesNames){
            try {
                String sql = "SELECT IDX, DATAVALUE, DATATIME FROM " + seriesName;
                ResultSet rSet = this.dataBaseIO.executeStatement(sql);
                TimeFormat seriesTimeFormat = this.getTimeFormat(seriesName);
                String seriesValueFormat = FileFilter.getFilter().getValueFormat(seriesName);
                rSet.beforeFirst();
                while(rSet.next()){
                    String curretValue = rSet.getString("DATAVALUE");
                    String currentTime = rSet.getString("DATATIME");
                    globalData.addOrReplaceItemData(seriesName,currentTime, seriesValueFormat, curretValue,seriesTimeFormat, currentTime, true, false);
                }
                globalData.getSeriesData(seriesName).setTimeFormat(seriesTimeFormat);
                globalData.getSeriesData(seriesName).setSeriesValueFormat(seriesValueFormat);
            } catch (SQLException ex) {
                Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return globalData;
    */

    @Override
    public GlobalData getDataRange() {
        GlobalData globalData = new GlobalData();
        ArrayList<String> seriesNames = new ArrayList();
        int seriesCount = this.dataset.getSeriesCount();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        //String time = formatter.format(minKey);
        for(int i = 0; i < seriesCount; i++){
            seriesNames.add(this.dataset.getSeriesKey(i).toString());
        }
        for(String seriesName:seriesNames){
            int itemCount = this.dataset.getSeries(seriesName).getItemCount();
            
//            if(this.getChartTitle().equals("Expectation Chart")){
//                TimeSeriesDataItem dataItem = this.dataset.getSeries(seriesName).getDataItem(0);
//                    String value = dataItem.getValue().toString();
//                    String time = formatter.format(dataItem.getPeriod().getFirstMillisecond());
//                    globalData.addOrReplaceItemData(seriesName, time, FileInformation.DATAFORMAT_DOUBLE, value, null, time, true, false);
//            }else{
                for (int j = 0; j < itemCount; j++) {
                    TimeSeriesDataItem dataItem = this.dataset.getSeries(seriesName).getDataItem(j);
                    String value = dataItem.getValue().toString();
                    String time = formatter.format(dataItem.getPeriod().getFirstMillisecond());
                    globalData.addOrReplaceItemData(seriesName, time, FileInformation.DATAFORMAT_DOUBLE, value, null, time, true, false);
                }
//            }

        }
        return globalData;
    }

    @Override
    public Metadata showTrendencyOfSeries(Metadata metadata) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Trendency Chart");
        return metadata;
    }

    @Override
    public Metadata showDataAfter(Metadata metadata, String comparedTime) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Time After Chart");
        return metadata;
    }

    @Override
    public Metadata showDataBefore(Metadata metadata, String comparedTime) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Time Before Chart");
        return metadata;
    }

    @Override
    public Metadata showDataBetween(Metadata metadata, String smallTime, String biggerTime) {
        ArrayList<String> seriesNames = metadata.getRawData().getSeriesNames();
        for(String seriesName: seriesNames){
            TimeSeries timeSeries = new TimeSeries( seriesName);
            int seriesSize = metadata.getValuesSize(seriesName);
            String seriesValueFormat = metadata.getRawData().getSeriesData(seriesName).getSeriesValueFormat();
            TimeFormat seriesTimeFormat = metadata.getRawData().getSeriesData(seriesName).getTimeFormat();
            for(int i = 0; i < seriesSize; i++){
                String value = metadata.getValue(seriesName, i);
                String time = metadata.getValueKey(seriesName, i);
                if(seriesValueFormat.equals("double")){
                    timeSeries.add(this.toRegularTimePeriod(time, seriesTimeFormat), Double.valueOf(value));
                } 
            }
            this.dataset.addSeries(timeSeries);
            //this.addSeriesWithPointShape(timeSeries, customisedShape);
        }
        this.setBaseShapetoCircle(true);
        this.setChartTitle("Data between  Chart");
        return metadata;
    }

   

    
    
    
    
}
