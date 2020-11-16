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
<<<<<<< HEAD
public class HealthCare extends Application{
    
    @Override
    public void start(Stage primaryStage) {}
    
=======
public class HealthCare extends Application {

    Database db = new Database();
    ArrayList<User_Old> users = new ArrayList();

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();

        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    users = db.initDatabase();
                } catch (Exception ex) {
                    Logger.getLogger(HealthCare.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (db.saveData(users) == 1) {
                    System.out.println(users.get(0).getPaymentInformation().get(0).getPin());
                };
                for (User_Old user : users) {
                    System.out.println(user.getAppointmentInformation().get(0).getAppointmentId());
                }
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

>>>>>>> 00e93d8b6f8698f71274b8ae6aa04a0f53c1c2ee
    /**
     * @param args the command line arguments
     */
    public static void main(String [] args ){
        Application.launch(LoginInterface.class, args);
    }

}
