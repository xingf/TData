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


/**
 *
 * @author Administrator
 */
public class Metadata {
    public static String GLOBALMAX_FORSERIES = "GLOBALMAX_FORSERIES";
    public static String LOCALMAX_FORSERIES = "LOCALMAX_FORSERIES";
    public static String LOCALMIN_FORSERIES = "LOCALMIN_FORSERIES";
    public static String GLOBALMIN_FORSERIES = "GLOBALMIN_FORSERIES";
    public static String EXPECTION_FORSERIES = "EXPECTION_FORSERIES";
    public static String DISTRIBUTION_FORSERIES = "DISTRIBUTION_FORSERIES";
    public static String QUERY_VALUE_ABOVE = "QUERY_VALUE_ABOVE";
    public static String QUERY_VALUE_BELOW = "QUERY_VALUE_BELOW";
    public static String QUERY_VALUE_BETWEEN = "QUERY_VALUE_BETWEEN";
    public static String HIGHTLIGHT = "HIGHTLIGHT";
    public static String QUERY_TRENDENCY = "TRENDENCY";
    public static String QUERY_DATA_LATER = "QUERY_DATA_LATER";
    public static String QUERY_DATA_BEFORE = "QUERY_DATA_BEFORE";
    public static String QUERY_DATA_BETWEEN = "QUERY_DATA_BETWEEN";
    
    class Value{
        String value;
        String key;
        
        public Value(String value, String key){
            this.value = value;
            this.key = key;
        }
        
        public Value(){
            this.value = null;
            this.key = null;
        }
        
        public void setValue(String value){
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
        
        public String getKey(){
            return this.key;
        }
        
        public void setKey(String key){
            this.key = key;
        }
    }
    
    private String operation;
    private boolean forMultipleValues;
    private boolean forSeries;
    model.GlobalData rawData;
    LinkedHashMap<String, ArrayList<Value>> values;
    
    public Metadata(){
        this.operation = null;
        this.forMultipleValues = false;
        this.forSeries = false;
        this.rawData = null;
        this.values = new LinkedHashMap();
        
    }
    
    public void addData(String operation, String value, String valuesKey, String valueKey){
        this.operation = operation;
        if(this.values.containsKey(valuesKey)){
            this.values.get(valuesKey).add(new Value(value, valueKey));
        }else{
            ArrayList<Value> tmpValues = new ArrayList();
            tmpValues.add(new Value(value, valueKey));
            this.values.put(valuesKey, tmpValues);
        }
    }
    
    public void removeData(String value, String valuesKey, String valueKey){
        if(this.values.containsKey(valuesKey)){
            ArrayList<Value> series = this.values.get(valuesKey);
            for(int i= 0; i < series.size(); i++){
                if(series.get(i).getKey().equals(valueKey)){
                    this.values.get(valuesKey).remove(i);
                }
            }
        }
    }
    
    public void removeAllData(){
        this.operation = Metadata.HIGHTLIGHT;
        this.forMultipleValues = false;
        this.forSeries = false;
        this.rawData = null;
        this.values = new LinkedHashMap();
    }
    
    public int getValuesSize(String valuesKey){
        return this.values.get(valuesKey).size();
    }
    
    public String getValue(String valuesKey, int valueIndexInValues){
        return this.values.get(valuesKey).get(valueIndexInValues).getValue();
    }
    
    public String getValue(String valuesKey, String valueKey){
        if (this.values.containsKey(valuesKey)) {
            ArrayList<Value> series = this.values.get(valuesKey);
            for (int i = 0; i < series.size(); i++) {
                if (series.get(i).getKey().equals(valueKey)) {
                    return series.get(i).getValue();
                } 
            }
        } 
        return null;
    }
    
    public String getValueKey(String valuesKey, int valueIndexInValues){
        return this.values.get(valuesKey).get(valueIndexInValues).getKey();
    }
    
    public ArrayList<String> getValuesKeys(){
        ArrayList<String> valuesKeys = new ArrayList();
        Set<String> valuesKeysSet = this.values.keySet();
        Iterator<String> itr = valuesKeysSet.iterator();
        while(itr.hasNext()){
            valuesKeys.add(itr.next());
        }
        return valuesKeys;
    }
    
    public void setForMultipleValue(boolean forMultipleValue){
        this.forMultipleValues = forMultipleValue;
    }
    
    public boolean isForMultipleValue(){
        return !this.forMultipleValues;
    }

    public boolean isForSeries(){
        return !this.forSeries;
    }
    
    public void setForSeries(boolean forSeries){
        this.forSeries = forSeries;
    }
    
    public void setRawData(model.GlobalData rawData){
        this.rawData = rawData;
    }
    
    public model.GlobalData getRawData(){
        return this.rawData;
    }
    
    public String getOperation(){
        return this.operation;
    }
}
