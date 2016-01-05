/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testNewChart;

import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import model.DataModel;
import testNewChart.AbstractView3;

/**
 *
 * @author Administrator
 */
public class HistogramView extends MyView{

    JFreeChart chart;
    ChartPanel chartPanel;
    DefaultCategoryDataset dataset;
    CategoryPlot plot;
    public HistogramView(DataModel dataModel){
        this.setDataModel(dataModel);
        dataset = new DefaultCategoryDataset();
        
        chart = ChartFactory.createBarChart("Bar Chart Demo", "Category", "Value", dataset, PlotOrientation.VERTICAL, true, true, true);
        chartPanel = new ChartPanel(chart);
        
        plot = this.chart.getCategoryPlot();
        this.chartPanel.setMouseWheelEnabled(true);
        this.chartPanel.setDomainZoomable(true);
        this.chartPanel.setRangeZoomable(true);
    }
   
    public void updateData(ResultSet data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Node getView() {
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);
        System.out.println("is resizeable: " + swingNode.isResizable());
        swingNode.setManaged(true);
        return swingNode;
    }

    public void showDistribution(){
        ArrayList data = DataModel.getDataModel().getDatabaseData();
        data.sort(new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = (String)o1;
                String s2 = (String)o2;
                if(s1.compareTo(s2) > 0){
                    return 1;
                }else if(s1.compareTo(s2) == 0){
                    return 0;
                }else{
                    return -1;
                }
               
            }
        });
       
        Iterator itr = data.iterator();
        String lastKey = null;
        int cnt = 0;
        int size = data.size();
        int limit = 0;
        while(itr.hasNext()){
            String curKey = (String) itr.next();
            if(limit > 50)return;
            limit++;
            if(curKey.equals(lastKey)){
                lastKey = curKey;
                cnt++;
            }else{
                if(lastKey == null){
                    lastKey = curKey;
                    cnt = 1;
                }else{
                    
                    NumberFormat nf = NumberFormat.getNumberInstance();
                    double value = Double.valueOf(nf.format(((double)cnt) / ((double)size)));
                    System.out.println(value + " " + cnt);
                    this.dataset.addValue(value , "Histogram", lastKey);
                    
                    lastKey = curKey;
                    cnt = 1;
                }
                
            }
        }
        
       
        
    }
    
    
}
