/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dataProperty;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Administrator
 */
public class DataProperty {
    public static final String TYPE_DOUBLE = "DOUBLE";
    protected String type;
    public void setType(String type){
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
    
    static final String SCALE_QUANTITATIVE = "QUANTITATIVE";
    static final String SCALE_QULITATIVE = "QUALITATIVE";
    protected StringProperty scale = new SimpleStringProperty();
    public String getScale(){
        return this.scale.get();
    }
    public void setScale(String scale){
        this.scale.set(scale);
    }
    public StringProperty scale(){
        return this.scale;
    }
    
    
    private SimpleObjectProperty frameOfReference = new SimpleObjectProperty<DataFrameOfReference>();
    public void setFrameOfReference(DataFrameOfReference frameOfReference){
        this.frameOfReference.set(frameOfReference);
    }
    public DataFrameOfReference getFrameOfReference(){
        return (DataFrameOfReference)this.frameOfReference.get();
    }
    public SimpleObjectProperty dataFrameOfReference(){
        return this.frameOfReference;
    }
    
    public static final String DATAKIND_EVENT = "EVENT";
    public static final String DATAKIND_STATE = "STATE";
    private StringProperty dataKind = new SimpleStringProperty();
    public void setDataKind(String dataKind){
        this.dataKind.set(dataKind);
    }
    public String getDataKind(){
        return this.dataKind.get();
    }
    public StringProperty dataKind(){
        return this.dataKind;
    }
    
    
    
    private SimpleObjectProperty numberOfVariables = new SimpleObjectProperty<NumberOfVariables>();
    public void setNumberOfVariables(NumberOfVariables numberOfVariables){
        this.numberOfVariables.set(numberOfVariables);
    }
    public NumberOfVariables getNumberOfVariables(){
        return (NumberOfVariables)this.numberOfVariables.get();
    }
    public SimpleObjectProperty numberOfVariables(){
        return this.numberOfVariables;
    }

    public static final String DIMENSIONALITY_UNI_DIM = "uni_dim";
    public static final String DIMENSIONALITY_TWO_DIM = "two_dim";
    public static final String DIMENSIONALITY_THREE_DIM = "three_dim";
    public static final String DIMENSIONALITY_MUL_DIM = "multi_dim";
    public static final String DIMENSIONALITY_HIGH_DIM = "high_dim";
    
    private StringProperty dimensionality = new SimpleStringProperty();
    public void setDimensionality(String dimensionality){
        this.dimensionality.set(dimensionality);
    }
    public String getDimensionality(){
        return this.dimensionality.get();
    }
    public StringProperty dimensionality(){
        return this.dimensionality;
    }
    
    private  DataProperty(){
        this.setType(TYPE_DOUBLE);
        
        
        
        //MISC/QBIRTHS.1
        this.setScale(DataProperty.SCALE_QUANTITATIVE);
        frameOfReference = new SimpleObjectProperty<DataFrameOfReference>();
        DataFrameOfReference dfor = new DataFrameOfReference();
        dfor.setDataFrameOfReference(DataFrameOfReference.DATAFRAMEOFREFERENCE_ABSTRACT);
        this.setFrameOfReference(dfor);
        this.setDataKind(DataProperty.DATAKIND_STATE);
        NumberOfVariables nov = new NumberOfVariables();
        nov.setNumberOfVariables(NumberOfVariables.NUMBEROFVARIABLES_UNIVARIATE);
        this.setNumberOfVariables(nov);
        this.setDimensionality(DataProperty.DIMENSIONALITY_TWO_DIM);
        
        
        //BOXJENK/SERIESA.1
//        this.setScale(DataProperty.SCALE_QUANTITATIVE);
//        frameOfReference = new SimpleObjectProperty<DataFrameOfReference>();
//        DataFrameOfReference dfor = new DataFrameOfReference();
//        dfor.setDataFrameOfReference(DataFrameOfReference.DATAFRAMEOFREFERENCE_ABSTRACT);
//        this.setFrameOfReference(dfor);
//        this.setDataKind(DataProperty.DATAKIND_EVENT);
//        NumberOfVariables nov = new NumberOfVariables();
//        nov.setNumberOfVariables(NumberOfVariables.NUMBEROFVARIABLES_UNIVARIATE);
//        this.setNumberOfVariables(nov);
//        this.setDimensionality(DataProperty.DIMENSIONALITY_TWO_DIM);
    }
    
    static DataProperty dataProperty = null;
    public static DataProperty getDataProperty(){
        if(dataProperty == null){
            dataProperty = new DataProperty();
        }
        return dataProperty;
    }
    
    
}
