/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesture;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import view.AbstractChart;

/**
 *
 * @author Administrator
 */
public class GestureEvent extends EventObject{
    static final public int EVENT_A = 1;
    static final public int EVENT_B = 2;
    static final public int EVENT_C = 3;
    static final public int EVENT_D = 4;
    static final public int EVENT_E = 5;
    static final public int EVENT_F = 6;
    static final public int EVENT_G = 7;
    static final public int EVENT_H = 8;
    static final public int EVENT_I = 9;
    static final public int EVENT_J = 10;
    static final public int EVENT_K = 11;
    static final public int EVENT_L = 12;
    static final public int EVENT_M = 13;
    static final public int EVENT_N = 14;
    static final public int EVENT_O = 15;
    static final public int EVENT_P = 16;
    static final public int EVENT_Q = 17;
    static final public int EVENT_R = 18;
    static final public int EVENT_S = 19;
    static final public int EVENT_T = 20;
    static final public int EVENT_U = 21;
    static final public int EVENT_V = 22;
    static final public int EVENT_W = 23;
    static final public int EVENT_X = 24;
    static final public int EVENT_Y = 25;
    static final public int EVENT_Z = 26;
    static final public int EVENT_ZERO = 27;
    static final public int EVENT_ONE = 28;
    static final public int EVENT_TWO = 29;
    static final public int EVENT_UP = 50;
    static final public int EVENT_DOWN = 51;
    static final public int EVENT_LEFT = 52;
    static final public int EVENT_RIGHT = 53;
    static final public int EVENT_PEAK = 54;
    static final public int EVENT_VALLEY = 55;
    static final public int EVENT_PEAKPEAK = 56;
    static final public int EVENT_VALLAYVALLAY = 57;
    static final public int EVENT_RIGHTHALFCIRCLE = 58;
    static final public int EVENT_DOWNRIGHTARC = 59;
    static final public int EVENT_UPRIGHTARC = 60;
    static final public int EVENT_RIGHTX = 61;
    static final public int EVENT_LEFTCIRCLE = 62;
    static final public int EVENT_SINGLEPOINT = 63;
    static final public int EVENT_DRU_SEMICIRCLE = 64;
    static final public int EVENT_UR_QUDRANT = 65;
    static final public int EVENT_RIGHTUP = 66;
    static final public int EVENT_PINCHIN = 67; 
    static final public int EVENT_PINCHOUT = 68;
    static final public int EVENT_DR_QUDRANT = 69;
    static final public int EVENT_LU_QUDRANT = 70;
    static final public int EVENT_RU_QUDRANT = 71;
    static final public int EVENT_RUL_SEMICIRCLE = 72;
    static final public int EVENT_NONE = -10;
    
    
    static final public HashMap<String, Integer> gestureMap;
    static{
        gestureMap = new HashMap();
        gestureMap.put("a", EVENT_A);
        gestureMap.put("c", EVENT_C);
        gestureMap.put("d", EVENT_D);
        gestureMap.put("e", EVENT_E);
        gestureMap.put("p", EVENT_P);
        gestureMap.put("s", EVENT_S);
        gestureMap.put("v", EVENT_V);
        gestureMap.put("up", EVENT_UP);
        gestureMap.put("down", EVENT_DOWN);
        gestureMap.put("left", EVENT_LEFT);
        gestureMap.put("right", EVENT_RIGHT);
        gestureMap.put("peak", EVENT_PEAK);
        gestureMap.put("valley", EVENT_VALLEY);
        gestureMap.put("peakpeak", EVENT_PEAKPEAK);
        gestureMap.put("vallayvallay", EVENT_VALLAYVALLAY);
        gestureMap.put("down_right_arc", EVENT_DOWNRIGHTARC);
        gestureMap.put("up_right_arc", EVENT_UPRIGHTARC);
        gestureMap.put("righthalfcircle", EVENT_RIGHTHALFCIRCLE);
        gestureMap.put("rightx", EVENT_RIGHTX);
        gestureMap.put("leftcircle", EVENT_LEFTCIRCLE);
        gestureMap.put("singlepoint", EVENT_SINGLEPOINT);
        gestureMap.put("dru_semicircle", EVENT_DRU_SEMICIRCLE);
        gestureMap.put("ur_qudrant", EVENT_UR_QUDRANT);
        gestureMap.put("right_up", EVENT_RIGHTUP);
        gestureMap.put("pinchin", EVENT_PINCHIN);
        gestureMap.put("pinchout", EVENT_PINCHOUT);
        gestureMap.put("dr_qudrant", EVENT_DR_QUDRANT);
        gestureMap.put("lu_qudrant", EVENT_LU_QUDRANT);
        gestureMap.put("ru_qudrant", EVENT_RU_QUDRANT);
        gestureMap.put("rul_semicircle", EVENT_RUL_SEMICIRCLE);
        
    }
    
    private int gesture = -1;
    
    private ArrayList<GesturePoint> gesturePoints = new ArrayList();
    
    public GestureEvent(int gesture, ArrayList<GesturePoint> gesturePoints){
        super(0);
        this.setGesture(gesture);
        this.gesturePoints = gesturePoints;
    }
    
    public int getGesture(){
        return this.gesture;
    }
    
    public void setGesture(int gesture){
        this.gesture = gesture;
    }
    
    public ArrayList<GesturePoint> getGesturePointsInArrayList(){
        return this.gesturePoints;
    }
    
    public ArrayList<Point2D> getScenePositionsOfGesturePointsInArrayList(){
        ArrayList<Point2D> positions = new ArrayList();
        for(int i = 0; i < this.gesturePoints.size(); i++){
            positions.add(this.gesturePoints.get(i).getScenePosition());
        }
        return positions;
    }
    
    public ArrayList<Long> getTimesOfGesturePointsInArrayList(){
        ArrayList<Long> times = new ArrayList();
        for(int i = 0; i < this.gesturePoints.size(); i++){
            times.add(this.gesturePoints.get(i).getTime());
        }
        return times;
    }
    
    private double getMaxY() {
        double max = Double.MIN_VALUE;
        ArrayList<Point2D> positions = this.getScenePositionsOfGesturePointsInArrayList();
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getY() > max) {
                max = positions.get(i).getY();
            }
        }
        return max;
    }

    private  double getMaxX() {
        ArrayList<Point2D> positions = this.getScenePositionsOfGesturePointsInArrayList();
        double max = Double.MIN_VALUE;
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getX() > max) {
                max = positions.get(i).getX();
            }
        }
        return max;
    }

    private  double getMinY() {
        ArrayList<Point2D> positions = this.getScenePositionsOfGesturePointsInArrayList();
        double min = Double.MAX_VALUE;
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getY() < min) {
                min = positions.get(i).getY();
            }
        }
        return min;
    }

    private double getMinX() {
        ArrayList<Point2D> positions = this.getScenePositionsOfGesturePointsInArrayList();
        double min = Double.MAX_VALUE;
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).getX() < min) {
                min = positions.get(i).getX();
            }
        }
        return min;
    }
    
   public Object getStartGesturePointSource(){
         return this.gesturePoints.get(0).getSource();
    }
   
   public Point2D getStartGestureLocalPosition(){
       return this.gesturePoints.get(0).getLocalPosition();
   }
   
   public Point2D getLastGestureLocalPosition(){
       return this.gesturePoints.get(this.gesturePoints.size() - 1).getLocalPosition();
   }
   
   public Point2D getStartGestureScenePosition(){
       return this.gesturePoints.get(0).getScenePosition();
   }
   
   public Point2D getStartGestureScreenPosition(){
       return this.gesturePoints.get(0).getScreenPosition();
   }
   
   public Point2D getLastGestureScreenPosition(){
       return this.gesturePoints.get(this.gesturePoints.size() - 1).getScreenPosition();
   }
   
   public boolean isLocalGesture(){
       Object startSource = this.gesturePoints.get(0).getSource();
       for(int i = 1; i < this.gesturePoints.size() ; i++){
           if(!startSource.equals( this.gesturePoints.get(i).getSource())){
               return false;
           }
       }
       return true;
   }
   
   public boolean isSceneGesture(){
       Object startSource = this.gesturePoints.get(0).getSource();
       for(int i = 1; i < this.gesturePoints.size() ; i++){
           if(!startSource.equals( this.gesturePoints.get(i).getSource())){
               return true;
           }
       }
       return false;
   }
    
   public AbstractChart getStartSource(){
       return this.gesturePoints.get(0).getSource();
   }
   
   
   public double getGestureHeight(){
        double minY = this.getMinY();
        double maxY = this.getMaxY();
        double height = Math.abs(minY - maxY);
        return height;
   }
   
   public double getGestureWidth(){
       double minX = this.getMinX();
        double maxX = this.getMaxX();
        double width = Math.abs(minX - maxX);
        return width;
   }
   
   
}
