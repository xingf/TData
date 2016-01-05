/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 *
 * @author Xing Wang
 */
public class GestureStrokeGraph {
    private JFrame gestureStrokeFrame;
    private GestureStrokePanel gestureStrokePanel;
    private ArrayList<Integer> strokes = new ArrayList();
    private final double stepLength = 10;
    private double startLocationX = 800;
    private double startLocationY = 500;
    
    class GestureStrokePanel extends JPanel{
        public GestureStrokePanel(){
            super();
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            double lastX;
            double lastY;
            //draw gesture
            //System.out.println("paint");
            if(strokes.size() > 1){
                int direction = strokes.get(0);
                double xshift = calXShiftByDirection(direction);
                double yshift = calYShiftByDirection(direction);
                drawLine(g2, Color.RED, startLocationX, startLocationY, startLocationX + xshift, startLocationY + yshift);
                lastX = startLocationX + xshift;
                lastY = startLocationY + yshift;
                //System.out.println("strokes.size: " + strokes.size());
                for(int index = 1; index < strokes.size(); index++){
                    int nextStroke = strokes.get(index);
                    double newX = lastX + calXShiftByDirection(nextStroke);
                    double newY = lastY + calYShiftByDirection(nextStroke);
                    if(index %2 == 0){
                        //System.out.println("drawLine ji : " + lastX + " " + lastY + " " + newX + " " + newY);
                        drawLine(g2, Color.BLUE, lastX, lastY, newX, newY);
                    }else{
                        //System.out.println("drawLine ou : " + + lastX + " " + lastY + " " + newX + " " + newY);
                        drawLine(g2, Color.GREEN, lastX, lastY, newX, newY);
                        
                    }
                    lastX = newX;
                    lastY = newY;
                }
            }
        }
    }
    
    private void drawLine(Graphics2D g2, Paint paint, double startX, double startY, double endX, double endY){
        g2.setPaint(paint);
        Line2D l = new Line2D.Double(startX, startY, endX, endY);
        g2.draw(l);
    }
    
    
    private GestureStrokeGraph(){
        gestureStrokeFrame = new JFrame();
        gestureStrokeFrame.setTitle("Gesture Stroke Panel");
        gestureStrokeFrame.setSize(400, 200);
        gestureStrokeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gestureStrokePanel = new GestureStrokePanel();
        gestureStrokeFrame.add(gestureStrokePanel);
        //jf.pack();
    }
    public GestureStrokeGraph(ArrayList directions){
        this();
        this.strokes = directions;
        //System.out.println("construct GestureStrokeGraph");
        gestureStrokeFrame.setVisible(true);
    }
    
    public void setStartX(double x){
        this.startLocationX = x;
    }
    public void setStartY(double y){
        this.startLocationY = y;
    }    
    public void setStrokes(ArrayList dir){
        this.strokes = dir;
        gestureStrokePanel.repaint();
    }
    
    
    
    
    private double calXShiftByDirection(int direction){
        
        double xshift = 0;
        switch(direction){
            case 1:
            case 5:
                xshift = 0;
                break;
            case 2:
            case 4:
                xshift = stepLength * 0.707;
                break;
            case 3:
                xshift = stepLength ;
                break;
            case 6:
            case 8:
                xshift = - stepLength * 0.707;
                break;
            case 7:
                xshift = -stepLength ;
                break;
        }
        return xshift;
    }
    
    private double calYShiftByDirection(int direction){
        double yshift = 0;
        switch(direction){
            case 1:
                yshift = -stepLength;
                break;
            case 2:
            case 8:
                yshift = -stepLength * 0.707;
                break;
            case 3:
            case 7:
                yshift = 0 ;
                break;
            case 4:
            case 6:
                yshift = stepLength * 0.707;
                break;
            case 5:
                yshift = stepLength;
                break;
        }
        return yshift;
    }
}
