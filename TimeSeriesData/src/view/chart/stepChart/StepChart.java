package view.chart.stepChart;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import view.chart.sharedComponents.MyJFreeChartXYStepRenderer;
import view.chart.sharedComponents.MyJFreeChartDateAxis;
import java.util.ArrayList;
import javafx.embed.swing.SwingNode;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.util.ParamChecks;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.date.DateUtilities;
import model.DataModel;
import view.AbstractChart;
import view.chart.sharedComponents.MyChartPanel;
import view.chart.sharedComponents.MyJFreeChartNumberAxis;

/**
 *
 * @author Administrator
 */
public class StepChart /*extends AbstractChart*/{
    XYSeriesCollection dataset = new XYSeriesCollection();
    JFreeChart chart;
    MyJFreeChartDateAxis xAxis;
    MyJFreeChartNumberAxis yAxis;
    XYPlot plot;
    MyChartPanel chartPanel;
    public StepChart(){
        System.out.println("create step chart");
        chart = this.createChart("Time Step Chart", "Time", "Value", dataset, PlotOrientation.VERTICAL, true, true, true);
        
        this.chartPanel = new MyChartPanel(chart);
        plot = this.chart.getXYPlot();
        
        //yAxis.setAutoRangeIncludesZero(false);
        chartPanel.setMouseWheelEnabled(true);
        
        //this.xAxis.setTimeScope(TimeScope.SCOPE_INTERVAL);
        plot.setDomainPannable(true);
        plot.setRangePannable(true); 
        plot.setDomainZeroBaselineVisible(false);
        plot.setRangeZeroBaselineVisible(false);
    }
    private JFreeChart createChart(String title, String xAxisLabel,
            String yAxisLabel, XYDataset dataset, PlotOrientation orientation,
            boolean legend, boolean tooltips, boolean urls) {

        ParamChecks.nullNotPermitted(orientation, "orientation");
        xAxis = new MyJFreeChartDateAxis(xAxisLabel);
        yAxis = new MyJFreeChartNumberAxis(yAxisLabel);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }

        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        XYItemRenderer renderer = new MyJFreeChartXYStepRenderer(toolTipGenerator,
                urlGenerator);

        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT,
                plot, legend);
        
        this.showRawData();
       
        return chart;

    }
    
    //@Override
     public SwingNode getView() {
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);
        //System.out.println("is resizeable: " + swingNode.isResizable());
        swingNode.setManaged(true);
        return swingNode;
    }
    
    public void setData(ArrayList data, ArrayList time){
        int variablesCount = data.size();
        for(int i= 0; i < variablesCount; i++){
            ArrayList<String> variableData = (ArrayList<String>) data.get(i);
            
        }
    } 
    public void showRawData() {
        XYSeries s1 = new XYSeries("abs");
        ArrayList time = DataModel.getDataModel().getDatabaseTime();
        ArrayList data = DataModel.getDataModel().getDatabaseData();
        int size = data.size();
        for (int i = 0; i < size; i++) {
            String timeString = (String) time.get(i);
            String[] timeComponent = timeString.split("-");
            int day = Integer.valueOf(timeComponent[2]);
            int month = Integer.valueOf(timeComponent[1]);
            int year = Integer.valueOf(timeComponent[0]);
            Float value = Float.valueOf((String) data.get(i));

            s1.add(DateUtilities.createDate(year, month, day).getTime(), value);
        }
        this.dataset.addSeries(s1);
    }

    //@Override
    public SwingNode showException(ArrayList<String> exceptions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
