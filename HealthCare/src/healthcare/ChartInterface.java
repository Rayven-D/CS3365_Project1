/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
/**
 *
 * @author Rayven
 */
public class ChartInterface extends Application {
    
    Label name, birthday, address, social, insurance, payment, id;
    Label appDate, reasonVisit, weight, height, bp, treatment, prescription, docVisiting;
    TextField nameField, birthdayField, addressField, socialField, insuranceField, paymentField, idField;
    TextField appDateField, weightField, heightField, bpField, docVisitedField;
    TextArea reasonVisitField, treatmentField, prescriptionField;
    ComboBox treatments;
    HBox nameBdayBox,  sipiBox, appDateBox, whbpBox;
    VBox chartBox, topBox, dropDownBox, allBox;
    User curUser;
    
    public ChartInterface(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        Timer tm = new Timer();
        for(int i = 0; i < 3; i++){
            temp.add(i);
        }
        //Patient Name
        name = new Label("Name: ");
        nameField = new TextField();
        
        //Birthday
        birthday = new Label("Birthday: ");
        birthdayField = new TextField();
       
        
        //Address
        address = new Label("Address: ");
        addressField = new TextField();
        addressField.setMaxWidth(500);
        
        //Social
        social = new Label("SSN: ");
        socialField = new TextField();
        
        //Insurance
        insurance = new Label("Insurance: ");
        insuranceField = new TextField();
        
        //Payment
        payment = new Label("Payment: ");
        paymentField = new TextField();
        
        //ID
        id = new Label("Patient ID: ");
        idField = new TextField();
        
        //Treatment Menu Dropdown
        treatments = new ComboBox();
        temp.forEach((n) -> treatments.getItems().add(n));
        treatments.getSelectionModel().selectFirst();
        //Date
        appDate = new Label(treatments.getValue()+"");
        
        nameBdayBox = new HBox();
        nameBdayBox.getChildren().addAll(name, nameField);
        nameBdayBox.getChildren().addAll(birthday, birthdayField);
            //properties
            nameBdayBox.setSpacing(10);
        
        

            
        sipiBox = new HBox();
        sipiBox.getChildren().addAll(social, socialField, insurance, insuranceField, payment, paymentField, id, idField);
            //properties
            sipiBox.setSpacing(10);
        
        VBox addr = new VBox();
        addr.getChildren().addAll(address, addressField);
        
        topBox = new VBox();
        topBox.getChildren().addAll(nameBdayBox, addr, sipiBox);
        topBox.setSpacing(10);
        
        
        dropDownBox = new VBox();
        dropDownBox.getChildren().addAll(treatments);
            //properties
        
        //AppDate
        this.appDate = new Label("Date: ");
        this.appDateField = new TextField();
            this.appDateField.setText(tm.getTodayDate());
            this.appDateField.setEditable(false);
        appDateBox = new HBox();
        appDateBox.getChildren().addAll(appDate, appDateField);
        
            
        this.reasonVisit = new Label("Reason for Visit: ");
        this.reasonVisitField = new TextArea();
            this.reasonVisitField.setMaxWidth(1000);
            this.reasonVisitField.setPrefRowCount(3);
            
        this.weight = new Label("Weight: ");
        this.weightField = new TextField();
        
        this.height = new Label("Height: ");
        this.heightField = new TextField();
        
        this.bp = new Label("BP: ");
        this.bpField = new TextField();
        
        this.whbpBox = new HBox();
        whbpBox.getChildren().addAll(weight, weightField, height, heightField, bp, bpField);
        whbpBox.setSpacing(10);
        
        
        this.treatment = new Label("Treatment: ");
        this.treatmentField = new TextArea();
            this.treatmentField.setMaxWidth(1000);
            this.treatmentField.setPrefRowCount(3);
       
        this.prescription = new Label("Prescription: ");
        this.prescriptionField = new TextArea();
            this.prescriptionField.setMaxWidth(1000);
            this.prescriptionField.setPrefRowCount(3);
    }
    
    @Override
    public void start(Stage primaryStage) {
              
        chartBox = new VBox();
        chartBox.getChildren().addAll(appDateBox);
        chartBox.getChildren().addAll(whbpBox,reasonVisit, reasonVisitField);
        chartBox.getChildren().addAll(treatment, treatmentField, prescription, prescriptionField);
        chartBox.setSpacing(5);
        
        allBox = new VBox();
        allBox.getChildren().addAll(topBox, dropDownBox, chartBox);
            allBox.setPadding(new Insets(20,20,20,20));
            allBox.setSpacing(10);
       
        
        Scene scene = new Scene(allBox, 1250, 500);
        
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String [] args){
        launch(args);
    }
}