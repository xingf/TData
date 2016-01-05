/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesture;

import java.awt.geom.Point2D;
import java.util.EventObject;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import view.AbstractChart;



/**
 *
 * @author Administrator
 */
public class GesturePoint {
    static public String EVENTTYPE_MOUSEEVENT = "MouseEvent";
    static public String EVENTTYPE_TOUCHEVENT = "TouchEvent";
    static public String EVENTTYPE_ZOOMEVENT = "ZoomEvent";
    
    private final EventObject eventObject;
    private final long time;
    //private final String eventType;
    
    public GesturePoint(EventObject trackEvent, long time/*, String eventType*/){
        this.eventObject = trackEvent;
        this.time = time;
        //this.eventType = eventType;
    }
    
    public long getTime(){
        return this.time;
    }
    
    public int getCount(){
        if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            return te.getTouchCount();
        }
        else {
            return 1;
        }
    }
    
    public Point2D getLocalPosition(){
        Point2D localPosition = new Point2D.Double();
        if(eventObject instanceof MouseEvent){
            MouseEvent me = (MouseEvent)eventObject;
            localPosition.setLocation(me.getX(), me.getY());
        }else if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            localPosition.setLocation(te.getTouchPoint().getX(), te.getTouchPoint().getY()) ;
        }
        return localPosition;
    }
    
    public Point2D getLocalPosition(int id){
        Point2D localPosition = new Point2D.Double();
        if(eventObject instanceof MouseEvent){
            MouseEvent me = (MouseEvent)eventObject;
            localPosition.setLocation(me.getX(), me.getY());
        }else if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            localPosition.setLocation(te.getTouchPoints().get(id).getX(), te.getTouchPoints().get(id).getY()) ;
        }
        return localPosition;
    }
    
    
    public Point2D getScenePosition(){
        Point2D scenePosition = new Point2D.Double();
        if(eventObject instanceof MouseEvent){
            MouseEvent me = (MouseEvent)eventObject;
            scenePosition.setLocation(me.getSceneX(), me.getSceneY());
        }else if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            
            scenePosition.setLocation(te.getTouchPoint().getSceneX(), te.getTouchPoint().getSceneY()) ;
        }
        return scenePosition;
    }
    
    public Point2D getScenePosition(int id){
        Point2D scenePosition = new Point2D.Double();
        if(eventObject instanceof MouseEvent){
            MouseEvent me = (MouseEvent)eventObject;
            scenePosition.setLocation(me.getSceneX(), me.getSceneY());
        }else if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            
            scenePosition.setLocation(te.getTouchPoints().get(id).getSceneX(), te.getTouchPoints().get(id).getSceneY()); ;
            //scenePosition.setLocation(te.getTouchPoint().getSceneX(), te.getTouchPoint().getSceneY()) ;
        }
        return scenePosition;
    }
    
    
    public Point2D getScreenPosition(){
        Point2D screenPosition = new Point2D.Double();
        if(eventObject instanceof MouseEvent){
            MouseEvent me = (MouseEvent)eventObject;
            screenPosition.setLocation(me.getScreenX(), me.getScreenY());
        }else if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            screenPosition.setLocation(te.getTouchPoint().getScreenX(), te.getTouchPoint().getScreenY()) ;
        }
        return screenPosition;
    }
    
    public Point2D getScreenPosition(int id){
        Point2D screenPosition = new Point2D.Double();
        if(eventObject instanceof MouseEvent){
            MouseEvent me = (MouseEvent)eventObject;
            screenPosition.setLocation(me.getScreenX(), me.getScreenY());
        }else if(eventObject instanceof TouchEvent){
            TouchEvent te = (TouchEvent)eventObject;
            //screenPosition.setLocation(te.getTouchPoint().getScreenX(), te.getTouchPoint().getScreenY()) ;
            screenPosition.setLocation(te.getTouchPoints().get(id).getScreenX(), te.getTouchPoints().get(id).getScreenY());
        }
        return screenPosition;
    }
    
   public boolean isZoomEvent(){
       if(eventObject instanceof ZoomEvent){
           return true;
       }
       return false;
   }
    
   
   
    public AbstractChart getSource(){
         return (AbstractChart)this.eventObject.getSource();
         
    }
}
