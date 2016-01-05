/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.cyclicChart;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.Range;


/**
 *
 * @author Administrator
 */
public class MyJFreeChartCyclicNumberAxis extends NumberAxis{
    @Override
    public void resizeRange(double percent, double anchorValue) {
        if (percent > 0.0) {
            double factor = 0.5;
            double halfLength;
            Range adjusted;
            if(percent > 1){
                //zoom out
                percent -= 1;
                percent *= factor;
                halfLength = this.getRange().getLength() * percent / 2;
                adjusted = new Range(this.getLowerBound(), this.getUpperBound() + halfLength);
            }else{
                //zoom in
                percent *= factor;
                halfLength = this.getRange().getLength() * percent / 2;
                adjusted = new Range(this.getLowerBound(), this.getUpperBound() - halfLength);
            }
           
           
//            Range adjusted = new Range(anchorValue - halfLength,
//                    anchorValue + halfLength);
            System.out.println("percent: " + percent + " halfLength: " + halfLength + "lowerBound: " + this.getLowerBound() + " upperBound: " + this.getUpperBound());
            setRange(adjusted);
        }
        else {
            setAutoRange(true);
        }
    }

}
