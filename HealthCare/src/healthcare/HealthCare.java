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
<<<<<<< HEAD
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
        
=======

    Database db = new Database();
    ArrayList<User> users = new ArrayList();
    ArrayList<PatientChart> charts = new ArrayList();
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();

        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //GET DATABSE INFORMATION AND PUT IT INTO USERS
                try {
                    users = db.getUsers();
                    charts = db.getCharts();
                } catch (Exception ex) {
                    Logger.getLogger(HealthCare.class.getName()).log(Level.SEVERE, null, ex);
                }

                //PULLING DATA FROM USERS EXAMPLE AFTER GETTING FROM DB
                //users.get(0).changePatient_name(new PatientChart(),"bob");
                System.out.println(db.getSingleAvailability(124).get(0).getPatientCount());
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
>>>>>>> a24e0f9a6f99d969e2f82a6fa7cce07bfb0fe604
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(LoginInterface.class, args);
    }
    
}
