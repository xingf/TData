/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
import javafx.embed.swing.SwingNode;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TouchPoint;
import model.Metadata;
import gesture.GestureRecognizer;
import java.awt.Point;
import javafx.scene.input.ZoomEvent;
import view.chart.sharedComponents.MyChartPanel;

/**
 *
 * @author Administrator
 */
public abstract class AbstractChart extends SwingNode implements IAbstractChart{
    protected Metadata hightlightData = new Metadata();
    
    String ID;
    public void setID(String id){
        this.ID = id;
    }
    public String getID(){
        return this.ID;
    }
    
    public static String ID_MAIN_VIEW = "MAIN_VIEW";
    public static String ID_EXPECTION_VIEW = "EXPECTION_VIEW";
    public static String ID_LOCALMAX_VIEW = "LOCALMAX_VIEW";
    public static String ID_LOCALMIN_VIEW = "LOCALMIN_VIEW";
    public static String ID_GLOBALMAX_VIEW ="GLOBALMAX_VIEW";
    public static String ID_GLOBALMIN_VIEW = "GLOBALMIN_VIEW";
    boolean first = true;
    boolean cancelZooming = true;
    public void setOnEventHandler(){
        this.setOnTouchPressed(new EventHandler<TouchEvent>(){
            @Override
            public void handle(TouchEvent event) {
                GestureRecognizer.getGestureRecognitizer().clear();
                GestureRecognizer.getGestureRecognitizer().addGesturePoint(event, System.currentTimeMillis());
                if(event.getTouchCount() == 3 && first == true){
                    MyChartPanel cp = (MyChartPanel) getContent();
                    cp.panStart((int)event.getTouchPoint().getSceneX(), (int)event.getTouchPoint().getSceneY());
                    first = false;
                    cancelZooming = true;
                    event.consume();
                }
//                System.out.println(""
//                        + ""
//                        + ""
//                        + "Touch Pressed");
//                System.out.println("getEventSetID:  " + event.getEventSetId());
//                EventType<TouchEvent> et = event.getEventType();
//                System.out.println("Event Type: " + et.getName() + "; TouchCount: " + event.getTouchCount());
//                TouchPoint p = event.getTouchPoint();
//                System.out.println("Touch Point-- ID:  " + p.getId() + ", location: (" + p.getX() +", " + p.getY() + "),  sceneXY(" + p.getSceneX() + ", " + p.getSceneY() + "), screenxy(" + p.getScreenX()+ ", " + p.getScreenY()+")");
//                List<TouchPoint> pl = event.getTouchPoints();
//                System.out.println("------------------------------------------------------------------");
//                for(TouchPoint tp:pl){
//                    
//                    System.out.println("Touch Points-- ID:  " + tp.getId() + ", location: (" + tp.getX() +", " + tp.getY() + "),  sceneXY(" + tp.getSceneX() + ", " + tp.getSceneY() + "), screenxy(" + tp.getScreenX()+ ", " + tp.getScreenY()+")");
//                }
//                System.out.println("------------------------------------------------------------------");
                event.consume();
            }
        });
        
        
        this.setOnTouchMoved(new EventHandler<TouchEvent>(){
            @Override
            public void handle(TouchEvent event) {
                GestureRecognizer.getGestureRecognitizer().addGesturePoint(event, System.currentTimeMillis());
                if(event.getTouchCount() == 3 && first == true){
                    MyChartPanel cp = (MyChartPanel) getContent();
                    cp.panStart((int)event.getTouchPoint().getSceneX(), (int)event.getTouchPoint().getSceneY());
                    first = false;
                    cancelZooming = true;
                    event.consume();
                }else if(event.getTouchCount() == 3){
                    MyChartPanel cp = (MyChartPanel) getContent();
                    cp.panning((int)event.getTouchPoint().getSceneX(), (int)event.getTouchPoint().getSceneY());
                    //cancelZooming = true;
                    event.consume();
                }
//                System.out.println(""
//                        + ""
//                        + ""
//                        + "Touch Moved");
//                System.out.println("getEventSetID:  " + event.getEventSetId());
//                EventType<TouchEvent> et = event.getEventType();
//                System.out.println("Event Type: " + et.getName() + "; TouchCount: " + event.getTouchCount());
//                TouchPoint p = event.getTouchPoint();
//                System.out.println("Touch Point-- ID:  " + p.getId() + ", location: (" + p.getX() +", " + p.getY() + "),  sceneXY(" + p.getSceneX() + ", " + p.getSceneY() + "), screenxy(" + p.getScreenX()+ ", " + p.getScreenY()+")");
//                List<TouchPoint> pl = event.getTouchPoints();
//                System.out.println("------------------------------------------------------------------");
//                for(TouchPoint tp:pl){
//                    
//                    System.out.println("Touch Points-- ID:  " + tp.getId() + ", location: (" + tp.getX() +", " + tp.getY() + "),  sceneXY(" + tp.getSceneX() + ", " + tp.getSceneY() + "), screenxy(" + tp.getScreenX()+ ", " + tp.getScreenY()+")");
//                }
//                System.out.println("------------------------------------------------------------------");
                event.consume();
            }
        });
        this.setOnTouchReleased(new EventHandler<TouchEvent>(){
            @Override
            public void handle(TouchEvent event) {
                GestureRecognizer.getGestureRecognitizer().recognize();
                MyChartPanel cp = (MyChartPanel) getContent();
                cp.panFinished();
                first = true;
                if(event.getTouchCount() == 1)
                cancelZooming = false;
               
//                System.out.println(""
//                        + ""
//                        + ""
//                        + "Touch Released");
//                System.out.println("getEventSetID:  " + event.getEventSetId());
//                EventType<TouchEvent> et = event.getEventType();
//                System.out.println("Event Type: " + et.getName() + "; TouchCount: " + event.getTouchCount());
//                TouchPoint p = event.getTouchPoint();
//                System.out.println("Touch Point-- ID:  " + p.getId() + ", location: (" + p.getX() +", " + p.getY() + "),  sceneXY(" + p.getSceneX() + ", " + p.getSceneY() + "), screenxy(" + p.getScreenX()+ ", " + p.getScreenY()+")");
//                List<TouchPoint> pl = event.getTouchPoints();
//                System.out.println("------------------------------------------------------------------");
//                for(TouchPoint tp:pl){
//                    
//                    System.out.println("Touch Points-- ID:  " + tp.getId() + ", location: (" + tp.getX() +", " + tp.getY() + "),  sceneXY(" + tp.getSceneX() + ", " + tp.getSceneY() + "), screenxy(" + tp.getScreenX()+ ", " + tp.getScreenY()+")");
//                }
//                System.out.println("------------------------------------------------------------------");
                event.consume();
            }
        });
        this.setOnTouchStationary(new EventHandler<TouchEvent>(){
            @Override
            public void handle(TouchEvent event) {
//                System.out.println(""
//                        + ""
//                        + ""
//                        + "Touch Stationary");
//                System.out.println("getEventSetID:  " + event.getEventSetId());
//                EventType<TouchEvent> et = event.getEventType();
//                System.out.println("Event Type: " + et.getName() + "; TouchCount: " + event.getTouchCount());
//                TouchPoint p = event.getTouchPoint();
//                System.out.println("Touch Point-- ID:  " + p.getId() + ", location: (" + p.getX() +", " + p.getY() + "),  sceneXY(" + p.getSceneX() + ", " + p.getSceneY() + "), screenxy(" + p.getScreenX()+ ", " + p.getScreenY()+")");
//               List<TouchPoint> pl = event.getTouchPoints();
//                System.out.println("------------------------------------------------------------------");
//                for(TouchPoint tp:pl){
//                    
//                    System.out.println("Touch Points-- ID:  " + tp.getId() + ", location: (" + tp.getX() +", " + tp.getY() + "),  sceneXY(" + tp.getSceneX() + ", " + tp.getSceneY() + "), screenxy(" + tp.getScreenX()+ ", " + tp.getScreenY()+")");
//                }
//                System.out.println("------------------------------------------------------------------");
                event.consume();
            }
        });
        
        this.setOnZoom(new EventHandler<ZoomEvent>() {

            @Override
            public void handle(ZoomEvent event) {
                
                if(cancelZooming){
                    return;
                }
                double zoomFactor = event.getTotalZoomFactor();
                double zooEventFactor = event.getZoomFactor();
               
                MyChartPanel cp = (MyChartPanel) getContent();
                int direction = 0;
                if(event.getTotalZoomFactor() > 1){
                    //Zoom In
                    direction = -1;
                }else{
                    //Zoom Out
                    direction = 1;
                }
                cp.zoomHandler.pinchMoved(new Point((int)event.getSceneX(), (int)event.getSceneY()), direction);
                //cp.zoomHandler.pinchMoved(new Point((int)event.getSceneX(), (int)event.getSceneY()), event.getZoomFactor());
                //GestureRecognizer.getGestureRecognitizer().addGesturePoint(event, System.currentTimeMillis());
                event.consume();
            }
        });
        
        
        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //System.out.println("Clear Gesture Track");
                GestureRecognizer.getGestureRecognitizer().clear();
                //System.out.println("Add First Gesture Point: (" + event.getSceneX() + ", " + event.getSceneY() + ") When Pressed");
                GestureRecognizer.getGestureRecognitizer().addGesturePoint(event, System.currentTimeMillis());
                
            } 
        });
        
        this.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("Add Following Gesture Point: (" + event.getSceneX() + ", " + event.getSceneY() + ")");
                GestureRecognizer.getGestureRecognitizer().addGesturePoint(event, System.currentTimeMillis());
//                MyChartPanel cp = (MyChartPanel) getContent();
//                cp.handleSelectOrRestore(event);
            }
        });
        
        this.setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("Start Recognize ---------------------------------------------------------------");
                GestureRecognizer.getGestureRecognitizer().recognize();
            }
        });
        
        this.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
//                System.out.println("MouseClicked: scene(" + event.getSceneX() + ", " + event.getSceneY() + "), local(" + 
//                        event.getX() + ", " + event.getY() + ")");
            }
        });
    }
}
