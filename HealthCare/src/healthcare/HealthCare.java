/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 *
 * @author Rayven
 */
public class HealthCare extends Application {
    ArrayList<PatientChart> charts;
    @Override
    public void start(Stage primaryStage) {}
    
    public void getCharts(){
        Database db = new Database();
        ArrayList<User> patients = new ArrayList<User>();
        try{
         patients = db.initDatabase();
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        for(int i = 0; i < patients.size(); i++){
            User tempUser = patients.get(i);
            
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(LoginInterface.class, args);
    }
    
}
