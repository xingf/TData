/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timeseriesdata;



import javafx.application.Application;
import javafx.stage.Stage;
import model.DataModel;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;


/**
 *The visualization of time oriented data                            
 * 
 * @version 1.0
 * @author Xing Wang
 */
public class MainFrame extends Application {
    
  
    Stage stage = null;
    public Stage getStage(){
        return this.stage;
    }
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        DataModel model = DataModel.getDataModel();

        model.loadAllData();
        Controller controller = Controller.getViewController();
        //controller.setMainFrame(this);

        stage.setScene(controller.getScene());

        //When close stage, delete database
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Close Stage");
                DataModel.getDataModel().deleteTables();
            }
        });
        stage.show(); 
        System.out.println("after show");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
