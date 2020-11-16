/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 *
 * @author Arthr
 */
public class HealthCare extends Application{
    
    @Override
    public void start(Stage primaryStage) {}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String [] args ){
        Application.launch(LoginInterface.class, args);
    }

}
