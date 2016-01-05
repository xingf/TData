/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testNewChart;

import model.DataModel;

/**
 *
 * @author Administrator
 */
public abstract class MyView {
    protected DataModel dataModel;
    public void setDataModel(DataModel dataModel){
        this.dataModel = dataModel;
    }
    public DataModel getDataModel(){
        return this.dataModel;
    }
   
}
