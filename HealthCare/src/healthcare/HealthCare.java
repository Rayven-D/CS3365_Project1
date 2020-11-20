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
    ArrayList<PatientChart> charts = new ArrayList();
    ArrayList<Report> reports = new ArrayList();

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
                    reports = db.getReports();
                } catch (Exception ex) {
                    Logger.getLogger(HealthCare.class.getName()).log(Level.SEVERE, null, ex);
                }

                //PULLING DATA FROM USERS EXAMPLE AFTER GETTING FROM DB
                //users.get(0).changePatient_name(new PatientChart(),"bob");
//                Report report = new Report();
//                ReportInformation reportInfo1 = new ReportInformation();
//                ReportInformation reportInfo2 = new ReportInformation();
//                ArrayList<ReportInformation> reportInfo = new ArrayList();
//
//                reportInfo1.setAmountEarned(1000);
//                reportInfo1.setName("Dr. Arthur");
//                reportInfo1.setPatientAmount(2);
//                reportInfo2.setAmountEarned(400);
//                reportInfo2.setName("Dr. Liam");
//                reportInfo2.setPatientAmount(3);
//                report.setDate("12/12/2020");
//                reportInfo.add(reportInfo1);
//                reportInfo.add(reportInfo2);
//                report.setReports(reportInfo);
//                db.saveSingleReport(report, "12/12/2020");
//                System.out.println(reports.get(0).getDate());
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
