/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;

/**
 *
 * @author Administrator
 */
public class ViewFrame /*implements IGestureListener*/ {

    static private ViewFrame frame = null;
    private final Scene scene;
    private final BorderPane borderPane;
    public ArrayList<AbstractChart> views;
    private int columnCount = 1;
    private int rowCount = 0;
    public final GridPane gridPane;
    FileChooser fileChooser = null;

    //ScrollPane scrollPane;

    private ViewFrame() throws IOException {
        views = new ArrayList();
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);

        borderPane = new BorderPane(gridPane);
        borderPane.setMinSize(BorderPane.USE_COMPUTED_SIZE, BorderPane.USE_COMPUTED_SIZE);
        borderPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        borderPane.setPrefSize(BorderPane.USE_COMPUTED_SIZE, BorderPane.USE_COMPUTED_SIZE);
        borderPane.setVisible(true);

        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Data File");

        scene = new Scene(borderPane, 800, 600);
    }

    public File showFileChooser() {

        File dataFile = fileChooser.showOpenDialog(this.scene.getWindow());
        return dataFile;
    }

    static public ViewFrame getFrame() {
        if (frame == null) {
            try {
                frame = new ViewFrame();
            } catch (IOException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return frame;
    }

    public Scene getScene() {
        return this.scene;
    }

    
        public void showFullSceneTreeB() {
            
        int nodeCount = views.size();
        if (nodeCount >= 100) {
            if (nodeCount % 2 == 1) {
                this.columnCount = 1;
            } else if (nodeCount % 2 == 0) {
                this.columnCount = 2;
            }
        }

        if (nodeCount == 0) {
            rowCount = 0;
        } else {
            if (this.columnCount == 1) {
                rowCount = nodeCount / this.columnCount;
            } else if (this.columnCount > 1) {
                rowCount = (nodeCount - 1) / 2 + 1;
            }
        }

        //System.out.println("total row: " + rowCount + " column: " + columnCount);
        if (nodeCount != 0) {
            if (this.gridPane.getChildren().size() > 0) {
                //this.gridPane.getChildren().clear();
                this.gridPane.getChildren().remove(0, this.gridPane.getChildren().size());
               
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.gridPane.getRowConstraints().size() > 0) {
                this.gridPane.getRowConstraints().clear();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.gridPane.getColumnConstraints().size() > 0) {
                this.gridPane.getColumnConstraints().clear();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        for (int i = 0; i < rowCount; i++) {
            RowConstraints rCons = new RowConstraints();
            rCons.setPercentHeight(100 / rowCount);
            rCons.setMaxHeight(Double.MAX_VALUE);
            this.gridPane.getRowConstraints().add(rCons);
            
        }

        try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        for (int i = 0; i < this.columnCount; i++) {
            ColumnConstraints cCons = new ColumnConstraints();
            cCons.setMaxWidth(Double.MAX_VALUE);
            if (this.columnCount == 1) {
                cCons.setPercentWidth(100);
            } else {
                cCons.setPercentWidth(100 / this.columnCount);
            }
            this.gridPane.getColumnConstraints().add(cCons);
            
        }

        try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        for (int i = 0; i < nodeCount; i++) {
            int nodeRowIndex = i / this.columnCount;
            //int nodeColumnIndex = i % this.columnCount;
            
            int nodeColumnIndex = 0;
            //System.out.println("Add node : " + i);
            this.gridPane.add(views.get(i), nodeColumnIndex, nodeRowIndex);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
    public void showFullSceneTree() {
        int nodeCount = views.size();
        if (nodeCount >= 100) {
            if (nodeCount % 2 == 1) {
                this.columnCount = 1;
            } else if (nodeCount % 2 == 0) {
                this.columnCount = 2;
            }
        }

        if (nodeCount == 0) {
            rowCount = 0;
        } else {
            if (this.columnCount == 1) {
                rowCount = nodeCount / this.columnCount;
            } else if (this.columnCount > 1) {
                rowCount = (nodeCount - 1) / 2 + 1;
            }
        }

        //System.out.println("total row: " + rowCount + " column: " + columnCount);
        if (nodeCount != 0) {
            if (this.gridPane.getChildren().size() > 0) {
                //this.gridPane.getChildren().clear();
                this.gridPane.getChildren().remove(0, this.gridPane.getChildren().size());
               
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.gridPane.getRowConstraints().size() > 0) {
                this.gridPane.getRowConstraints().clear();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (this.gridPane.getColumnConstraints().size() > 0) {
                this.gridPane.getColumnConstraints().clear();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        for (int i = 0; i < rowCount; i++) {
            RowConstraints rCons = new RowConstraints();
            rCons.setPercentHeight(100 / rowCount);
            rCons.setMaxHeight(Double.MAX_VALUE);
            this.gridPane.getRowConstraints().add(rCons);
            
        }

        try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        for (int i = 0; i < this.columnCount; i++) {
            ColumnConstraints cCons = new ColumnConstraints();
            cCons.setMaxWidth(Double.MAX_VALUE);
            if (this.columnCount == 1) {
                cCons.setPercentWidth(100);
            } else {
                cCons.setPercentWidth(100 / this.columnCount);
            }
            this.gridPane.getColumnConstraints().add(cCons);
            
        }

        try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        for (int i = 0; i < nodeCount; i++) {
            int nodeRowIndex = i / this.columnCount;
            //int nodeColumnIndex = i % this.columnCount;
            
            int nodeColumnIndex = 0;
            //System.out.println("Add node : " + i);
            this.gridPane.add(views.get(i), nodeColumnIndex, nodeRowIndex);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void fullScreen(AbstractChart content, int rowSize, int columnSize) {
        if (this.gridPane.getChildren().size() > 0) {
            this.gridPane.getChildren().clear();
        }
        if (this.gridPane.getRowConstraints().size() > 0) {
            this.gridPane.getRowConstraints().clear();
        }
        if (this.gridPane.getColumnConstraints().size() > 0) {
            this.gridPane.getColumnConstraints().clear();
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        RowConstraints rCons = new RowConstraints();
        rCons.setPercentHeight(100);
        rCons.setMaxHeight(Double.MAX_VALUE);
        this.gridPane.getRowConstraints().add(rCons);

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        ColumnConstraints cCons = new ColumnConstraints();
        cCons.setMaxWidth(Double.MAX_VALUE);
        cCons.setPercentWidth(100);
        this.gridPane.getColumnConstraints().add(cCons);

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.gridPane.add(content, 0, 0);
    }

    public void addDataPaneContent(AbstractChart newView) {

        newView.setVisible(true);
        newView.setCache(false);
        newView.setManaged(true);
        newView.maxHeight(Double.MAX_VALUE);
        newView.maxWidth(Double.MAX_VALUE);
        views.add(newView);
        this.showFullSceneTree();

    }

    public void removeView(AbstractChart view) {
        this.views.remove(view);
        this.showFullSceneTree();
    }

    public void removeAllViews() {
        for (int i = this.views.size(); i > 0 && i <= this.views.size(); i--) {
            this.views.remove(i - 1);
        }
        this.showFullSceneTree();
    }

//    public void fullScreen(Object source){
//        
//        AbstractChart hightLightView = this.findView(source);
//        hightLightView.setVisible(true);
//        hightLightView.setCache(false);
//        hightLightView.setManaged(true);
//        hightLightView.maxHeight(Double.MAX_VALUE);
//        hightLightView.maxWidth(Double.MAX_VALUE);
//            if (this.gridPane.getChildren().size() > 0) {
//                this.gridPane.getChildren().clear();
//                try {
//                Thread.sleep(5);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            }
//            if (this.gridPane.getRowConstraints().size() > 0) {
//                this.gridPane.getRowConstraints().clear();
//                try {
//                Thread.sleep(5);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            }
//            if (this.gridPane.getColumnConstraints().size() > 0) {
//                this.gridPane.getColumnConstraints().clear();
//                try {
//                Thread.sleep(5);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            }
//            
//            RowConstraints rCons = new RowConstraints();
//            rCons.setPercentHeight(100);
//            rCons.setMaxHeight(Double.MAX_VALUE);
//            this.gridPane.getRowConstraints().add(rCons);
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            ColumnConstraints cCons = new ColumnConstraints();
//            cCons.setMaxWidth(Double.MAX_VALUE);
//            
//            cCons.setPercentWidth(100);
//            this.gridPane.getColumnConstraints().add(cCons);
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(ViewFrame.class.getName()).log(Level.SEVERE, null, ex);
//            }
//          this.gridPane.add(hightLightView, 0, 0);
//    }
//    private AbstractChart findView(Object source){
//        for(int i= 0; i < this.views.size(); i++){
//            if(this.views.get(i).getEventSource().equals(source)){
//                return this.views.get(i);
//            }
//        }
//        return null;
//    }
}
