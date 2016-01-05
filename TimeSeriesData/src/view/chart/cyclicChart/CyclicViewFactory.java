/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.cyclicChart;

import testNewChart.AbstractView3;
import testNewChart.AbstractViewFactory;

/**
 *
 * @author Administrator
 */
public class CyclicViewFactory implements AbstractViewFactory{

    //@Override
    public AbstractView3 create() {
        return new CyclicChart();
    }

    @Override
    public AbstractView3 createXYLineChart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractView3 createCyclicChart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
