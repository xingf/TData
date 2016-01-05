/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class TimeFormat {
    public final static String YEAR = "year";
    public final static String MONTH = "month";
    public final static String DAY = "day";
    public final static String HOUR = "hour";
    public final static String MINUTE = "minute";
    public final static String SECOND = "second";
    public final static String MILLISECOND = "millisecond";
    
    private boolean year;
    private boolean month;
    private boolean day;
    private boolean hour;
    private boolean minute;
    private boolean second;
    private boolean millisecond;
    private String granularity;
    private String granularityMultiple;
    
    
    public TimeFormat(){
        this.millisecond = false;
        this.second = false;
        this.minute = false;
        this.hour = false;
        this.day = false;
        this.month = false;
        this.year = false;
        this.granularity = null;
        this.granularityMultiple = null;
    }

    
    public TimeFormat(boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean second, boolean millisecond, String granularity, String granularityMultiple){
        
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
        this.granularity = granularity;
        this.granularityMultiple = granularityMultiple;
    }
    
    public TimeFormat(ArrayList<String> timeFormatArrayList, String granularity, String granularityMultiple){
        for(String component: timeFormatArrayList){
            if(component.trim().equals(TimeFormat.YEAR)){
                this.year = true;
            }else if(component.trim().equals(TimeFormat.MONTH)){
                this.month = true;
            }else if(component.trim().equals(TimeFormat.DAY)){
                this.day = true;
            }else if(component.trim().equals(TimeFormat.HOUR)){
                this.hour = true;
            }else if(component.trim().equals(TimeFormat.MINUTE)){
                this.minute = true;
            }else if(component.trim().equals(TimeFormat.SECOND)){
                this.second = true;
            }else if(component.trim().equals(TimeFormat.MILLISECOND)){
                this.millisecond = true;
            }
            
        }
        this.granularity = granularity;
        this.granularityMultiple = granularityMultiple;
    }
    
    public boolean containsYear() {
        return year;
    }

    public void setYear(boolean year) {
        this.year = year;
    }

    public boolean containsMonth() {
        return month;
    }

    public void setMonth(boolean month) {
        this.month = month;
    }

    public boolean containsDay() {
        return day;
    }

    public void setDay(boolean day) {
        this.day = day;
    }

    public boolean containsHour() {
        return hour;
    }

    public void setHour(boolean hour) {
        this.hour = hour;
    }

    public boolean containsMinute() {
        return minute;
    }

    public void setMinute(boolean minute) {
        this.minute = minute;
    }

    public boolean containsSecond() {
        return second;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public boolean containsMillisecond() {
        return millisecond;
    }

    public void setMillisecond(boolean millisecond) {
        this.millisecond = millisecond;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public String getGranularityMultiple() {
        return granularityMultiple;
    }

    public void setGranularityMultiple(String granularityMultiple) {
        this.granularityMultiple = granularityMultiple;
    }
}
