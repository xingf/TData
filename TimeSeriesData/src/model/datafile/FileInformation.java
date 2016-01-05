/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.datafile;

/**
 *
 * @author Administrator
 */
public class FileInformation {
    public static String DATAFORMAT_DOUBLE = "double";
    public static String DATAFORMAT_INT = "int";
    
    private String seriesName;
    private String dataFormat;
    private String granularity;
    private String granularityMultiple;
    private String timeComponents;
    private String startTime;
    private String endTime;
    
    public FileInformation(){
        this.seriesName = null;
        this.dataFormat = null;
        this.granularity = null;
        this.granularityMultiple = null;
        this.timeComponents = null;
        this.startTime = null;
        this.endTime = null;
    }
    public FileInformation(String seriesName, String granularity, String granularityMultiple, String timeComponents, String startTime, String endTime){
        this.seriesName = seriesName;
        this.dataFormat = null;
        this.granularity = granularity;
        this.granularityMultiple = granularityMultiple;
        this.timeComponents = timeComponents;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    
    public void setSeriesName(String seriesName){
        this.seriesName = seriesName;
    }
    public String getSeriesName(){
        return this.seriesName;
    }
    public void seriesName(){
        this.getSeriesName();
    }
    
    public String getDataFormat(){
        return this.dataFormat;
    }
    public void setDataFormat(String dataFormat){
        this.dataFormat = dataFormat;
    }
    
    public void setGranularity(String granularity){
        this.granularity = granularity;
    }
    public String getGranularity(){
        return this.granularity;
    }
    public String granularity(){
        return this.getGranularity();
    }
    
    public void setGranularityMultiple(String granularityMultiple){
        this.granularityMultiple = granularityMultiple;
    }
    public String getGranularityMultiple(){
        return this.granularityMultiple;
    }
    public String granularityMultiple(){
        return this.getGranularityMultiple();
    }
    
    public void setTimeComponents(String timeComponents){
        String[] components = timeComponents.split(",");
        String formattedComponents = "";
        for(String component: components){
            formattedComponents += "," + component.trim();
        }
        formattedComponents = formattedComponents.substring(1);
        this.timeComponents = formattedComponents;
    }
    public String getTimeComponents(){
        return this.timeComponents;
    }
    public String timeComponents(){
        return this.getTimeComponents();
    }
    
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String startTime(){
        return this.getStartTime();
    }
    
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public String endTime(){
        return this.getEndTime();
    }
}
