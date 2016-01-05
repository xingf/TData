/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.timeProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class TimeViewPoint{
    public static final String VIEWPOINT_TOTALLY = "TOTALLY";
    public static final String VIEWPOINT_PARTIALLY = "PARTIALLY";
    public static final String VIEWPOINT_PARTIALLY_BRANCHING = "PARTIALLY_BRANCHING";
    public static final String VIEWPOINT_PARTIALLY_MULTIPLE = "PARTIALLY_MULTIPLE";
    private StringProperty timeViewPoint = new SimpleStringProperty();
    private String detail;
    public void setTimeViewPoint(String timeViewPoint){
        this.timeViewPoint.set(timeViewPoint);
    }
    public String getTimeViewPoint(){
        return this.timeViewPoint.get();
    }
    public StringProperty timeViewPoint(){
        return this.timeViewPoint;
    }
    
    public void setDetail(String detail){
        this.detail = detail;
    }
    public String getDetail(){
        return this.detail;
    }
    
}
