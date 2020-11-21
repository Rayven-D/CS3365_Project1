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
public class AppointmentInterface{
    private ArrayList<String> times;
    private Database db;
    private ArrayList<User> usersList;
    private int patientID; 
    private CheckInQueue queue;
    private Timer tm;
    private boolean confirmed;
    //Check-in
    Label inNameLabel, inTimeLabel, inDocLabel;
    TextField inNameField;
    ComboBox inDocCombo;
    ListView<String> inTimeList;
    Scene checkInScene;
    Button inConfirm;
    
    //Check-in NEW
    Label newNameLabel, newBirthdayLabel, newAddressLabel, newSocialLabel, newInsuranceLabel, newPaymentLabel;
    TextField newNameField, newAddressField, newSocialField, newInsuranceField, newPaymentField;
    DatePicker newBirthdayPicker;
    Scene newCheckInScene;
    Button newConfirm;
    
    //Appointment
    Label apptNameLabel, apptDateLabel, apptTimeLabel, apptDocLabel;
    TextField apptNameField;
    ComboBox apptDocCombo, apptDateCombo;
    ListView<String>  apptTimeList;
    Button apptConfirm, apptCancel;
    Scene appointmentScene;
    HealthCareInterface hcInterface;
    


    public AppointmentInterface(CheckInQueue queue, HealthCareInterface hcInterface, Database db){
        this.db = db;
        this.times = new ArrayList<>();
        for(int i = 9 ; i < 17; i++){
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
        tm = new Timer();
       setAppointmentScene();
       setCheckInScene(); 
       setNewCheckInScene();
       this.confirmed = false;
       this.hcInterface = hcInterface;
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
            
        this.apptCancel = new Button("Cancel");
            this.apptCancel.setOnAction(e ->{this.hcInterface.handle(e);});
        
        VBox apptBox = new VBox();
        apptBox.getChildren().addAll(this.apptNameLabel, this.apptNameField, this.apptDateLabel, this.apptDateCombo);
        apptBox.getChildren().addAll(this.apptDocLabel, this.apptDocCombo, this.apptTimeLabel, this.apptTimeList, this.apptConfirm, this.apptCancel);
        apptBox.setPadding(new Insets(20, 20, 20,20));
        
        
        this.appointmentScene = new Scene(apptBox, 500,500);
        
            
    }
    public void setCheckInScene(){
        this.inNameLabel = new Label("Name: ");
        this.inNameField = new TextField();
        
        this.inDocLabel = new Label("Doctor: ");
        this.inDocCombo = new ComboBox();
            try{
                usersList = db.getUsers();
                
            }catch(Exception e){e.printStackTrace();}
            for(int i = 0 ; i < usersList.size(); i++){
                if(usersList.get(i).getPermissions() == 2){
                    
                    this.inDocCombo.getItems().add(usersList.get(i).getName());
                }
            }
            this.inDocCombo.setOnAction((e) -> {handle(e);});
            
            
        this.inTimeLabel = new Label("Appointments: ");
        this.inTimeList = new ListView<>();
        
        this.inConfirm = new Button("Check-In");
            this.inConfirm.setOnAction(e ->{hcInterface.handle(e);});
        
        VBox inBox = new VBox();
        inBox.getChildren().addAll(this.inNameLabel, this.inNameField, this.inDocLabel, this.inDocCombo);
        inBox.getChildren().addAll(this.inTimeLabel, this.inTimeList, this.inConfirm);
            inBox.setPadding(new Insets(20,20,20,20));
            
        
        this.checkInScene = new Scene(inBox, 500, 500);
    }
    
    public void setNewCheckInScene(){
        this.newNameLabel = new Label("Name: ");
        this.newNameField = new TextField("FirstName LastName");
        
       
        
        this.newBirthdayLabel = new Label("\tBirthday: ");
        this.newBirthdayPicker = new DatePicker();
        
        HBox namebdBox = new HBox();
            namebdBox.getChildren().addAll(this.newNameLabel, this.newNameField, this.newBirthdayLabel, this.newBirthdayPicker);
        
        this.newAddressLabel = new Label("Address: ");
        this.newAddressField = new TextField();
            this.newAddressField.setPrefColumnCount(40);
        
        HBox addressBox = new HBox();
            addressBox.getChildren().addAll(this.newAddressLabel, this.newAddressField);
        
        this.newSocialLabel = new Label("SSN: ");
        this.newSocialField = new TextField();
        
        this.newInsuranceLabel = new Label("\tInsurance: ");
        this.newInsuranceField = new TextField();
        
        this.newPaymentLabel = new Label("\tPayment Info: ");
        this.newPaymentField = new TextField();
        
        HBox sipBox = new HBox();
        sipBox.getChildren().addAll(this.newSocialLabel, this.newSocialField);
        sipBox.getChildren().addAll(this.newInsuranceLabel, this.newInsuranceField);
        sipBox.getChildren().addAll(this.newPaymentLabel, this.newPaymentField);
            
        this.newConfirm = new Button("Finish");
        this.newConfirm.setOnAction(e -> {this.hcInterface.handle(e);});
        
        VBox newCheckInBox = new VBox();
        newCheckInBox.getChildren().addAll(namebdBox, addressBox, sipBox, this.newConfirm);
        newCheckInBox.setSpacing(10);
        newCheckInBox.setPadding(new Insets(20,20,20,20));
        
        
        this.newCheckInScene = new Scene(newCheckInBox, 750,500);
        
    }
    
    public void handle(Event e){
        if(e.getSource() == this.apptDocCombo || e.getSource() == this.apptDateCombo){
            this.apptTimeList.getItems().clear();
            for(int i = 0 ;i < this.usersList.size(); i++){
                if(usersList.get(i).getName().equals(this.apptDocCombo.getValue())){
                    ArrayList<Day> temp = db.getSingleAvailability(usersList.get(i).getId());
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
        }else if(e.getSource() == this.inDocCombo){
            for(User d : this.usersList){
                if(d.getName().equals(this.inDocCombo.getValue())){
                    ArrayList<Day> temp = db.getSingleAvailability(d.getId());
                    for(Day day : temp){
                        if(day.getDate().equals(tm.getTodayDate())){
                            int []apptTimes = day.getAvailabilityTimes();
                            for(int j = 0; j < apptTimes.length;j++){
                                if(apptTimes[j] != 0){
                                    String s = times.get(j) + " - " + db.getSingleChart(apptTimes[j]).getPatient_name();
                                    this.inTimeList.getItems().add(s);
                                }
                            }
                            break;
                        }
                        
                    }
                    break;
                }
            }
        }
        else if(e.getSource() == this.inConfirm || e.getSource() == this.apptConfirm || e.getSource() == this.apptCancel){
            this.confirmed = true;
        }
    }
    
    public int getSelectedPatientID(){
        String s = this.inTimeList.getSelectionModel().getSelectedItem();
        s = s.replaceAll("[^a-zA-Z\\.\\s]", "");
        s = s.trim();
        try{
            for(PatientChart pc: db.getCharts()){
                if(s.equals(pc.getPatient_name())){
                    return pc.getPatient_id();
                }
            }
        }catch(Exception exep){exep.printStackTrace();}
        return 0;
    }
    
    public int getSelectedDocID(){
        try{
            for(User u: db.getUsers()){
                if(u.getName().equals(this.inDocCombo.getValue())){
                    return u.getId();   
                }
            }
        }catch(Exception exep){exep.printStackTrace();}
        return 0;
    }
    
    
    public Scene getAppointmentScene(){
        return this.appointmentScene;
    }
    public Scene getcheckInScene(){
        
        return this.checkInScene;
    }
    
    public Scene getNewCheckInScene(){
        return this.newCheckInScene;
    }

    public int getPatientID(){
        return this.patientID;
    }
    
    public boolean getConfirmation(){
        return this.confirmed;
    }
    
}
