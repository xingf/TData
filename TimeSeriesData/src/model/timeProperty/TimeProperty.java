/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.timeProperty;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class TimeProperty {
    public static final String SCALE_ORDINAL = "ORDINAL";
    public static final String SCALE_DISCRETE = "DISCRETE";
    public static final String SCALE_CONTINUOUS = "CONTINUOUS";
    private StringProperty scale = new SimpleStringProperty();
    public void setScale(String scale){
        this.scale.set(scale);
    }
    public String getScale(){
        return this.scale.get();
    }
    public StringProperty scale(){
        return this.scale;
    }
    
    private SimpleObjectProperty scope = new SimpleObjectProperty<TimeScope>();
    public void setScope(TimeScope scope){
        this.scope.set(scope);
    }
    public TimeScope getScope(){
        return (TimeScope)this.scope.get();
    }
    public SimpleObjectProperty scope(){
        return this.scope;
    }
    
    public static final String ARRANGEMENT_LINEAR = "LINEAR";
    public static final String ARRANGEMENT_CYCLIC = "CYCLIC";
    private StringProperty arrangement = new SimpleStringProperty();
    public void setArrangement(String arrangement){
        this.arrangement.set(arrangement);
    }
    public String getArrangement(){
        return this.arrangement.get();
    }
    public StringProperty arrangement(){
        return this.arrangement;
    }
    
    private SimpleObjectProperty viewPoint = new SimpleObjectProperty<TimeViewPoint>();
    public void setViewPoint(TimeViewPoint viewPoint){
        this.viewPoint.set(viewPoint);
    }
    public TimeViewPoint getViewPoint(){
        return (TimeViewPoint)this.viewPoint.get();
    }
    public SimpleObjectProperty viewPoint(){
        return this.viewPoint;
    }
    
    private SimpleObjectProperty granularity = new SimpleObjectProperty<TimeGranularity>();
    public void setGranularity(TimeGranularity granularity){
        this.granularity.set(granularity);
    }
    public TimeGranularity getGranularity(){
        return (TimeGranularity)this.granularity.get();
    }
    public SimpleObjectProperty timeGranularity(){
        return this.granularity;
    }
    
   
    
    
    
    static final public String TIMEPRIMITIVES_INSTANT = "INSTANT";
    static final public String TIMEPRIMITIVES_INTERVAL = "INTERVAL";
    static final public String TIMEPRIMITIVES_SPAN = "SPAN";
    private StringProperty timePrimitives = new SimpleStringProperty();
    public void setTimePrimitives(String timePrimitives){
        this.timePrimitives.set(timePrimitives);
    }
    public String getTimePrimitives(){
        return this.timePrimitives.get();
    }
    public StringProperty timePrimitives(){
        return this.timePrimitives;
    }
    
    static final String DETERMINACY_DETERMINATE = "DETERMINATE";
    static final String DETERMINACY_INDETERMINATE = "INDETERMINATE";
    private StringProperty determinacy = new SimpleStringProperty();
    public void setDeterminacy(String determinacy){
        this.determinacy.set(determinacy);
    }
    public String getDeterminacy(){
        return this.determinacy.get();
    }
    public StringProperty determinacy(){
        return this.determinacy;
    }
    
    private TimeProperty(){
        
        // For MISC/QBIRTHS.1 
        this.setScale(TimeProperty.SCALE_CONTINUOUS);
        TimeScope ts = new TimeScope();
        ts.setTimeScope(TimeScope.SCOPE_POINT);
        this.setScope(ts);
        this.setArrangement(TimeProperty.ARRANGEMENT_LINEAR);
        TimeViewPoint tvp = new TimeViewPoint();
        tvp.setTimeViewPoint(TimeViewPoint.VIEWPOINT_TOTALLY);
        this.setViewPoint(tvp);
        TimeGranularity tg = new TimeGranularity();
        tg.setTimeGranularity(TimeGranularity.TIMEGRANULARITY_MULTIPLE);
        //tg.setBottomGranularity(TimeGranularity.BOTTOMGRANULARITY_DAY);
        tg.setBottomGranularity(TimeGranularity.BOTTOMGRANULARITY_MILLISECOND);
        
        tg.setYearFlag(true);
        tg.setMonthFlag(true);
        tg.setDayFlag(true);
        tg.setMultiply(1);
        this.setGranularity(tg);
        this.setTimePrimitives(TimeProperty.TIMEPRIMITIVES_INTERVAL);
        this.setDeterminacy(TimeProperty.DETERMINACY_DETERMINATE);
        this.setStartYear(1977);
        this.setStartMonth(1);
        this.setStartDay(1);
//        
        
        // BOXJENK/SERIESA.1
//        this.setScale(TimeProperty.SCALE_DISCRETE);
//        TimeScope ts = new TimeScope();
//        ts.setTimeScope(TimeScope.SCOPE_POINT);
//        this.setScope(ts);
//        this.setArrangement(TimeProperty.ARRANGEMENT_LINEAR);
//        TimeViewPoint tvp = new TimeViewPoint();
//        tvp.setTimeViewPoint(TimeViewPoint.VIEWPOINT_TOTALLY);
//        this.setViewPoint(tvp);
//        TimeGranularity tg = new TimeGranularity();
//        //tg.setTimeGranularity(TimeGranularity.TIMEGRANULARITY_MULTIPLE);
//        //tg.setBottomGranularity(TimeGranularity.BOTTOMGRANULARITY_DAY);
//        tg.setTimeGranularity(TimeGranularity.TIMEGRANULARITY_SINGLE);
//        tg.setBottomGranularity(TimeGranularity.BOTTOMGRANULARITY_HOUR);
//           
//        tg.setMultiply(2);
//        this.setGranularity(tg);
//        this.setTimePrimitives(TimeProperty.TIMEPRIMITIVES_INSTANT);
//        this.setDeterminacy(TimeProperty.DETERMINACY_DETERMINATE);
        //this.setStartHour(0);
        
        
    }
   
    static TimeProperty timeProperty = null;
    public static TimeProperty getTimeProperty(){
        if(timeProperty == null){
            timeProperty = new TimeProperty();
        }
        return timeProperty;
    }
   
    
    private int startYear = Integer.MIN_VALUE; 
    private int startMonth = Integer.MIN_VALUE;
    private int startDay = Integer.MIN_VALUE;
    private int startHour = Integer.MIN_VALUE;
    private int startMinute = Integer.MIN_VALUE;
    private int startSecond = Integer.MIN_VALUE;
    private int endYear = Integer.MIN_VALUE;
    private int endMonth = Integer.MIN_VALUE;
    private int endDay = Integer.MIN_VALUE;
    private int endHour = Integer.MIN_VALUE;
    private int endMinute = Integer.MIN_VALUE;
    private int endSecond = Integer.MIN_VALUE;
    
    public void setStartYear(int year){
        this.startYear = year;
    }
    public int getStartYear(){
        return this.startYear;
    }
    
    public void setStartMonth(int month){
        this.startMonth = month;
    }
    public int getStartMonth( ){
        return this.startMonth;
    }
    
    public void setStartDay(int day){
        this.startDay = day;
    }
    public int getStartDay(){
        return this.startDay;
    }
    
    public void setStartHour(int hour){
        this.startHour = hour;
    }
    public int getStartHour(){
        return this.startHour;
    }
    
    public void setStartMinute(int minute){
        this.startMinute = minute;
    }
    public int getStartMinute(){
        return this.startMinute;
    }
    
    public void setStartSecond(int second){
        this.startSecond = second;
    }
    public int getStartSecond(){
        return this.startSecond;
    }
    
    public void setEndYear(int year){
        this.endYear = year;
    }
    public int getEndYear(){
        return this.endYear;
    }
    
    public void setEndMonth(int month){
        this.endMonth = month;
    }
    public int getEndMonth(){
        return this.endMonth;
    }
    
    public void setEndDay(int day){
        this.endDay = day;
    }
    public int getEndDay(){
        return this.endDay;
    }
    
    public void setEndHour(int hour){
        this.endHour = hour;
    }
    public int getEndHour(){
        return this.endHour;
    }
    
    public void setEndMinute(int minute){
        this.endMinute = minute;
    }
    public int getEndMinute(){
        return this.endMinute;
    }
    
    public void setEndSecond(int second){
        this.endSecond = second;
    }
    public int getEndSecond(){
        return this.endSecond;
    }
}
