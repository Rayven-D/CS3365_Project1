/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Rayven
 */
public class ChartController extends Application {
    
    User curUser;
    ChartInterface chartGUI;
    
    public ChartController(){
        curUser = null;
    }
    public ChartController(User u){
        this.curUser = u;
        chartGUI = new ChartInterface();
        this.setFields();
                System.out.println("K");
    }
    
    public void setFields(){
        chartGUI.id.setVisible(false);
        chartGUI.idField.setVisible(false);
        if(curUser instanceof Doctor || curUser instanceof Nurse){
            if(curUser instanceof Nurse){
                chartGUI.treatment.setVisible(false);
                chartGUI.treatmentField.setVisible(false);
                chartGUI.prescription.setVisible(false);
                chartGUI.prescriptionField.setVisible(false);                
            }
            chartGUI.payment.setVisible(false);
            chartGUI.paymentField.setVisible(false);
        }else if( curUser instanceof Staff){
            chartGUI.chartBox.setVisible(false);
            chartGUI.dropDownBox.setVisible(false);
        }
        
    }    
    @Override
    public void start(Stage primaryStage) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
