/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dataProperty;


/**
 *
 * @author Administrator
 */
public class NumberOfVariables {
    private String numberOfVariables;

    public String getNumberOfVariables() {
        return this.numberOfVariables;
    }

    public void setNumberOfVariables(String numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
    }
    public NumberOfVariables numberOfVariables(){
        return this;
    }
    
    static final String NUMBEROFVARIABLES_UNIVARIATE = "UNIVARIATE";
    static final String NUMBEROFVARIABLES_MULTIVARIATE = "MULTIVARIATE";
}
