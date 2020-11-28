//CS 2365 OOP Spring 2020 Section 001
//Rayven Jan B. Deray

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author rjder
 */
public class CEOInterface extends Application {

    ObservableList<ReportInformation> data;

    Label dateLabel, numPatServedLabel, amountEarnedLabel;
    TextField dateField, numPatServedField, amountEarnedField;
    ComboBox docCombo;
    Stage stage;
    Scene CEOScene;

    //CEO Menu
    Button viewReportsButton;
    Button generateReportButton;
    

    //Reports Screen
    ComboBox reportDateSelectorBox;
    Button reportSelectorButton;

    private CEOController controller;
    private int reportSelection = 0;
    private HealthCareInterface hcInterface;
    
    
    
    public CEOInterface(){}
    
    public CEOInterface(HealthCareInterface hci, Stage stage){
        this.hcInterface = hci;
        this.controller = new CEOController(this);
        this.stage = stage;
        data = FXCollections.observableArrayList(this.controller.getReports().get(reportSelection).getReports());
        this.setCEOMenuScene(); 
    }

    @Override
    public void start(Stage primaryStage) {
          
    }

    public void handle(Event e) {
        this.controller.handle(e);
    }

    public void setCEOMenuScene() {
        this.viewReportsButton = new Button("View Reports");
        this.generateReportButton = new Button("Generate New Report");
        
        this.viewReportsButton.setOnAction((e) -> {this.hcInterface.handle(e);});
        this.generateReportButton.setOnAction((e) -> controller.generateReport());
        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(this.viewReportsButton, this.generateReportButton, this.hcInterface.universalLogout);
        menuBox.setPadding(new Insets(20, 20, 20, 20));
        this.CEOScene = new Scene(menuBox, 500, 500);
    }

    public void setCEOReportsScene() {
        this.reportDateSelectorBox = new ComboBox();
        for (Report report : this.controller.getReports()) {
            this.reportDateSelectorBox.getItems().add(report.getDate());
        }
        this.reportDateSelectorBox.getSelectionModel().select(reportSelection);
        this.reportSelectorButton = new Button("View Report");

        
        this.reportSelectorButton.setOnAction(new EventHandler(){
            @Override
            public void handle(Event e) {
               setCEOReportsScene();
               stage.setScene(getCEOScene());
            }
        });

        this.reportDateSelectorBox.setOnAction(new EventHandler() {
            @Override
            public void handle(Event e) {
                String reportDateInner = (String) reportDateSelectorBox.getValue();
                data = FXCollections.observableArrayList(controller.getReportData(reportDateInner));
                reportSelection = controller.getReportIndex(reportDateInner);
            }
        });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(this.reportDateSelectorBox, this.reportSelectorButton);

        //TITLE
        Label title = new Label("Reports");
        title.setFont(new Font("Arial", 20));

        //TABLE
        TableView table = new TableView();
        table.setEditable(false);
        TableColumn docName = new TableColumn("Doctor Name");
        TableColumn patientAmount = new TableColumn("Patients Amount");
        TableColumn amountEarned = new TableColumn("Money Earned");
        docName.setMinWidth(153);
        patientAmount.setMinWidth(153);
        amountEarned.setMinWidth(153);
        docName.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientAmount.setCellValueFactory(new PropertyValueFactory<>("patientAmount"));
        amountEarned.setCellValueFactory(new PropertyValueFactory<>("amountEarned"));
        table.setItems(data);
        table.getColumns().addAll(docName, patientAmount, amountEarned);

        Button back = new Button("Go Back");
               back.setOnAction(e -> {this.hcInterface.goBack.fire();});
        
        //VISUALIZE
        VBox vbox = new VBox();
        vbox.getChildren().addAll(title, hbox, table, back);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        this.CEOScene = new Scene(vbox, 500, 500);
        
    }
    
    public Scene getCEOScene(){
        return this.CEOScene;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
