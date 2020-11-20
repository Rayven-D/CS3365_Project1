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
/**
 *
 * @author Rayven
 */
public class AppointmentInterface{
    private ArrayList<String> times;
    private Database db = new Database();
    private ArrayList<User> usersList;
    //Check-in
    Label inNameLabel, inTimeLabel, inDocLabel;
    ComboBox inDocCombo;
    ListView<String> inTimeList;
    Scene checkInScene;
    Button inConfirm;
     
    
    //Appointment
    Label apptNameLabel, apptDateLabel, apptTimeLabel, apptDocLabel;
    TextField apptNameField;
    ComboBox apptDocCombo, apptDateCombo;
    ListView<String>  apptTimeList;
    Button apptConfirm, apptCanel;
    Scene appointmentScene;
    
    //Landing Page
    Button checkInPatient, setUpAppt;
    Scene landingPageScene;

    public AppointmentInterface(){
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
    }
    
    public void setAppointmentScene(){
        this.apptNameLabel = new Label("Name: ");
        this.apptNameField = new TextField();
            this.apptNameField.setPrefColumnCount(30);
            
        
        this.apptDateLabel = new Label("Date wanted: ");
        this.apptDateCombo = new ComboBox();
            this.apptDateCombo.setOnAction((e) -> {handle(e);});
        Timer tm = new Timer();
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
            
        
        VBox apptBox = new VBox();
        apptBox.getChildren().addAll(this.apptNameLabel, this.apptNameField, this.apptDateLabel, this.apptDateCombo);
        apptBox.getChildren().addAll(this.apptDocLabel, this.apptDocCombo, this.apptTimeLabel, this.apptTimeList);
        apptBox.setPadding(new Insets(20, 20, 20,20));
        
        
        this.appointmentScene = new Scene(apptBox, 500,500);
        
            
    }
    public void setCheckInScene(){
        
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
        }
    }
    
    public Scene getAppointmentScene(){
        return this.appointmentScene;
    }
    public Scene getcheckInScene(){
        
        return this.checkInScene;
    }
    public Scene getLandingScene(){
        return this.landingPageScene;
    }
    
}
