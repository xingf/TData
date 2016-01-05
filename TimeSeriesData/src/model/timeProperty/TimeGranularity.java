/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.timeProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jfree.chart.axis.DateTickUnitType;

/**
 *
 * @author Administrator
 */
public class TimeGranularity {
    public static final String TIMEGRANULARITY_NONE = "NONE";
    public static final String TIMEGRANULARITY_SINGLE = "SINGLE";
    public static final String TIMEGRANULARITY_MULTIPLE = "MULTIPLE";
    boolean yearFlag = false;
    public boolean hasYear(){
        return this.yearFlag;
    }
    public void setYearFlag(boolean yearFlag){
        this.yearFlag = yearFlag;
    }
    boolean monthFlag = false;
    public boolean hasMonth(){
        return this.monthFlag;
    }
    public void setMonthFlag(boolean monthFlag){
        this.monthFlag = monthFlag;
    }
    boolean dayFlag = false;
    public boolean hasDay(){
        return this.dayFlag;
    }
    public void setDayFlag(boolean dayFlag){
        this.dayFlag = dayFlag;
    }
    boolean hourFlag = false;
    public boolean hasHour(){
        return this.hourFlag;
    }
    public void setHourFlag(boolean hourFlag){
        this.hourFlag = hourFlag;
    }
    boolean minuteFlag = false;
    public boolean hasMinute(){
        return this.minuteFlag;
    }
    public void setMinuteFlag(boolean minuteFlag){
        this.minuteFlag = minuteFlag;
    }
    boolean secondFlag = false;
    public boolean hasSecond(){
        return this.secondFlag;
    }
    public void setSecondFlag(boolean secondFlag){
        this.secondFlag = secondFlag;
    }
    
    boolean millisecondFlag = false;
    public boolean hasMillisecond(){
        return this.millisecondFlag;
    }
    public void setMillisecondFlag(boolean millisecondFlag){
        this.millisecondFlag = millisecondFlag;
    }
    
    
    private StringProperty timeGranularity = new SimpleStringProperty();
    public String getTimeGranularity(){
        return this.timeGranularity.get();
    }
    public void setTimeGranularity(String timeGranularity){
        this.timeGranularity.set(timeGranularity);
    }
    public StringProperty timeGranularity(){
        return this.timeGranularity;
    }
    
    public static final DateTickUnitType BOTTOMGRANULARITY_DAY = DateTickUnitType.DAY;
    public static final DateTickUnitType BOTTOMGRANULARITY_MONTH = DateTickUnitType.MONTH;
    public static final DateTickUnitType BOTTOMGRANULARITY_YEAR = DateTickUnitType.YEAR;
    public static final DateTickUnitType BOTTOMGRANULARITY_HOUR = DateTickUnitType.HOUR;
    public static final DateTickUnitType BOTTOMGRANULARITY_MINUTE = DateTickUnitType.MINUTE;
    public static final DateTickUnitType BOTTOMGRANULARITY_SECOND = DateTickUnitType.SECOND;
    public static final DateTickUnitType BOTTOMGRANULARITY_MILLISECOND = DateTickUnitType.MILLISECOND;
    private DateTickUnitType bottomGranularity;
    public DateTickUnitType getBottomGranularity(){
        return this.bottomGranularity;
    }
    public void setBottomGranularity(DateTickUnitType bottomGranularity){
        this.bottomGranularity = bottomGranularity;
    }
    
    private int multiply = 0;
    public int getMultiply(){
        return this.multiply;
    }
    
    public void setMultiply(int multiply){
        this.multiply = multiply;
    }
}
