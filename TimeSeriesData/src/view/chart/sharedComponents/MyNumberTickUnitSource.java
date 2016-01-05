/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.chart.sharedComponents;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.NumberTickUnitSource;
import org.jfree.chart.axis.TickUnit;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.util.ObjectUtilities;

/**
 *
 * @author Administrator
 */
public class MyNumberTickUnitSource implements TickUnitSource, Serializable {

    private boolean integers;
    
    private int power = 0;
    
    private int factor = 1;
    
    private int multiply = 1;
    
    public void setMultiply(int multiply){
        this.multiply = multiply;
    }
    
    public int getMultiply(){
        return this.multiply;
    }
    
    /** The number formatter to use (an override, it can be null). */
    private NumberFormat formatter;

    /**
     * Creates a new instance.
     */
    public MyNumberTickUnitSource() {
        this(false);
    }
    
    /**
     * Creates a new instance.
     * 
     * @param integers  show integers only. 
     */
    public MyNumberTickUnitSource(boolean integers) {
        this(integers, null);
    }
    
    public MyNumberTickUnitSource(boolean integers,int multiply){
        this(integers);
        this.multiply = multiply;
    }
    
    /**
     * Creates a new instance.
     * 
     * @param integers  show integers only?
     * @param formatter  a formatter for the axis tick labels ({@code null} 
     *         permitted).
     */
    public MyNumberTickUnitSource(boolean integers, NumberFormat formatter) {
        this.integers = integers;
        this.formatter = formatter;
        this.power = 0;
        this.factor = 1;
    }
    
    @Override
    public TickUnit getLargerTickUnit(TickUnit unit) {
        System.out.println("getLargerTickUnit(TickUnit unit) ");
        TickUnit t = getCeilingTickUnit(unit);
        if (t.equals(unit)) {
            next();
            t = new NumberTickUnit(getTickSize(), getTickLabelFormat(), 
                    getMinorTickCount());
        }
        
        return t; 
    }

    @Override
    public TickUnit getCeilingTickUnit(TickUnit unit) {
        System.out.println("getCeilingTickUnit(TickUnit unit) ");
        
        return getCeilingTickUnit(unit.getSize());
    }

    @Override
    public TickUnit getCeilingTickUnit(double size) {
        System.out.println("getCeilingTickUnit(double size) ");
        if (Double.isInfinite(size)) {
            throw new IllegalArgumentException("Must be finite.");
        }
        this.power = (int) Math.ceil(Math.log10(size));
        if (this.integers) {
            power = Math.max(this.power, 0);
        }
        this.factor = 1;
        boolean done = false;
        // step down in size until the current size is too small or there are 
        // no more units
        while (!done) {
            done = !previous();
            double tickSize = getTickSize();
            if (tickSize < size) {
                next();
                done = true;
            }
        }
       
        return new NumberTickUnit(getTickSize(), getTickLabelFormat(), 
                getMinorTickCount());
    }
    
    private boolean next() {
        System.out.println("next()");
        
        if (factor == 1) {
            factor = 2;
            return true;
        }
        if (factor == 2) {
            factor = 5;
            return true;
        }
        if (factor == 5) {
            if (power == 300) {
                return false;
            }
            power++;
            factor = 1;
            return true;
        } 
        throw new IllegalStateException("We should never get here.");
    }

    private boolean previous() {
        System.out.println("previous() ");
        
        if (factor == 1) {
            if (this.integers && power == 0 || power == -300) {
                return false;
            }
            factor = 5;
            power--;
            return true;
        } 
        if (factor == 2) {
            factor = 1;
            return true;
        }
        if (factor == 5) {
            factor = 2;
            return true;
        } 
        throw new IllegalStateException("We should never get here.");
    }

    private double getTickSize() {
        System.out.println("getTickSize()");
        
        return this.factor * Math.pow(10.0, this.power) * this.multiply;
    }
    
    private DecimalFormat dfNeg4 = new DecimalFormat("0.0000");
    private DecimalFormat dfNeg3 = new DecimalFormat("0.000");
    private DecimalFormat dfNeg2 = new DecimalFormat("0.00");
    private DecimalFormat dfNeg1 = new DecimalFormat("0.0");
    private DecimalFormat df0 = new DecimalFormat("#,##0");
    private DecimalFormat df = new DecimalFormat("#.######E0");
    
    private NumberFormat getTickLabelFormat() {
        System.out.println("getTickLabelFormat()");
        if (this.formatter != null) {
            return this.formatter;
        }
        if (power == -4) {
            return dfNeg4;
        }
        if (power == -3) {
            return dfNeg3;
        }
        if (power == -2) {
            return dfNeg2;
        }
        if (power == -1) {
            return dfNeg1;
        }
        if (power >= 0 && power <= 6) {
            return df0;
        }
        
        return df;
    }
    
    private int getMinorTickCount() {
        System.out.println("getMinorTickCount()");
        if (factor == 1) {
            return 10;
        } else if (factor == 5) {
            return 5;
        }
        
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        System.out.println("equals(Object obj)");
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberTickUnitSource)) {
            return false;
        }
        MyNumberTickUnitSource that = (MyNumberTickUnitSource) obj;
        if (this.integers != that.integers) {
            return false;
        }
        if (!ObjectUtilities.equal(this.formatter, that.formatter)) {
            return false;
        }
        if (this.power != that.power) {
            return false;
        }
        if (this.factor != that.factor) {
            return false;
        }
        
        
        return true;
    }
}

