/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.geom.Point2D;
import java.util.ArrayList;
import model.Metadata;
import model.GlobalData;

/**
 *
 * @author Administrator
 */
public interface IAbstractChart {
    
    public void showData(GlobalData tsData);
    public void showData(GlobalData globalData, Metadata metadata);
    public Metadata showExpectionOfSeries(Metadata metadata);
    public Metadata showGlobalMaxValueOfSeries(Metadata metadata);
    public Metadata showGlobalMinValueOfSeries(Metadata metadata);
    public Metadata showLocalMinValueOfSeries(Metadata metadata);
    public Metadata showLocalMaxValueOfSeries(Metadata metadata);
    public Metadata showValueAboveOfSeries(Metadata metadata, String comparedValue);
    public Metadata showValueBelowOfSeries(Metadata metadata, String comparedValue);
    public Metadata showValueBetweenOfSeries(Metadata metadata, String smallerValue, String biggerValue);
    public Metadata showTrendencyOfSeries(Metadata metadata);
    public Metadata showDataAfter(Metadata metadata, String comparedTime);
    public Metadata showDataBefore(Metadata metadata, String comparedTime);
    public Metadata showDataBetween(Metadata metadata, String smallTime, String biggerTime);
    public Metadata hightLightData(Metadata tsData);
//    public double getX(int series, int item);
//    public double getY(int series, int item);
    //public int selectedSeries();
    //public Object getEventSource();
//    public int getSeriesIndex(double x, double y);
//    public int getItemIndex(double x, double y);
    public boolean isInDataArea(double x, double y);
    public Metadata showDistributionOfSeries(Metadata metadata);
//    public String getYValue(Point2D localPosition);
//    public String getXValue(Point2D localPosition);
//
//    public String getValue(Point2D localPosition);
//    public String getTime(Point2D localPosition);
//    public String getSeriesKey(Point2D localPosition);
    public void addHightlightTimeSeriesData(String seriesKey, String value, String time);
    //public void setHightlightTimeSeriesData();
    public void clearHightlightTimeSeriesData(String seriesKey, String value, String time);
    public void clearAllHightlightTimeSeriesData();
//    public Metadata getHightlightData();
//    public boolean timeSelected(Point2D p2);
//    public boolean valueSelected(Point2D p2);
//    public String getSelectedTickMarkTime(Point2D p2);
//    public String getSelectedTickMarkValue(Point2D p2);
    
    public boolean isIntersectedWithYAxisTick(Point2D localPosition);
    public boolean isIntersectedWithXAxisTick(Point2D localPosition);
    public String getNearestYValue(Point2D localPosition);
    public String getNearestXValue(Point2D localPosition);
    
    
    public boolean isIntersectionWithItemData(Point2D localPosition);
    public String getIntersectedItemDataTime(Point2D localPosition);
    public String getIntersectedItemDataValue(Point2D localPosition);
    
    public GlobalData getDataRange();
}
