/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testNewChart;

import testNewChart.AbstractView3;

/**
 *
 * @author Administrator
 */
public interface AbstractViewFactory {
    abstract AbstractView3 createXYLineChart();
    abstract AbstractView3 createCyclicChart();
}
