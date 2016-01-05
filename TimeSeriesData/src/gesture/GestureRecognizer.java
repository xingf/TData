/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template strokeFile, choose Tools | Templates
 * and open the template in the editor.
 */
package gesture;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import gesture.parser.GestureLexer;
import gesture.parser.GestureParser;
import javafx.scene.input.ZoomEvent;
import toolkit.Toolkit;

/**
 *
 * @author Administrator
 */
public class GestureRecognizer {
    private ArrayList<GesturePoint> gesturePoints = null;
    protected static GestureRecognizer  gestureRecognizer = null;
    private File strokeFile = new File("C:\\SelfProgram\\ANTLRWORK\\WS\\input.txt");
    private FileWriter strokeFileWriter;
    
    private GestureRecognizer(){
        gesturePoints = new ArrayList<>();
        try {
            this.strokeFileWriter = new FileWriter(strokeFile, false);
        } catch (IOException ex) {
            Logger.getLogger(GestureRecognizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static GestureRecognizer getGestureRecognitizer(){
        if(gestureRecognizer == null){
            gestureRecognizer = new GestureRecognizer();
        }
        return gestureRecognizer;
    }
    
    //不断添加手势轨迹点，用以进行收拾判断
    public void addGesturePoint(EventObject eventObject, long time){
        this.gesturePoints.add(new GesturePoint(eventObject, time));
        
    }
    
    
    private int getPointMaxCount(){
        int max = 1;
        if(this.gesturePoints.size() != 0){
            for(GesturePoint gp: gesturePoints){
                if(gp.getCount() > max){
                    max = gp.getCount();
                }
            }
            return max;
        }else{
            return 0;
        }
    }
    
    ArrayList<GesturePoint> getComplexGesturePoints(){
        ArrayList<GesturePoint> complexGesturePoints = new ArrayList();
        if(this.gesturePoints != null){
            for(GesturePoint gp: this.gesturePoints){
                if(gp.getCount() > 1){
                    complexGesturePoints.add(gp);
                }
            }
            return complexGesturePoints;
        }
        return null;
    }
    
//    boolean containsZoomEvent(){
//        for(GesturePoint p : this.gesturePoints){
//            if(p.isZoomEvent())return true;
//        }
//        return false;
//    }
//    
//    private ZoomEvent getZoomEvent(){
//        for(GesturePoint p : this.gesturePoints){
//            if(p.isZoomEvent())return p;
//        }
//        return null;
//    }
    
    public void recognize(){

        
        if(this.getPositions(gesturePoints).size() < 5){
           int gestureInt = GestureEvent.gestureMap.get("singlepoint");
           GestureEvent ge = new GestureEvent(gestureInt, this.gesturePoints);
           GestureEventManager.getGestureManager().notifyListener(ge);
            return;
        }else if(getPointMaxCount() > 1){
            return;
        }
        
//        if(getPointMaxCount() == 2){
//            System.out.println("Twi Gesture");
//            ArrayList<GesturePoint> twiGesturePoints = this.getComplexGesturePoints();
//            int in = 0;
//            int out = 0;
//            for(int i = 1; i < twiGesturePoints.size(); i++ ){
//               GesturePoint gp = twiGesturePoints.get(i);
//               Point2D pos1 = gp.getLocalPosition(0);
//               Point2D pos2 = gp.getLocalPosition(1);
//               
//               GesturePoint gp2 = twiGesturePoints.get(i - 1);
//               Point2D pos3 = gp2.getLocalPosition(0);
//               Point2D pos4 = gp2.getLocalPosition(1);
//               
//               double distance1 = Math.hypot((pos1.getX() - pos3.getX()), (pos1.getY() - pos3.getY()));
//               double distance2 = Math.hypot((pos3.getX() - pos4.getX()), (pos3.getY() - pos4.getY()));
//               if(Math.hypot((pos1.getX() - pos2.getX()), (pos1.getY() - pos2.getY()))
//                  > Math.hypot((pos3.getX() - pos4.getX()), (pos3.getY() - pos4.getY()))  ){
//                   out++;
//               }else if(Math.hypot((pos1.getX() - pos2.getX()), (pos1.getY() - pos2.getY()))
//                  < Math.hypot((pos3.getX() - pos4.getX()), (pos3.getY() - pos4.getY()))){
//                   in++;
//               }
//            }
//            
//            if(in >  out){
//                int gestureInt = GestureEvent.gestureMap.get("pinchin");
//               GestureEvent ge = new GestureEvent(gestureInt, this.gesturePoints);
//               System.out.println("Recognized Gesture: --pinchin--");
//               GestureEventManager.getGestureManager().notifyListener(ge);
//            }else{
//                 int gestureInt = GestureEvent.gestureMap.get("pinchout");
//               GestureEvent ge = new GestureEvent(gestureInt, this.gesturePoints);
//               System.out.println("Recognized Gesture: --pinchout--");
//               GestureEventManager.getGestureManager().notifyListener(ge);
//            }
//            return; 
//        }
//        
//        if(this.containsZoomEvent()){
//            ZoomEvent ze = 
//        }
       
//        if(this.getPointMaxCount() > 1){
//            return;
//        }
        
        System.out.println("start recognition ...");
       //String direction = this.toDirection();
       String strokesString=" ";
       ArrayList<Integer> strokes = this.calStrokes();
       this.saveStrokes(strokes);
       
       this.showGesture(strokes);
       for(int stroke:strokes){
           strokesString += stroke + " ";
       }
       strokesString = strokesString.trim();
       CharStream cs = (CharStream) new ANTLRInputStream(strokesString);
       GestureLexer lexer = new GestureLexer(cs);
       CommonTokenStream tokens = new CommonTokenStream(lexer);
      
       GestureParser parser = new GestureParser(tokens);
       ParseTree tree = parser.gesture();
         
       String[] s = tree.toStringTree(parser).split(" ");
      if(s.length > 2){
          if(s[1].length() > 2){
              String gesture = s[1].substring(1).trim();
              System.out.println("Recognized Gesture: --" + gesture + "--");
              if (!gesture.isEmpty()) {
                  int gestureInt = GestureEvent.gestureMap.get(gesture);
                  GestureEvent ge = new GestureEvent(gestureInt, this.gesturePoints);
                  GestureEventManager.getGestureManager().notifyListener(ge);
              } else {
                  System.out.println("recognition failed");
              }
          }
      }else{
          System.out.println("recognition failed");
      }
       
    }
    
    private void saveStrokes(ArrayList<Integer> strokes){
        try {
            String strokesString = " ";
            for(int stroke:strokes){
                strokesString += stroke + " ";
            }
            
            FileWriter fw = new FileWriter(this.strokeFile, false);
            fw.write(strokesString.trim());
            fw.flush();
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(GestureRecognizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private GestureStrokeGraph gestureStrokeGraph = null;
    private boolean showGesture(ArrayList strokes){
        
        if(gestureStrokeGraph == null){
            gestureStrokeGraph = new GestureStrokeGraph(strokes);
        }else{
            gestureStrokeGraph.setStrokes(strokes);
        }
        return true;
    }
    
    public boolean clear(){
        this.gesturePoints.clear();
        return true;
    }
    
    private ArrayList<Point2D> getPositions(ArrayList<GesturePoint> gesturePoints){
        ArrayList<Point2D> positions = new ArrayList();
        for (GesturePoint gesturePoint : gesturePoints) {
            positions.add(gesturePoint.getScenePosition());          
        }
        return positions;
    }
    
     private ArrayList calAngles(ArrayList<Point> points){ 
        ArrayList<Double> angles = new ArrayList();
        double lastAngle = 0.0;
//        for(int idx = 1; idx < points.size(); idx ++){
//            //double angle = Toolkit.calAngleToRefPositionByPosition(points.get(idx), points.get(idx - 1));
//            
//            double angle = Toolkit.calAngleToRefPositionByPosition(points.get(idx), points.get(idx - 1));
//            angles.add(angle);  
//        }
        
        for(int idx = 7; idx < points.size(); idx= idx + 7){
            //double angle = Toolkit.calAngleToRefPositionByPosition(points.get(idx), points.get(idx - 1));
            
            double angle = Toolkit.calAngleToRefPositionByPosition(points.get(idx), points.get(idx - 7));
            angles.add(angle);  
        }
        return angles;
    }
    
     private ArrayList calStrokes(){
        ArrayList positions = this.getPositions(this.gesturePoints);
        ArrayList angles = this.calAngles(positions);
        ArrayList<Integer> strokes = new ArrayList();
       // ArrayList<Double> tAngles = this.translatedAngles(angles);
       // ArrayList<Double> newAngles = this.anglesPreprocess(angles);
        //ArrayList<Double> tbnewAngles = this.translatedBackAngles(newAngles);
        Iterator itr = angles.iterator();
        
        /*  angle map to stroke
            [337.5, 360) || [0,22.5)  UP=1
            [22.5,67.5)  RIGHT_UP=2
            [67.5,112.5) RIGHT=3
            [112.5,157.5) RIGHT_DOWN=4
            [157.5,202.5] DOWN=5
            [202.5, 247.5) LEFT_DOWN=6
            [247.5,292.5) LEFT=7
            [292.5,337.5) LEFT_UP=8
        */
        
        while(itr.hasNext()){
            double angle = (double)itr.next();
            int stroke = (int)((angle + 22.5)/45 + 1);
            while(stroke > 8){
                stroke = stroke - 8;
            }
            
            if(stroke == 0){
                System.out.println("Something is wrong -- from GestureRecognizer calStrokes()");
            }
            strokes.add(stroke);
        }
        
        System.out.println("Angles:  " + angles.toString());
        System.out.println("Strokes: " + strokes.toString());
//        System.out.println("newAngles :" + newAngles);
//        Iterator itr1 = newAngles.iterator();
//        ArrayList<Integer> newStrokes = new ArrayList();
//        while(itr1.hasNext()){
//            double angle = (double)itr1.next();
//            int stroke = (int)((angle + 22.5)/45 + 1);
//            if(stroke == 0){
//                System.out.println("Something is wrong -- from GestureRecognizer calStrokes()");
//            }
//            newStrokes.add(stroke);
//        }
//        System.out.println("new Strokes: " + newStrokes.toString());
        return strokes;
     }  
     
     private ArrayList<Double> anglesPreprocess(ArrayList<Double> angles){
         ArrayList<Double> newAngles = new ArrayList();
         int windowSize = 5;
         int size = angles.size();
         double curAngle = Double.MIN_VALUE;
         for(int i = 0; i < size; i++ ){
             if(i <   (windowSize - 1) / 2){
                double sum = 0.0;
                int length = 0;
                for(int j = 0; j < windowSize && j < size; j++){
                    sum += angles.get(j);
                    length++;
                }
                newAngles.add(sum / length);
             }else if(i>= (size - 1 -((windowSize - 1) / 2))){
                double sum = 0.0;
                int length = 0;
                for(int j = size - windowSize; j < size; j++){
                    sum += angles.get(j);
                    length++;
                }
                newAngles.add(sum / length);
             }else{
                 double sum = 0.0;
                 
                 for(int j = i - ((windowSize - 1) / 2); j <= i + ((windowSize - 1) / 2) && j < size; j++){
                     sum += angles.get(j);
                 }
                 newAngles.add(sum / windowSize);
             }
         }
         
         return newAngles;
     }
     
     
     
//      private ArrayList<Double> translatedAngles(ArrayList<Double> angles){
//         ArrayList<Double> newAngles = new ArrayList();
//         Iterator<Double> itr = angles.iterator();
//         while(itr.hasNext()){
//             double angle = itr.next();
//             if(angle > 180){
//                 angle = angle - 360;
//             }
//             newAngles.add(angle);
//         }
//         return newAngles;
//     }
     
     private ArrayList<Double> translatedBackAngles(ArrayList<Double> angles){
         ArrayList<Double> newAngles = new ArrayList();
         Iterator<Double> itr = angles.iterator();
         while(itr.hasNext()){
             double angle = itr.next();
             if(angle < 0){
                 angle = 360 + angle;
             }
             newAngles.add(angle);
         }
         return newAngles;
     }
     
//    private ArrayList calStrokes(){
//         ArrayList<Integer> strokes = new ArrayList();
//        ArrayList<Point2D> positions = this.getPositions(this.gesturePoints);
//        int refPositionIndex = 0;
//        int lastDirection = 0;
//        int direction = 0;
//        for(int i = 1; i < positions.size(); i++){
//            double angle = Toolkit.calAngleToRefPositionByPosition(positions.get(i), positions.get(refPositionIndex));
//            if(i == 1){
//                direction = (int)((angle + 22.5)/45 + 1);
//                lastDirection = direction;
//            }else{
//                direction = (int)((angle + 22.5)/45 + 1);
//                if(direction != lastDirection){
//                    lastDirection = direction;
//                    refPositionIndex = i - 1;                 
//                    i--;
//                    continue;
//                }else{
//                    
//                }
//            }
//            strokes.add(direction);
//        }
//        return strokes;
//        
//        
//     }
     
     
     
}
