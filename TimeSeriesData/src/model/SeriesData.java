/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Administrator
 */
public class SeriesData {
    
    public static String DATAFORMAT_DOUBLE = "double";
    public static String DATAFORMAT_INT = "int";
    private LinkedHashMap<String, ItemData> seriesDataMap;
    private String seriesKey;
    private TimeFormat seriesTimeFormat;
    private String seriesValueFormat;
    public SeriesData() {
        this.seriesDataMap = new LinkedHashMap();
        this.seriesKey = null;
        this.seriesTimeFormat = null;
        this.seriesValueFormat = null;
    }

    public LinkedHashMap getData(){
        return this.seriesDataMap;
    }
    
    public ArrayList<ItemData> getDataInArrayList(){
        ArrayList<ItemData> dataArrayList = new ArrayList();
        ArrayList<String> dataKeyArrayList = this.getItemDataKeyInArrayList();
        for(String dataKey: dataKeyArrayList){
            dataArrayList.add(this.seriesDataMap.get(dataKey));
        }
        
        return dataArrayList;
    }
    
    private ArrayList<String> getItemDataKeyInArrayList(){
        ArrayList<String> keyArrayList = new ArrayList();
        Set<String> keySet = this.seriesDataMap.keySet();
        for(String key:keySet){
            keyArrayList.add(key);
        }
        return keyArrayList;
    }
    
    public void setSeriesData(LinkedHashMap<String, ItemData> data, String seriesValueFormat, TimeFormat seriesTimeFormat, String seriesKey){
        this.seriesDataMap = data;
        this.seriesValueFormat = seriesValueFormat;
        this.seriesTimeFormat = seriesTimeFormat;
        this.seriesKey = seriesKey;
    }
    
    public String getSeriesKey(){
        return this.seriesKey;
    }
    
    public void setSeriesKey(String seriesKey){
        this.seriesKey = seriesKey;
    }
    
    public TimeFormat getTimeFormat(){
        return this.seriesTimeFormat;
    }
    
    public void setTimeFormat(TimeFormat timeFormat){
        this.seriesTimeFormat = timeFormat;
    }
    
    public ItemData getItemData(String itemDataKey){
        return this.seriesDataMap.get(itemDataKey);
    }
    
    public ItemData getItemData(int itemIndex){
        String itemKey = String.valueOf(itemIndex);
        return this.seriesDataMap.get(itemKey);
    }
    
    public void addOrReplaceItemData(String itemKey, String valueFormat, String value, TimeFormat timeFormat,String time, boolean visible, boolean hightlight){
        if(!this.seriesDataMap.containsKey(itemKey)){
            ItemData singleData = new ItemData(itemKey, valueFormat, value, timeFormat,time , visible, hightlight);
            this.seriesDataMap.put(itemKey, singleData);
        }
    }
    
    public void addOrReplaceItemData(String seriesKey, ItemData itemData){
        if(this.seriesDataMap.containsKey(seriesKey)){
            this.seriesDataMap.replace(seriesKey, itemData);
        }else{
            this.seriesDataMap.put(seriesKey, itemData);
        }
    }
    
    public String getSeriesValueFormat(){
        return this.seriesValueFormat;
    }
    
    public void setSeriesValueFormat(String seriesValueFormat){
        this.seriesValueFormat = seriesValueFormat;
    }
    
    public boolean contains(String itemKey){
        if(this.seriesDataMap.containsKey(itemKey)){
            return true;
        }else{
            return false;
        }
    }
}
