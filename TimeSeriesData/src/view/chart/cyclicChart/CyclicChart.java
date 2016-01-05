/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.cyclicChart;

import view.chart.sharedComponents.MyJFreeChartPolarPlot;
import view.chart.sharedComponents.MyJFreeChartDefaultPolarItemRenderer;
import java.awt.Font;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.PolarChartPanel;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import model.DataModel;
import model.timeProperty.TimeGranularity;
import model.timeProperty.TimeProperty;
import testNewChart.AbstractView3;
import testNewChart.MyView;
import toolkit.Toolkit;

/**
 *
 * @author Administrator
 */
public class CyclicChart extends MyView implements AbstractView3 {
    JFreeChart chart;
    XYSeriesCollection dataset = new XYSeriesCollection();
    MyJFreeChartPolarPlot plot;
    ValueAxis domainAxis;
    public CyclicChart( ){
        this.dataModel = DataModel.getDataModel();
        //chart = ChartFactory.createPolarChart("Polarplot Grahp", dataset, true, true, false);
        
        
        plot = new MyJFreeChartPolarPlot();
        plot.setDataset(dataset);
        NumberAxis rangeAxis = new MyJFreeChartCyclicNumberAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setTickLabelInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        plot.setAxis(rangeAxis);
        plot.setRenderer(new MyJFreeChartDefaultPolarItemRenderer());
        chart = new JFreeChart(
                "Polarplot Graph", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
       
        
        
        //init Plot
        plot = (MyJFreeChartPolarPlot)this.chart.getPlot();
        plot.setAngleOffset(0);
        plot.setCounterClockwise(true);
        plot.setAngleTickUnit(new NumberTickUnit(366 / 31));
        
        plot.setAngleLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setAngleGridlinesVisible(true);
        plot.setRadiusMinorGridlinesVisible(true);
        
        //init Axis 
        domainAxis = this.plot.getAxis();
    }
    
    
    TimeProperty timeProperty;
    StringProperty time_scale;
    SimpleObjectProperty time_scope;
    StringProperty time_arrangement;
    SimpleObjectProperty time_viewPoint;
    TimeGranularity time_granularity;
    StringProperty time_determinacy;
    private void InitView(){
        timeProperty = this.dataModel.getTimeProperty();
        time_scale = timeProperty.scale();
        time_scope = this.timeProperty.scope();
        this.time_arrangement = this.timeProperty.arrangement();
        this.time_viewPoint = this.timeProperty.viewPoint();
        this.time_granularity = this.timeProperty.getGranularity();
        this.time_determinacy = this.timeProperty.determinacy();
        
        if(this.time_granularity.getTimeGranularity().equals(TimeGranularity.TIMEGRANULARITY_NONE)){
            
        }else if(this.time_granularity.getTimeGranularity().equals(TimeGranularity.TIMEGRANULARITY_SINGLE)){
            
        }else if(this.time_granularity.getTimeGranularity().equals(TimeGranularity.TIMEGRANULARITY_MULTIPLE)){
           if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_YEAR)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_YEAR)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_MONTH)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_DAY)){
               //this.plot.setAngleTickUnit(new NumberTickUnit(10));
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_HOUR)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_MINUTE)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_SECOND)){
               
           }
        }
        
        
    }
    
    public XYDataset createData(){
        ArrayList data = dataModel.getDatabaseData();
        ArrayList time = dataModel.getDatabaseTime();
        this.InitView();
        if(this.time_granularity.getTimeGranularity().equals(TimeGranularity.TIMEGRANULARITY_NONE)){
            
        }else if(this.time_granularity.getTimeGranularity().equals(TimeGranularity.TIMEGRANULARITY_SINGLE)){
            System.out.println("Timanularity is Single");
        }else if(this.time_granularity.getTimeGranularity().equals(TimeGranularity.TIMEGRANULARITY_MULTIPLE)){
           if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_YEAR)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_YEAR)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_MONTH)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_DAY)){
               ArrayList years = Toolkit.yearsOf(time);
               if(years.size() > 1){
                   ArrayList<XYSeries> seriesArr = new ArrayList();
                   for(int i= 0; i< years.size(); i++){
                       XYSeries tmpSeries = new XYSeries(years.get(i).toString());
                       seriesArr.add(tmpSeries);
                       this.dataset.addSeries(tmpSeries);
                   }
                   for(int i = 0; i < data.size(); i++){
                       String curData = (String) data.get(i);
                       String curTime = (String) time.get(i);
                       int seriesIndex = years.indexOf(Toolkit.yearOf(curTime));
                       LocalDate ld = LocalDate.of(Toolkit.yearOf(curTime), Toolkit.monthOf(curTime), Toolkit.dayOf(curTime));
                       seriesArr.get(seriesIndex).add( ld.getDayOfYear(), Double.valueOf((String)data.get(i)));
                   }
               }
               
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_HOUR)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_MINUTE)){
               
           }else if(this.time_granularity.getBottomGranularity().equals(TimeGranularity.BOTTOMGRANULARITY_SECOND)){
               
           }
        }
         
        return dataset;
    }
    
    private static XYSeries createRandomData(final String name, 
                                             final double baseRadius, 
                                             final double thetaInc) {
        final XYSeries series = new XYSeries(name);
        for (double theta = 0.0; theta < 360.0; theta += thetaInc) {
            final double radius = baseRadius * (1.0 + Math.random());
            series.add(theta, radius);
        }
        return series;
    }
    @Override
    public void updateData(ResultSet data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getView() {
        SwingNode swingNode = new SwingNode();
        PolarChartPanel chartPanel = new PolarChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        swingNode.setContent(chartPanel);
        swingNode.autosize();
        swingNode.setManaged(true);
        return swingNode;
    }

    @Override
    public EventHandler getScrollEventHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventHandler getMouseEventHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniView() {
        this.createData();
    }

    @Override
    public void showAverage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeAllSeries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showSortData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showPeakValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public String getChartTitle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setChartTitle(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showDistribution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
