/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testNewChart;

import java.sql.ResultSet;
import javafx.event.EventHandler;
import javafx.scene.Node;
import model.DataModel;

/**
 *
 * @author Administrator
 */
public interface AbstractView3 {
    static String VIEW_NAME_A = "A";
    abstract void updateData(ResultSet data);
    abstract Node getView();
    abstract EventHandler getScrollEventHandler();
    abstract EventHandler getMouseEventHandler();
    //abstract void setDataIO(DataBaseIO dataIO);
    abstract void setDataModel(DataModel dataModel);
    abstract void iniView();
    abstract String getChartTitle();
    abstract void setChartTitle(String name);
    
    
    
    abstract void showAverage();
    abstract void showMax();
    abstract void showSortData();
    abstract void showPeakValue();
    abstract void removeAllSeries();
    abstract void showDistribution();
}
