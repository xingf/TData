/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dataProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class DataFrameOfReference {
   
    private String dataFrameOfReferece = new String();
    public void setDataFrameOfReference(String frameOfReference){
        this.dataFrameOfReferece = frameOfReference;
    }
    public String getDataFrameOfReference(){
        return this.dataFrameOfReferece;
    }
    public DataFrameOfReference dataFrameReference(){
        return this;
    }
    public DataFrameOfReference(){
        System.out.println("constructe DataFrameOfReference");
    }
    
    static final String DATAFRAMEOFREFERENCE_ABSTRACT = "ABSTRACT";
    static final String DATAFRAMEOFREFERENCE_SPATIAL = "SPATIAL";
}
