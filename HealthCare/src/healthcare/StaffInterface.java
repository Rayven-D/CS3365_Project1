/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.Event;
import java.util.ArrayList;
import java.util.HashSet;
/**
 *
 * @author Rayven
 */
public class StaffInterface{
     ArrayList<String> times;
     Database db;
     ArrayList<User> usersList;
     int patientID; 
     CheckInQueue queue;
     SystemTimer tm;
     boolean confirmed;
    //Check-in
    Label  inNameLabel, inBdayLabel;
    TextField inNameField;
    DatePicker inBdayPicker;
    Scene checkInScene;
    Button inConfirm, inCancel;
    
    
    //Appointment
    Label apptNameLabel, apptDateLabel, apptTimeLabel, apptDocLabel;
    TextField apptNameField;
    ComboBox apptDocCombo, apptDateCombo;
    ListView<String>  apptTimeList;
    Button apptConfirm, apptCancel;
    Scene appointmentScene;
    HealthCareInterface hcInterface;
    
    //Change Appointment
    Label changeNameLabel, changeDocLabel, changeTimeLabel, curApptLabel;
    Button changeNameButton, changeConfirm, changeCancel;
    TextField changeNameField;
    ComboBox changeDocBox;
    ComboBox changeDateCombo;
    ListView<String> changeTimeList;
    Scene changeAppointmentScene;
    
    //Delete Appointment
    Label deleteNameLabel, deleteCurAppointmentInfo;
    Button deleteNameOK;
    TextField deleteNameField;
    Button cancelAppointment, deleteGoBack;
    Scene deleteAppointmentScene;
    
    //Payment
    Label paymentType, amountLabel, approvedLabel;
    TextField amountOweLabel;
    Scene paymentScene;
    Button creditButton, debitButton, cashButton, exitButton, printButton;
    

    public StaffInterface(CheckInQueue queue, HealthCareInterface hcInterface, Database db){
        this.db = db;
        this.times = new ArrayList<>();
        for(int i = 9 ; i < 15; i++){
            int l = i % 13;
            if( i >= 13){
                l++;
            }
            String s = "" + l;
            if(l < 10)
                s = "  " + s;
            times.add(s + ":00");
            times.add(s + ":30");
        }
        this.queue = queue;
        tm = new SystemTimer();
       setAppointmentScene();
       setCheckInScene(); 
       setPaymentScene();
       this.confirmed = false;
       this.hcInterface = hcInterface;
    }
    
    public void setPaymentScene(){
        this.amountLabel = new Label("Total: ");
        this.amountOweLabel = new TextField("0.00");
            this.amountOweLabel.setEditable(false);
        
        this.paymentType = new Label("Payment Type");
        this.creditButton = new Button("Credit");
            this.creditButton.setOnAction(e -> {this.hcInterface.handle(e);});
        this.debitButton = new Button("Debit");
            this.debitButton.setOnAction(e -> {hcInterface.handle(e);});
        this.cashButton = new Button("Cash");
            this.cashButton.setOnAction(e -> {hcInterface.handle(e);});
        this.exitButton = new Button("Exit");
            this.exitButton.setOnAction(e ->{hcInterface.handle(e);});
        this.printButton = new Button("Print");
            this.printButton.setOnAction(e ->{hcInterface.handle(e);});
            this.printButton.setVisible(false);
        
        this.approvedLabel = new Label("Approved");
            approvedLabel.setVisible(false);
        HBox amountBox = new HBox();
        amountBox.getChildren().addAll(amountLabel, amountOweLabel);
            amountBox.setAlignment(Pos.CENTER);
        
        HBox payTypeBox = new HBox();
        payTypeBox.getChildren().addAll(creditButton, debitButton, cashButton);
            payTypeBox.setSpacing(5);
            payTypeBox.setAlignment(Pos.CENTER);
            
        VBox paymentAll = new VBox();
        paymentAll.getChildren().addAll(amountBox, paymentType, payTypeBox, approvedLabel, printButton, exitButton);
            paymentAll.setSpacing(5);
            paymentAll.setAlignment(Pos.CENTER);
          
        this.paymentScene = new Scene(paymentAll, 300,300);       
            
    }
    
    public void setAppointmentScene(){
        this.apptNameLabel = new Label("Name: ");
        this.apptNameField = new TextField();
            this.apptNameField.setPrefColumnCount(30);
            
        
        this.apptDateLabel = new Label("Date wanted: ");
        this.apptDateCombo = new ComboBox();
            this.apptDateCombo.setOnAction((e) -> {handle(e);});

           ArrayList<String> availDates = tm.getTwoWeekList();
           for(String s: availDates){
               this.apptDateCombo.getItems().add(s);
           }
        
        this.apptDocLabel = new Label("Doctor: ");
        this.apptDocCombo = new ComboBox();
        usersList = null;
            try{
                usersList = db.getUsers();
                
            }catch(Exception e){e.printStackTrace();}
            for(int i = 0 ; i < usersList.size(); i++){
                if(usersList.get(i).getPermissions() == 2){
                    
                    this.apptDocCombo.getItems().add(usersList.get(i).getName());
                }
            }
            this.apptDocCombo.setOnAction((e) -> {handle(e);});
           
           
        this.apptTimeLabel = new Label("Times: ");   
        this.apptTimeList = new ListView<>();
            
        this.apptConfirm = new Button("Set Appointment");
            this.apptConfirm.setOnAction(e -> {this.hcInterface.handle(e);});
            
        this.apptCancel = new Button("Go Back");
            this.apptCancel.setOnAction(e ->{this.hcInterface.handle(e);});
            
            
        
        VBox apptBox = new VBox();
        apptBox.getChildren().addAll(this.apptNameLabel, this.apptNameField, this.apptDateLabel, this.apptDateCombo);
        apptBox.getChildren().addAll(this.apptDocLabel, this.apptDocCombo, this.apptTimeLabel, this.apptTimeList, this.apptConfirm, this.apptCancel);
        apptBox.setPadding(new Insets(20, 20, 20,20));
        
        
        this.appointmentScene = new Scene(apptBox, 500,500);
        
            
    }
    
    public void setChangeAppointment(){
        this.changeNameLabel = new Label("Patient Name: ");
        this.changeNameField = new TextField();
        this.changeNameButton = new Button("Ok");
            this.changeNameButton.setOnAction(e ->{this.hcInterface.handle(e);});
            
        this.curApptLabel = new Label("");
        
            
        HBox changeNameHBox = new HBox();
            changeNameHBox.getChildren().addAll(changeNameLabel, changeNameField, changeNameButton);
        
        this.changeDocLabel = new Label("Doctor:");
        this.changeDocBox = new ComboBox();
            try{
                usersList = db.getUsers();
                
            }catch(Exception e){e.printStackTrace();}
            for(int i = 0 ; i < usersList.size(); i++){
                if(usersList.get(i).getPermissions() == 2){
                    
                    this.changeDocBox.getItems().add(usersList.get(i).getName());
                }
            }
            this.changeDocBox.setOnAction(e -> {this.hcInterface.handle(e);});
        
        Label changeDateLabel = new Label("Date:");
        this.changeDateCombo = new ComboBox();
            for(String s: tm.getTwoWeekList()){
                this.changeDateCombo.getItems().add(s);
            }
            this.changeDateCombo.setOnAction(e -> {hcInterface.handle(e);});
        
        this.changeTimeLabel = new Label("Times");
        this.changeTimeList = new ListView();
        
        this.changeConfirm = new Button("Confirm");
            this.changeConfirm.setOnAction(e -> {hcInterface.handle(e);});
        this.changeCancel = new Button("Cancel");
            this.changeCancel.setOnAction(e -> {hcInterface.handle(e);});
        
        VBox changeApptBox = new VBox();
        changeApptBox.getChildren().addAll(changeNameHBox, curApptLabel,
                changeDocLabel, changeDocBox, changeDateLabel, changeDateCombo, changeTimeLabel, changeTimeList, changeConfirm, changeCancel);
        
        this.changeAppointmentScene = new Scene(changeApptBox, 500,500);
    }
    
    public void setDeleteAppointmentScene(){
        this.deleteNameLabel = new Label("Name: ");
        this.deleteNameField = new TextField();
        
        this.deleteCurAppointmentInfo = new Label("");
        
        this.cancelAppointment = new Button("Cancel Appointment");
            this.cancelAppointment.setOnAction((e) -> {hcInterface.handle(e);});
        this.deleteGoBack = new Button("Go Back");
            this.deleteGoBack.setOnAction(e-> {hcInterface.handle(e);});
        this.deleteNameOK = new Button("OK");
            this.deleteNameOK.setOnAction((e) -> {hcInterface.handle(e);});
        
        VBox deleteBox = new VBox();
        deleteBox.getChildren().addAll(deleteNameLabel, deleteNameField,this.deleteNameOK, deleteCurAppointmentInfo, cancelAppointment, deleteGoBack);
        
        this.deleteAppointmentScene = new Scene(deleteBox, 500, 500);
    }
    
    public void setCheckInScene(){

        this.inNameLabel = new Label("Name: ");
        this.inNameField = new TextField();
        
        this.inBdayLabel = new Label("Birthday: ");
        this.inBdayPicker = new DatePicker();
      
        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(inNameLabel, inNameField);
        HBox bdayBox = new HBox();
        bdayBox.getChildren().addAll(inBdayLabel, inBdayPicker);
        
        this.inConfirm = new Button("Check-In");
            this.inConfirm.setOnAction(e ->{hcInterface.handle(e);});
        this.inCancel = new Button("Go Back");
            this.inCancel.setOnAction(e -> {hcInterface.handle(e);});
        
        VBox inBox = new VBox();
        
        inBox.getChildren().addAll( nameBox, bdayBox, this.inConfirm, this.inCancel);
            inBox.setPadding(new Insets(20,20,20,20));
            
        
        this.checkInScene = new Scene(inBox, 300, 200);
    }
    
   
    
    public void handle(Event e){
        if(e.getSource() == this.apptDocCombo || e.getSource() == this.apptDateCombo){
            this.apptTimeList.getItems().clear();
            for(int i = 0 ;i < this.usersList.size(); i++){
                if(this.hcInterface.userList.get(i).getName().equals(this.apptDocCombo.getValue())){
                    System.out.println(this.hcInterface.userList.get(i).getId());
                    ArrayList<Day> temp = db.getSingleAvailability(this.hcInterface.userList.get(i).getId());
                    for(Day d: temp){
                        if(d.getDate().equals(this.apptDateCombo.getValue())){
                            int []avails = d.getAvailabilityTimes();
                            for(int j = 0; j < this.times.size(); j++){
                                String s = times.get(j);
                                if(avails[j] != 0){
                                    s += " - No Availability"; 
                                }
                                this.apptTimeList.getItems().add(s);
                                
                            }
                        }
                    }
                }
            }
        }
        else if(e.getSource() == this.inConfirm || e.getSource() == this.apptConfirm || e.getSource() == this.apptCancel){
            this.confirmed = true;
        }
    }
      
    
    public Scene getAppointmentScene(){
        return this.appointmentScene;
    }
    public Scene getcheckInScene(){
        
        return this.checkInScene;
    }
    
    public int getPatientID(){
        return this.patientID;
    }
    
    public boolean getConfirmation(){
        return this.confirmed;
    }
    
    public Scene getChangeAppointmentScene(){
        return this.changeAppointmentScene;
    }
    public Scene getDeleteAppointmentScene(){
        return this.deleteAppointmentScene;
    }
    public Scene getPaymentScene(){
        return this.paymentScene;
    }
}
