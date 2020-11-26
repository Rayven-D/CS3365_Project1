/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javax.swing.*;

/**
 *
 * @author Rayven
 */
public class HealthCareInterface extends Application {
    
    private Database db;
    private Timer tm;
     ArrayList<User> userList;
    
        
    Label usernameLabel,passwordLabel;
    TextField usernameTextField;
    PasswordField passwordTextField;
    Label errorMessage;
    Button login, saveChanges;
    HBox passwordBox, usernameBox;
    VBox loginInfo;
    PasswordAuth passAuth;
    Stage userInterface;
    Label name, birthday, address, social, insurance, payment, id;
    Label appDate, reasonVisit, weight, height, bp, treatment, prescription, docVisiting;
    TextField nameField,  addressField, socialField, insuranceField, paymentField, idField;
    TextField appDateField, weightField, heightField, bpField, docVisitedField;
    TextArea reasonVisitField, treatmentField, prescriptionField;
    ComboBox treatments;
    HBox nameBdayBox,  sipiBox, appDateBox, whbpBox;
    VBox chartBox, topBox, dropDownBox, allBox;
    User curUser;
    PatientChart curPatient;
    
    DatePicker birthdayField;
    
    CheckInQueue patientQueue;
    AppointmentInterface apptInterface;
    
    Scene chartScene;
    
    //Universal LogOut
    Button universalLogout;
    //Nurse 
    ComboBox nurseDocBox;
    Label   nurseDocLabel;
    
    //Landing Page
    Button checkInPatient, setUpAppt, checkInNew;
    Scene landingPageScene;
    
    //Chart Landing Page
    ListView queuedPatients;
    Button chartLandConfirm;
    Scene chartLandScene;
    
    @Override
    public void start(Stage primaryStage) {

        this.userInterface = new Stage();
        this.runLoginInterface();
        this.patientQueue = new CheckInQueue();
        this.db = new Database();
        try{
            this.userList = this.db.getUsers();
        }catch(Exception e){
            e.printStackTrace();
        }
        this.tm = new Timer();
        apptInterface = new AppointmentInterface(this.patientQueue, this, this.db);
        this.universalLogout = new Button("Logout");
            this.universalLogout.setOnAction(event -> {handle(event);});
    }
    public void runLoginInterface(){
        usernameLabel = new Label("Username: ");
        usernameTextField = new TextField("Last Name");
        usernameTextField.setOnAction(e->{
                handle(e);
        });
        
        passwordLabel = new Label("Password:  ");
        passwordTextField = new PasswordField();
        passwordTextField.setOnAction(e->{
                handle(e);
        });
                
        errorMessage = new Label("Username or Password is Incorrect.");
        
        login =new Button("Log In");
        login.setOnAction(e->{
                handle(e);
        });        
        
        usernameBox = new HBox();
        usernameBox.getChildren().addAll(usernameLabel, usernameTextField);
        usernameBox.setAlignment(Pos.CENTER);
        
        passwordBox = new HBox();
        passwordBox.getChildren().addAll(passwordLabel, passwordTextField);
        passwordBox.setAlignment(Pos.CENTER);
        
        loginInfo = new VBox();
        loginInfo.getChildren().addAll(usernameBox, passwordBox, login);
        loginInfo.setAlignment(Pos.CENTER);
        loginInfo.setSpacing(20);   
        
        Scene patientLogin = new Scene(loginInfo, 300, 300);
        userInterface.setScene(patientLogin);
        userInterface.show();
        userInterface.setTitle("Login");
    }
    public void runChartInterface(int patientID){
        patientID -= 500;
        ArrayList<PatientChart> charts = new ArrayList<PatientChart>();
       try{
           Database d = new Database();
           charts = d.getCharts();
       }catch(Exception exep){
           exep.printStackTrace();
       }
       this.curPatient = charts.get(patientID);
        LocalDate localDate = LocalDate.now();
        //Patient Name
        name = new Label("Name: ");
        nameField = new TextField();
        
        //Birthday
        birthday = new Label("\tBirthday: ");
        birthdayField = new DatePicker();
       
        
        //Address
        address = new Label("Address: ");
        addressField = new TextField();
        addressField.setMaxWidth(500);
        
        //Social
        social = new Label("SSN: ");
        socialField = new TextField();
        
        //Insurance
        insurance = new Label("\tInsurance: ");
        insuranceField = new TextField();
        
        //Payment
        payment = new Label("\tPayment: ");
        paymentField = new TextField();
        
        //ID
        id = new Label("\tPatient ID: ");
        idField = new TextField();
        
        //Treatment Menu Dropdown
        treatments = new ComboBox();
        treatments.setOnAction((event) -> {handle(event);});
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try{
            treatments.getItems().add(dtf.format(localDate));
            treatments.getItems().add(curPatient.getPreviousVisit().getAppointment_date());
        }catch(Exception exep){
            exep.printStackTrace();
        }
        
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
            this.appDateField.setText(dtf.format(localDate));
            this.appDateField.setEditable(false);
        appDateBox = new HBox();
        appDateBox.getChildren().addAll(appDate, appDateField);
        
        
        
            
        this.reasonVisit = new Label("Reason for Visit: ");
        this.reasonVisitField = new TextArea();
            this.reasonVisitField.setMaxWidth(1000);
            this.reasonVisitField.setPrefRowCount(3);
            
        this.weight = new Label("Weight: ");
        this.weightField = new TextField();
        
        this.height = new Label("\tHeight: ");
        this.heightField = new TextField();
        
        this.bp = new Label("\tBP: ");
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
            
            
        //Save Changes Button
        this.saveChanges = new Button("Save Changes");
        this.saveChanges.setOnAction(e->{handle(e);});
        
        this.nurseDocLabel = new Label("Attending Doctor: ");
        this.nurseDocBox = new ComboBox();
            try{
                for(User u: db.getUsers()){
                    if(u.getPermissions() == 2){
                        this.nurseDocBox.getItems().add(u.getName());
                        System.out.println(u.getId() + " " + this.curPatient.getDoctorID());
                        if(u.getId() == curPatient.getDoctorID()){
                            
                            this.nurseDocBox.getSelectionModel().selectLast();
                            System.out.println(this.nurseDocBox.getSelectionModel().getSelectedItem());
                        }
                    }
                }
            }catch(Exception e){e.printStackTrace();}
        HBox nurseDocHBox = new HBox();
        nurseDocHBox.getChildren().addAll(this.nurseDocLabel, this.nurseDocBox);
        
        if(curUser.getPermissions() != 1){
            nurseDocHBox.setVisible(false);
        }
            
        chartBox = new VBox();
        chartBox.getChildren().addAll(appDateBox);
        chartBox.getChildren().addAll(whbpBox,reasonVisit, reasonVisitField);
        chartBox.getChildren().addAll(treatment, treatmentField, prescription, prescriptionField, this.saveChanges, this.universalLogout);
        chartBox.setSpacing(5);
        
        allBox = new VBox();
        allBox.getChildren().addAll(topBox,nurseDocHBox, dropDownBox, chartBox);
            allBox.setPadding(new Insets(20,20,20,20));
            allBox.setSpacing(10);
        this.setUserFields();

        this.chartScene = new Scene(allBox, 1250, 750);
        
        
        fillChartInfo(charts.get(patientID));    
    }
    
    public void setChartLandingPage(){
        Label queuedPatientsLabel = new Label("Patients");
        this.queuedPatients = new ListView();
            for(Integer i: this.patientQueue.getDocQueue(this.curUser.getId())){
                if(i != 0){
                    queuedPatients.getItems().add(this.db.getSingleChart(i).getPatient_name());
                    
                }
            }
            this.queuedPatients.getSelectionModel().selectFirst();
        
        this.chartLandConfirm = new Button("Continue");
            this.chartLandConfirm.setOnAction(e -> {handle(e);});
        
        VBox chartLandBox = new VBox();
        chartLandBox.getChildren().addAll(queuedPatientsLabel, this.queuedPatients, this.chartLandConfirm, this.universalLogout);
        
        this.chartLandScene = new Scene(chartLandBox, 400, 400);
        
    }
    
    public void setLandingPage(){
        this.checkInPatient = new Button("Check-In Existing");
            this.checkInPatient.setOnAction(e -> {handle(e);});
        this.checkInNew = new Button("Check-In New");
            this.checkInNew.setOnAction(e -> {handle(e);});
        this.setUpAppt = new Button("New Appointment");
            this.setUpAppt.setOnAction(e -> {handle(e);});
        
        VBox landingBox = new VBox();
        landingBox.getChildren().addAll(this.checkInPatient, this.checkInNew, this.setUpAppt,this.universalLogout);
            landingBox.setPadding(new Insets(20,20,20,20));
        
        this.landingPageScene = new Scene(landingBox, 300,300);
        
        this.userInterface.setScene(this.landingPageScene);
    }
    public void fillChartInfo(PatientChart curPatient){
        this.nameField.setText(curPatient.getPatient_name());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate ld = LocalDate.parse(curPatient.getBirthday(), format);
        this.birthdayField.setValue(ld);
        this.addressField.setText(curPatient.getAddress());
        String soc = "" + curPatient.getSsn();
        soc = soc.replaceAll("(\\d{3})(\\d{2})(\\d{4})", "###-##-$3");
        this.socialField.setText(soc);

        this.insuranceField.setText(curPatient.getInsurance());
    }
    
    public void setUserFields(){
        this.id.setVisible(false);
        this.idField.setVisible(false);
        if(curUser instanceof Doctor || curUser instanceof Nurse){
            if(curUser instanceof Nurse){
                this.treatment.setVisible(false);
                this.treatmentField.setVisible(false);
                this.prescription.setVisible(false);
                this.prescriptionField.setVisible(false);                
            }
            this.payment.setVisible(false);
            this.paymentField.setVisible(false);
        }else if( curUser instanceof Staff){
            this.chartBox.setVisible(false);
            this.dropDownBox.setVisible(false);
        }
        
    }    
    
    public void handle(Event e){
        if(e.getSource() == this.usernameTextField || e.getSource() == this.passwordTextField || e.getSource() == this.login){
            this.passAuth= new PasswordAuth();
            loginInfo.getChildren().remove(errorMessage);
            errorMessage.setText("Username or Password is incorrect.");
            if(!passAuth.validateInfo(this.usernameTextField.getText(), this.passwordTextField.getText())){
                loginInfo.getChildren().add(errorMessage);
            }else{
                curUser = this.passAuth.allowUser();
                if(curUser instanceof Staff){
                    this.setLandingPage();
                    this.userInterface.show();
                    this.userInterface.setTitle("Staff | " + curUser.getName());
                }else{
                    this.setChartLandingPage();
                    this.userInterface.setScene(this.chartLandScene);
                    this.userInterface.show();
                    this.userInterface.setTitle( curUser.getName());
                }
            } 
        }else if(e.getSource() == this.treatments){
            int sel_index = this.treatments.getSelectionModel().getSelectedIndex();
            if(sel_index == 1){
                this.appDateField.setText(this.curPatient.getPreviousVisit().getAppointment_date());
                   
                this.weightField.setText(""+this.curPatient.getPreviousVisit().getWeight());
                    this.weightField.setEditable(false);
                this.heightField.setText(""+this.curPatient.getPreviousVisit().getHeight());
                    this.weightField.setEditable(false);
                this.bpField.setText(""+this.curPatient.getPreviousVisit().getBlood_pressure());
                    this.bpField.setEditable(false);
                this.reasonVisitField.setText(this.curPatient.getPreviousVisit().getReason_for_visit());
                    this.reasonVisitField.setEditable(false);
                this.treatmentField.setText(this.curPatient.getPreviousVisit().getTreatment());
                    this.treatmentField.setEditable(false);
                this.prescriptionField.setText(this.curPatient.getPreviousVisit().getPrescription());
                    this.prescriptionField.setEditable(false);              
            }else if(sel_index == 0){
                this.appDateField.setText(this.curPatient.getCurrent_visit().getAppointment_date());
                   
                this.weightField.setText(""+this.curPatient.getCurrent_visit().getWeight());
                    this.weightField.setEditable(true);
                this.heightField.setText(""+this.curPatient.getCurrent_visit().getHeight());
                    this.weightField.setEditable(true);
                this.bpField.setText(""+this.curPatient.getCurrent_visit().getBlood_pressure());
                    this.bpField.setEditable(true);
                this.reasonVisitField.setText(this.curPatient.getCurrent_visit().getReason_for_visit());
                    this.reasonVisitField.setEditable(true);
                this.treatmentField.setText(this.curPatient.getCurrent_visit().getTreatment());
                    this.treatmentField.setEditable(true);
                this.prescriptionField.setText(this.curPatient.getCurrent_visit().getPrescription());
                    this.prescriptionField.setEditable(true);    
            }
        }else if(e.getSource() == this.chartLandConfirm){
            int patID = this.queuedPatients.getSelectionModel().getSelectedIndex();
            patID = this.patientQueue.getDocQueue(this.curUser.getId()).get(patID);
            this.runChartInterface(patID);
            this.userInterface.setScene(this.chartScene);
            this.userInterface.show();
            this.userInterface.setTitle(this.curUser.getName() + " | " + this.curPatient.getPatient_name());
        }
        else if(e.getSource() == this.checkInNew || e.getSource() == this.checkInPatient){
            if(e.getSource() == this.checkInNew){
                this.userInterface.setScene(apptInterface.getNewCheckInScene());
                this.userInterface.show();
                this.userInterface.setTitle("Check-In (New) | " + this.curUser.getName() );
            }
            else{
                this.userInterface.setScene(apptInterface.getcheckInScene());
                this.userInterface.show();
                this.userInterface.setTitle("Check-In | " + this.curUser.getName() );
            }
        }else if(e.getSource() == this.setUpAppt){
            this.userInterface.setScene(apptInterface.getAppointmentScene());
            this.userInterface.show();
            this.userInterface.setTitle("Check-In | " + this.curUser.getName() );
        }
        else if(e.getSource() == apptInterface.inConfirm || e.getSource() == apptInterface.apptCancel || e.getSource() == apptInterface.apptConfirm){            
            if(e.getSource() == apptInterface.inConfirm){
                
                this.patientQueue.addToQueue(-1, apptInterface.getSelectedPatientID());
                ArrayList<Day> temp = db.getSingleAvailability(apptInterface.getSelectedDocID());
               for(Day d: temp){
                        if(d.getDate().equals(tm.getTodayDate())){
                            int []avails = d.getAvailabilityTimes();
                            for(int j = 0; j < avails.length; j++){
                                if(avails[j] == apptInterface.getSelectedPatientID()){
                                    avails[j] = 0;
                                    break;
                                }                               
                            }
                            //write to DB here to clear DP of the appointment
                        }
                    }
                    //write to DB here for a new Patient Chart Object
            }
            if(e.getSource() == this.apptInterface.apptConfirm){
            try{
                for(User u: db.getUsers()){
                    if(u.getName().equals(apptInterface.apptDocCombo.getValue())){
                        ArrayList<Day> tempDay = db.getSingleAvailability(u.getId());
                       for(Day day: tempDay){
                           if(day.getDate().equals(apptInterface.apptDateCombo.getValue())){

                               int avail[] = day.getAvailabilityTimes();
                               int tempPatientID = -1;
                               for(PatientChart pc: db.getCharts()){
                                   if(pc.getPatient_name().equals(apptInterface.apptNameField.getText())){
                                        tempPatientID = pc.getPatient_id();
                                        break;
                                   }
                               }
                               apptInterface.apptTimeList.getSelectionModel().getSelectedIndex();
                                avail [apptInterface.apptTimeList.getSelectionModel().getSelectedIndex()] = tempPatientID ;
                                day.setAvailabilityTimes(avail); 
                                for(Day ddd :tempDay){
                                    System.out.println(ddd.getDate());
                                    int []tempDDD = ddd.getAvailabilityTimes();
                                    for(int tempInt: tempDDD){
                                        
                                        System.out.print(tempInt +  " ");
                                        
                                    }
                                    System.out.println();
                                }
                               db.saveSingleAvailability(u.getId(), tempDay);
                               break;
                           }
                                                  
                            } 
                            break;
                         }
                     }
                 }catch(Exception exep){exep.printStackTrace();}
             }  
        }
        else if(e.getSource() == this.saveChanges ){
            this.patientQueue.removeFromQueue(curUser.getId(), new Integer(curPatient.getPatient_id()));
            if(curUser.getPermissions() == 1){
                try{
                    for(User u: db.getUsers()){
                        if(u.getName().equals(this.nurseDocBox.getValue())){
                            System.out.println("k");
                            this.patientQueue.addToQueue(u.getId(), this.curPatient.getPatient_id());
                            this.setChartLandingPage();
                            this.userInterface.setScene(this.chartLandScene);
                            this.userInterface.show();
                            this.userInterface.setTitle(this.curUser.getName());
                            break;
                        }
                    }
                }catch(Exception exep){exep.printStackTrace();}
            }
        }
        else if(e.getSource() == this.universalLogout){
            this.runLoginInterface();
        }
    }   


}
