/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Metadata;
import gesture.GestureEvent;
import gesture.GestureEventManager;
import gesture.IGestureListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import timeseriesdata.MainFrame;
import model.DataModel;
import model.GlobalData;
import view.AbstractChart;
import view.ChartFactory;
import view.ViewFrame;

/**
 *
 * @author Xing Wang
 */
public class Controller implements IGestureListener {

    ViewFrame frame;
    AbstractChart mainView;
    DataModel model;
    Stage rootStage;
    MainFrame mainFrame;
    boolean fullScreenOnePanel = false;
    public void setMainFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    private Controller() {
        model = DataModel.getDataModel();
        frame = ViewFrame.getFrame();
        
        //System.out.println("Controller main function isFxApplicationThread: " + Platform.isFxApplicationThread());
        //view = ChartFactory.createXYLineChart();
        mainView = ChartFactory.createBasicView();
        GlobalData tsData = model.getTimeSeriesData();
        mainView.showData(tsData);
        mainView.setID(AbstractChart.ID_MAIN_VIEW);
        frame.addDataPaneContent(mainView);

        //model.calExceptionInDouble();
        GestureEventManager.getGestureManager().addGestureListener(this);
        
    }

    protected static Controller viewController = null;

    public static Controller getViewController() {
        if (viewController == null) {
            viewController = new Controller();
        }
        return viewController;
    }


    public Scene getScene(){
        return this.frame.getScene();
    }
    
    
    @Override
    public void gestureActionPerformed(GestureEvent ge) {
        int gesture = ge.getGesture();
        ArrayList<Point2D> positions = ge.getScenePositionsOfGesturePointsInArrayList();
        ArrayList<Long> times = ge.getTimesOfGesturePointsInArrayList();
        switch (gesture) {
        //switch (100) {
            case GestureEvent.EVENT_SINGLEPOINT:

                 Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            
                            //System.out.println("singlePoint");
                            AbstractChart sourceView = ge.getStartSource();
                            Point2D p = ge.getStartGestureLocalPosition();
                            //sourceView.getDataRange();
                            
//                            if(sourceView.isIntersectionWithItemData(p)){
//                                System.out.println("Intersect with " + sourceView.getIntersectedItemDataTime(p) + "  " + sourceView.getIntersectedItemDataValue(p));
//                                
//                            }else{
//                                System.out.println("Intersect nicht");
//                            }
                            
                          
                           
                           
//                            MyChartPanel cp = (MyChartPanel) sourceView.getContent();
//                            Point2D p2  = cp.translateScreenToJava2D(new Point((int)p.getX(), (int)p.getY()));
//                            
//                            
//                            System.out.println("Time Selected: " + sourceView.timeSelected(p) );
//                            System.out.println("Value Selected: " +  sourceView.valueSelected(p));
//                            if(sourceView.timeSelected(p)){
//                                System.out.println("Selected Time is: " + sourceView.getSelectedTickMarkTime(p));
//                            }
//                            if(sourceView.valueSelected(p)){
//                                System.out.println("Selected Value is: " + sourceView.getSelectedTickMarkValue(p));
//                            }
//                            
//                            String value = sourceView.getValue(p);
//                            String time = sourceView.getTime(p);
//                            if (value != null && time != null) {
//                                //public void addHightlightTimeSeriesData(String seriesKey, String value, String time);
//                                GlobalData tsData = model.getTimeSeriesData();
//                                ArrayList<String> seriesNames = tsData.getSeriesNames();
//                                for (String seriesName : seriesNames) {
//                                    String itemValue = tsData.getItemDataValue(seriesName, time);
//                                    String seriesValueFormat = tsData.getSeriesData(seriesName).getSeriesValueFormat();
//                                    if(seriesValueFormat.equals("double")){
//                                        if (Double.valueOf(itemValue).equals(Double.valueOf(value))) {
//                                            if (sourceView.getHightlightData().getRawData() == null) {
//                                                sourceView.getHightlightData().setRawData(tsData);
//                                            }
//                                            //public void addHightlightTimeSeriesData(String seriesKey, String value, String time);
//
//                                            if(sourceView.getHightlightData().getValue(seriesName, time) != null){
//                                                sourceView.getHightlightData().removeData(value, seriesName, time);
//                                            }else{
//                                                sourceView.addHightlightTimeSeriesData(seriesName, value, time);
//                                            }
//                                            
//                                        }
//                                    }
//                                    
//                                }
//
//                                sourceView.hightLightData(sourceView.getHightlightData());
//                            }

                        }
                    });
                 
                break;
           
           

            case GestureEvent.EVENT_PEAK:
//                double maxYSpan = GestureEvent.getMaxY(positions) - GestureEvent.getMinX(positions);
//                double maxXSpan = GestureEvent.getMaxX(positions) - GestureEvent.getMinX(positions);
                if(ge.getGestureHeight() > 200  || ge.getGestureWidth() > 200){
                     Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            //System.out.println("Big Peak");
                            AbstractChart globalMaxView = ChartFactory.createBasicView();
                            //Metadata metadata = model.selectGlobalMaxValueForSeries();
                            AbstractChart sourceView = ge.getStartSource();
                            Metadata metadata = model.selectGlobalMaxValueForSeries(sourceView.getDataRange());
                            globalMaxView.showGlobalMaxValueOfSeries(metadata);
                            frame.addDataPaneContent(globalMaxView);
                            //mainView.hightLightData(metadata);
                        }
                    });
                }else{
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Small peak");
                            AbstractChart localMaxView = ChartFactory.createBasicView();
                            AbstractChart sourceView = ge.getStartSource();
                            Metadata metadata = model.selectLocalMaxValueForSeries(sourceView.getDataRange());
                            //Metadata metadata = model.selectLocalMaxValueForSeries();
                            localMaxView.showLocalMaxValueOfSeries(metadata);
                            frame.addDataPaneContent(localMaxView);
                            //mainView.hightLightData(metadata);
                        }
                    });
                }
               
                break;
            case GestureEvent.EVENT_VALLEY:
                if( ge.getGestureHeight() > 200 ||
                    ge.getGestureWidth() > 200){
                    //System.out.println("Big Valley");
                    AbstractChart globalMinView = ChartFactory.createBasicView();
                    //Metadata metada = model.selectGlobalMinValueForSeries();
                    AbstractChart sourceView = ge.getStartSource();
                    Metadata metadata = model.selectGlobalMinValueForSeries(sourceView.getDataRange());
                    globalMinView.showGlobalMinValueOfSeries(metadata);
                    frame.addDataPaneContent(globalMinView);
                    //mainView.hightLightData(metadata);
                }else{
                    //System.out.println("Small Valley");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            AbstractChart localMinView = ChartFactory.createBasicView();
                            //Metadata metadata = model.selectLocalMinValueForSeries();
                            AbstractChart sourceView = ge.getStartSource();
                            GlobalData dataRange = sourceView.getDataRange();
                            Metadata metadata = model.selectLocalMinValueForSeries(dataRange);
                            localMinView.showLocalMinValueOfSeries(metadata);
                            frame.addDataPaneContent(localMinView);
                            //mainView.hightLightData(metadata);
                        }

                    });
                  
                }
                
                break;
       
           
          
            case GestureEvent.EVENT_RIGHT:    
                AbstractChart sourceView = ge.getStartSource();
                if (sourceView.isInDataArea(ge.getStartGestureLocalPosition().getX(), ge.getStartGestureLocalPosition().getY()) && ge.isLocalGesture()) {
                    //AbstractView exceptionView = ChartFactory.createBasicView();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Exception for Series");
                            AbstractChart exceptionView = ChartFactory.createBasicView();
                            AbstractChart sourceView = ge.getStartSource();
                            Metadata metadata = model.calExceptionForSeries(sourceView.getDataRange());
                            Metadata hightlightMetadata = exceptionView.showExpectionOfSeries(metadata);
                            frame.addDataPaneContent(exceptionView);
                            
                            //mainView.showData(model.getTimeSeriesData(), metadata);
//                            System.out.println("Right Gesture -- isSceneGesture " + ge.isSceneGesture());
//                            System.out.println("Right Gesture -- isLocalGesture " + ge.isLocalGesture());

                        }
                    });
                }
               
                break;
            case GestureEvent.EVENT_DR_QUDRANT:
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Filter value above");
                            AbstractChart sourceView = ge.getStartSource();
                            AbstractChart filterView = ChartFactory.createBasicView();
                            Point2D startGesturePoint = ge.getStartGestureLocalPosition();
                            String value = sourceView.getNearestYValue(ge.getStartGestureLocalPosition());
                            //Metadata valueAboveMetadata = model.selectValueAboveForSeries(value);
                            Metadata valueAboveMetadata = model.selectValueAboveForSeries(value, sourceView.getDataRange());
                            filterView.showValueAboveOfSeries(valueAboveMetadata, value);
                            frame.addDataPaneContent(filterView);
                            
                        }
                    });
                break;
            case GestureEvent.EVENT_UR_QUDRANT:
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Filter value below");
                            AbstractChart sourceView = ge.getStartSource();
                            AbstractChart filterView = ChartFactory.createBasicView();
                          
                           String value = sourceView.getNearestYValue(ge.getStartGestureLocalPosition());
                            //System.out.println("value: " + value);
                            //Metadata valueBelowMetadata = model.selectValueBelowForSeries(value);
                            Metadata valueBelowMetadata = model.selectValueBelowForSeries(value, sourceView.getDataRange());
                            filterView.showValueBelowOfSeries(valueBelowMetadata, value);
                            frame.addDataPaneContent(filterView);
                        }
                    });
               
                break;
                
            case GestureEvent.EVENT_DRU_SEMICIRCLE:
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Filter value between");
                            AbstractChart sourceView = ge.getStartSource();
                            AbstractChart filterView = ChartFactory.createBasicView();
                           
                            String smallerValue = sourceView.getNearestYValue(ge.getStartGestureLocalPosition());
                            String biggerValue = sourceView.getNearestYValue(ge.getLastGestureLocalPosition());
                            if(Double.valueOf(biggerValue) < Double.valueOf(smallerValue)){
                                String tmp = smallerValue;
                                smallerValue = biggerValue;
                                biggerValue = tmp;
                            }
                            
                            //System.out.println("smallervalue: " + smallerValue + "  biggervalue: " + biggerValue);
                            //Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue);
                            Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue, sourceView.getDataRange());
                            filterView.showValueBetweenOfSeries(valueBetweenMetadata, smallerValue, biggerValue);
                            frame.addDataPaneContent(filterView);
                        }
                    });
               
                break;
            case GestureEvent.EVENT_LU_QUDRANT:
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Filter time after");
                            AbstractChart sourceView = ge.getStartSource();
                            AbstractChart filterView = ChartFactory.createBasicView();
                           
                            String smallerTime = sourceView.getNearestXValue(ge.getStartGestureLocalPosition());
                            
                            GlobalData dataRange = sourceView.getDataRange();   /////////////////////////////
                            Metadata dataAfterMetadata = model.selectDataLaterForSeries(smallerTime);
                            filterView.showDataAfter(dataAfterMetadata, smallerTime);
                            
                            //Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue);
//                            Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue, sourceView.getDataRange());
//                            filterView.showValueBetweenOfSeries(valueBetweenMetadata, smallerValue, biggerValue);
                            frame.addDataPaneContent(filterView);
                        }
                    });
               
                break;
                
            case GestureEvent.EVENT_RU_QUDRANT:
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Filter time before");
                            AbstractChart sourceView = ge.getStartSource();
                            AbstractChart filterView = ChartFactory.createBasicView();
                           
                            String smallerTime = sourceView.getNearestXValue(ge.getStartGestureLocalPosition());
                            
                            Metadata dataAfterMetadata = model.selectDataBeforeForSeries(smallerTime);
                            filterView.showDataBefore(dataAfterMetadata, smallerTime);
                            
                            //Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue);
//                            Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue, sourceView.getDataRange());
//                            filterView.showValueBetweenOfSeries(valueBetweenMetadata, smallerValue, biggerValue);
                            frame.addDataPaneContent(filterView);
                        }
                    });
                
                break;
            case GestureEvent.EVENT_RUL_SEMICIRCLE:
                Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //System.out.println("Filter data between");
                            AbstractChart sourceView = ge.getStartSource();
                            AbstractChart filterView = ChartFactory.createBasicView();
                           
                            String biggerValue = sourceView.getNearestXValue(ge.getStartGestureLocalPosition());
                            String smallerValue = sourceView.getNearestXValue(ge.getLastGestureLocalPosition());
                            
                            
                            //System.out.println("smallerTime: " + smallerValue + "  biggerTime: " + biggerValue);
                            //Metadata valueBetweenMetadata = model.selectValueBetweenForSeries(smallerValue, biggerValue);
                            Metadata valueBetweenMetadata = model.selectDataBetweenForSeries(smallerValue, biggerValue);
                            filterView.showDataBetween(valueBetweenMetadata, smallerValue, biggerValue);
                            frame.addDataPaneContent(filterView);
                        }
                    });
                
                break;
            case GestureEvent.EVENT_LEFT:
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                       System.out.println("left remove view");
                       AbstractChart sourceView = ge.getStartSource();
                       frame.removeView(sourceView); 
                    }
                });
                break;
           
            case GestureEvent.EVENT_RIGHTUP:
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        
                        //System.out.println("Trendency");
                        AbstractChart exceptionView = ChartFactory.createBasicView();

                        Metadata metadata = model.calTrendencyForSeries();
                        Metadata hightlightMetadata = exceptionView.showTrendencyOfSeries(metadata);

                        frame.addDataPaneContent(exceptionView);
                    
                    }
                });
                
            default:
                break;
        }

    }
    
    
   
}
