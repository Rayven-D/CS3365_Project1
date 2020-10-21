package healthcare;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Arthr
 */
public class HealthCare extends Application {

    Database db = new Database();
    ArrayList<User> users = new ArrayList();

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();

        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                //GET DATABSE INFORMATION AND PUT IT INTO USERS
                try {
                    users = db.initDatabase();
                } catch (Exception ex) {
                    Logger.getLogger(HealthCare.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //PULLING DATA FROM USERS EXAMPLE AFTER GETTING FROM DB
                if (db.saveData(users) == 1) {
                    System.out.println(users.get(0).getPaymentInformation().get(0).getPin());
                };
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
