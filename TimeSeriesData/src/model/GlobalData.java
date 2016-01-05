/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import model.dataProperty.DataProperty;
import model.timeProperty.TimeProperty;

/**
 *
 * @author Administrator
 */
public class GlobalData {
    
    private LinkedHashMap<String, SeriesData> allDatasMap;
    private final TimeProperty timeProperty;
    private final DataProperty dataProperty;
//    private HashMap<String, SeriesMetaData> metaData;
    
    public GlobalData() {
        //metaData = new HashMap();
        allDatasMap = new LinkedHashMap();
        this.timeProperty = TimeProperty.getTimeProperty();
        this.dataProperty = DataProperty.getDataProperty();
        
    }

    public TimeProperty getTimeProperty() {
        return this.timeProperty;
    }
    public DataProperty getDataProperty() {
        return this.dataProperty;
    }
    
    
    public void addOrReplaceItemData(String seriesKey, String itemKey, String itemValueFormat, String itemValue,TimeFormat itemTimeFormat, String itemTime, boolean visible, boolean hightlight){
        if(!this.allDatasMap.containsKey(seriesKey)){
            SeriesData seriesData = new SeriesData();
            seriesData.setSeriesKey(seriesKey);
            seriesData.addOrReplaceItemData(itemKey, itemValueFormat, itemValue, itemTimeFormat, itemTime, visible, hightlight);
            this.allDatasMap.put(seriesKey, seriesData);
        }else{
            this.allDatasMap.get(seriesKey).addOrReplaceItemData(itemKey, itemValueFormat, itemValue, itemTimeFormat,itemTime, visible, hightlight);
        }
    }
//    public void addOrReplaceItemData(int seriesIndex, int itemIndex, String value, String time, boolean visible, boolean hightlight){
//        String seriesKey = String.valueOf(seriesIndex);
//        String itemKey = String.valueOf(itemIndex);
//        this.addOrReplaceItemData(seriesKey, itemKey, value, time, visible, hightlight);
//    }
    
    
//    public SeriesData getSeriesData(int seriesIndex){
//        String seriesKey = String.valueOf(seriesIndex);
//        return this.data.get(seriesKey);
//    }
    public SeriesData getSeriesData(String seriesKey){
        return this.allDatasMap.get(seriesKey);
    }
    
    public String getItemDataValue(String seriesKey, String itemKey){
        return this.allDatasMap.get(seriesKey).getItemData(itemKey).getValue();
    }
    public String getItemDataTime(String seriesKey, String itemKey){
        return this.allDatasMap.get(seriesKey).getItemData(itemKey).getTime();
    }


    public int getSeriesesSize(){
        return this.allDatasMap.size();
    }
   
    public int getSeriesSize(String seriesKey){
        return this.allDatasMap.get(seriesKey).getData().size();
    }


    public ArrayList<String> getSeriesNames(){
        Set<String> seriesNamesSet= this.allDatasMap.keySet();
        ArrayList<String> seriesNames = new ArrayList();
        Iterator<String> itr = seriesNamesSet.iterator();
        while(itr.hasNext()){
            seriesNames.add(itr.next());
        }
        return seriesNames;
    }
    
}
