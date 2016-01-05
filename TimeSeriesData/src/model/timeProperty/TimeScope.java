/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.timeProperty;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class TimeScope {
    public static final String SCOPE_POINT = "POINT";
    public static final String SCOPE_INTERVAL = "INTERVAL";
    
    private StringProperty timeScope = new SimpleStringProperty();
    private DoubleProperty timeExtend = new SimpleDoubleProperty();
    
    public void setTimeScope(String scopeType){
        timeScope.set(scopeType);
    }
    public String getTimeScope(){
        return timeScope.get();
    }
    public StringProperty timeScope(){
        return this.timeScope;
    }
    
    public void setTimeExtend(double timeExtend){
        this.timeExtend.set(timeExtend);
    } 
    public Double getTimeExtend(){
        return this.timeExtend.get();
    }
    public DoubleProperty timeExtend(){
        return this.timeExtend;
    }
}
