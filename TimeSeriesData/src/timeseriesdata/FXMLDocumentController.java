/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeseriesdata;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.TouchPoint;
import gesture.GestureEvent;
import gesture.GestureRecognizer;
import gesture.GesturePoint;

/**
 *
 * @author Administrator
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void handleTouchPressedAction(TouchEvent te){
        System.out.println("touch pressed ");
//        System.out.println("getEventSetID " + te.getEventSetId() + " getEventType " + te.getEventType().getName());
//        System.out.println("getTouchCount " + te.getTouchCount());
//        System.out.print("touchPoint: " ); 
//        this.displayTouchPoint(te.getTouchPoint());
//        List points = te.getTouchPoints();
//        if(points.isEmpty()){
//            System.out.println("getTouchPoints is empty");
//        }else{
//            Iterator itr = points.iterator();
//            while(itr.hasNext()){
//                this.displayTouchPoint((TouchPoint)itr.next());
//            }
//        }
//        System.out.println("touch pressed finish --------------------------------------------------------");
//        GestureRecognizer.getGestureRecognitizer().clear();
//        GestureRecognizer.getGestureRecognitizer().addGesturePoint(te, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);   
    }
    
    private void displayTouchPoint(TouchPoint point){
        System.out.println("ID " + point.getId() + " x " + point.getX() + " y " + point.getY() + " state " + point.getState());
    }
    
    @FXML 
    private void handleTouchMovedAction(TouchEvent te){
        System.out.println("touch moved ");
//        GestureRecognizer.getGestureRecognitizer().addGesturePoint(te, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);

        //System.out.println("getEventSetID " + te.getEventSetId() + " getEventType " + te.getEventType().getName());
        //System.out.println("getTouchCount " + te.getTouchCount());
        //System.out.print("touchPoint: " ); 
        //this.displayTouchPoint(te.getTouchPoint());
        //List points = te.getTouchPoints();
//        if(points.isEmpty()){
//            System.out.println("getTouchPoints is empty");
//        }else{
//            Iterator itr = points.iterator();
//            while(itr.hasNext()){
//                this.displayTouchPoint((TouchPoint)itr.next());
//            }
//        }
//        System.out.println("touch moved finish -----------------------------------------------------------");
    }
    @FXML 
    private void handleTouchReleasedAction(TouchEvent te){
//        System.out.println("touch released");
//        GestureRecognizer.getGestureRecognitizer().addGesturePoint(te, System.currentTimeMillis(), GesturePoint.EVENTTYPE_MOUSEEVENT);
//        GestureRecognizer.getGestureRecognitizer().recognize();
    }
    @FXML
    private void handleMousePressedAction(MouseEvent me){
        System.out.println("handled Mouse Pressed Action");
    }
    @FXML 
    private void handleScrollAction(ScrollEvent se){
        System.out.println("handle Scroll Action");
    }
    
    @FXML
    private void handleOnKeyPressedAction(KeyEvent ke){
        System.out.println("handleOnKeyTypedAction");
        System.out.println("keycode: " + ke.getCode().name() + " " +ke.getText());
        GestureEvent ge;
        if(ke.isControlDown()){
            
        }else{
            if(ke.getCode() == KeyCode.A){
                System.out.println("typed A");
                //ge = new GestureEvent(this, GestureEvent.EVENT_A);
                
            }else if(ke.getCode() == KeyCode.B){
                //ge = new GestureEvent(this, GestureEvent.EVENT_B);
               
            }else if(ke.getCode() == KeyCode.S){
                //ge = new GestureEvent(this, GestureEvent.EVENT_S);
            }else if(ke.getCode() == KeyCode.C){
                //ge = new GestureEvent(this, GestureEvent.EVENT_C);
            }else if(ke.getCode() == KeyCode.P){
                //ge = new GestureEvent(this, GestureEvent.EVENT_P);
            }else if(ke.getCode() == KeyCode.O){
                 //ge = new GestureEvent(this, GestureEvent.EVENT_O);
            }else if(ke.getCode() == KeyCode.DIGIT2){
                //ge = new GestureEvent(this, GestureEvent.EVENT_TWO);
            }else if(ke.getCode() == KeyCode.DIGIT1){
                //ge = new GestureEvent(this, GestureEvent.EVENT_ONE);
            }else if(ke.getCode() == KeyCode.D){
                //ge = new GestureEvent(this, GestureEvent.EVENT_D);
            }
            else{
                //ge = new GestureEvent(this, GestureEvent.EVENT_NONE);
                //System.out.println("Not A");
            }
            //GestureManager.getGestureManager().notifyListener(ge);
        }
    }
}
