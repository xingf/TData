/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Administrator
 */
public class ItemData {
    private String value;
    private String time;
    private boolean visible;
    private boolean hightlight;
    private boolean selected;
    private String key;
    private TimeFormat timeFormat;
    private String valueFormat;
    public ItemData(){
        value = null;
        time = null;
        visible = true;
        hightlight = false;
        this.timeFormat = null;
    }
    public ItemData(String key, String itemValueFormat, String value, TimeFormat timeFormat,String time, boolean visible, boolean hightlight){
        this.key = key;
        this.valueFormat = itemValueFormat;
        this.value = value;
        this.time = time;
        this.timeFormat = timeFormat;
        this.visible = visible;
        this.hightlight = hightlight;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public String getTime(){
        return this.time;
    }
    
    public void setTime(String time){
        this.time = time;
    } 
    
    
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public boolean isVisible(){
        return this.visible;
    }
    
    public void setHightlight(boolean hightlight){
        this.hightlight = hightlight;
    }
    public boolean isHightlight(){
        return this.hightlight;
    }
    
    public void setSelection(boolean selected){
        this.selected = selected;
    }
    public boolean isSelected(){
        return this.selected;
    }
    
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    
    public TimeFormat getTimeFormat(){
        return this.timeFormat;
    }
    
    public void setTimeFormat(TimeFormat timeFormat){
        this.timeFormat = timeFormat;
    }
    
}
