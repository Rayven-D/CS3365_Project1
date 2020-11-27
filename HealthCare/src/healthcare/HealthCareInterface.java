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
import javafx.stage.Popup;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javax.swing.*;

/**
 *
 * @author Rayven
 */
public class HealthCareInterface extends Application {
    
    private boolean logoutPrimed, cancelPrimed;
    private Database db;
    private Timer tm;
    private String popupMessage;
    private Label popupMessageLabel;
    private Button popupOK, popupYes, popupNo;
    private Scene popupWindow;
    private Stage popup;
    private Stage window2;
    
    private VBox popupBox;
     ArrayList<User> userList;
     ArrayList<PatientChart> patientList;
    
        
    Label usernameLabel,passwordLabel;
    TextField usernameTextField;
    PasswordField passwordTextField;
    Label errorMessage;
    Button login, saveChanges, goBack, inCheckIn, paymentInfo;
    HBox passwordBox, usernameBox;
    VBox loginInfo;
    PasswordAuth passAuth;
    Stage userInterface;
    Label name, birthday, address, social, insurance, payment, id;
    Label appDate, reasonVisit, weight, height, bp, treatment, prescription, docVisiting;
    TextField nameField,  addressField, socialField, insuranceField, idField;
    TextField appDateField, weightField, heightField, bpField, docVisitedField;
    TextArea reasonVisitField, treatmentField, prescriptionField;
    ComboBox treatments;
    HBox nameBdayBox,  sipiBox, appDateBox, whbpBox;
    VBox chartBox, topBox, dropDownBox, allBox;
    User curUser;
    PatientChart curPatient;
    
    DatePicker birthdayField;
    
    CheckInQueue patientQueue;
    StaffInterface staffInterface;
    
    Scene chartScene;
    
    //Universal LogOut
    Button universalLogout;
   
    //Nurse 
    ComboBox nurseDocBox;
    Label   nurseDocLabel;
    
    //Staff Landing Page
    Button checkInPatient, setUpAppt, changeAppt, deleteAppt;
    Scene landingPageScene;
    
    //Chart Landing Page
    ListView queuedPatients;
    Button chartLandConfirm;
    Scene chartLandScene;
    
    @Override
    public void start(Stage primaryStage) {
        this.universalLogout = new Button("Logout");
            this.universalLogout.setOnAction(event -> {handle(event);});
        this.goBack = new Button("Go Back");
            this.goBack.setOnAction(e -> {handle(e);});
        this.popupMessage = "";
        this.popupMessageLabel = new Label(popupMessage);
            this.popupNo = new Button("No");
                this.popupNo.setOnAction(e -> {handle(e);});
            this.popupOK = new Button("OK");
                this.popupOK.setOnAction(e -> {handle(e);});
            this.popupYes = new Button("Yes");
                this.popupYes.setOnAction(e -> {handle(e);});
  
            HBox popupButton = new HBox();
            popupButton.getChildren().addAll( popupYes, popupNo, popupOK, universalLogout );
                popupButton.setAlignment(Pos.BOTTOM_CENTER);
            popupBox = new VBox();
            popupBox.getChildren().addAll(popupMessageLabel, popupButton );
                popupBox.setAlignment(Pos.CENTER);
        
            
        this.popupWindow = new Scene(popupBox, 300,100);

            
        this.userInterface = new Stage();
            
        this.runLoginInterface();
        this.patientQueue = new CheckInQueue();
        this.db = new Database();
        try{
            this.patientList = this.db.getCharts();
            this.userList = this.db.getUsers();
        }catch(Exception e){
            e.printStackTrace();
        }
        this.tm = new Timer();
        staffInterface = new StaffInterface(this.patientQueue, this, this.db);

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
        ArrayList<PatientChart> charts = new ArrayList<PatientChart>();
       try{
           Database d = new Database();
           charts = d.getCharts();
       }catch(Exception exep){
           exep.printStackTrace();
       }
       this.curPatient = db.getSingleChart(patientID);
       //ResetPayment
       
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
        paymentInfo = new Button("Payment Info");
            paymentInfo.setOnAction((e) -> {handle(e);});
        
        //ID
        id = new Label("\tPatient ID: ");
        idField = new TextField();
        
        //Treatment Menu Dropdown
        treatments = new ComboBox();
        treatments.setOnAction((event) -> {handle(event);});
        try{
            for(TreatmentRecord tr: curPatient.getTreatment_record_arr()){
                treatments.getItems().add(tr.getAppointment_date());
            }
        }catch(Exception exep){
            exep.printStackTrace();
        }
        
        treatments.getSelectionModel().selectLast();
        
        
        //Date
        appDate = new Label(treatments.getValue()+"");
        
        nameBdayBox = new HBox();
        nameBdayBox.getChildren().addAll(name, nameField);
        nameBdayBox.getChildren().addAll(birthday, birthdayField);
            //properties
            nameBdayBox.setSpacing(10);
        
        

            
        sipiBox = new HBox();
        sipiBox.getChildren().addAll(social, socialField, insurance, insuranceField, payment, paymentInfo, id, idField);
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
        this.saveChanges = new Button("Save and Exit");
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
        
        if(curUser.getPermissions() >1 ){
            nurseDocHBox.setVisible(false);
        }
        
        this.inCheckIn = new Button("Check-In");
        this.inCheckIn.setOnAction(e -> {handle(e);});
        if(curUser.getPermissions() != 0){
            this.inCheckIn.setVisible(false);
        }
        
        
            
        chartBox = new VBox();
        chartBox.getChildren().addAll(appDateBox);
        chartBox.getChildren().addAll(whbpBox,reasonVisit, reasonVisitField);
        chartBox.getChildren().addAll(treatment, treatmentField, prescription, prescriptionField,  this.saveChanges,this.goBack, this.universalLogout);
        chartBox.setSpacing(5);
        
        allBox = new VBox();
        allBox.getChildren().addAll(topBox,nurseDocHBox, dropDownBox, chartBox, this.inCheckIn);
            allBox.setPadding(new Insets(20,20,20,20));
            allBox.setSpacing(10);
        this.setUserFields();

        this.chartScene = new Scene(allBox, 1250, 750);
        
        
        this.fillChartInfo(curPatient);
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
        this.checkInPatient = new Button("Check-In");
            this.checkInPatient.setOnAction(e -> {handle(e);});
        this.setUpAppt = new Button("Make Appointment");
            this.setUpAppt.setOnAction(e -> {handle(e);});
        this.changeAppt = new Button("Change an Appointment");
            this.changeAppt.setOnAction(e -> {handle(e);});
            
        this.deleteAppt = new Button("Delete Appointment");
            this.deleteAppt.setOnAction(e ->{handle(e);});
        
        VBox landingBox = new VBox();
        landingBox.getChildren().addAll(this.checkInPatient, this.setUpAppt, this.changeAppt, this.deleteAppt, this.universalLogout);
            landingBox.setPadding(new Insets(20,20,20,20));
        
        this.landingPageScene = new Scene(landingBox, 300,300);
        
        this.userInterface.setScene(this.landingPageScene);
    }
    public void fillChartInfo(PatientChart curPatient){
        this.nameField.setText(curPatient.getPatient_name());
        if(curPatient.getBirthday() == null || curPatient.getBirthday().length() <= 0){
            
        }else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate ld = LocalDate.parse(curPatient.getBirthday(), format);
            this.birthdayField.setValue(ld);
        }
        this.addressField.setText(curPatient.getAddress());
        String soc = "" + curPatient.getSsn();
        
        this.socialField.setText(soc);

        this.insuranceField.setText(curPatient.getInsurance());
        
        this.weightField.setText(curPatient.getCurrent_visit().getWeight() + "");
        
        this.heightField.setText(curPatient.getCurrent_visit().getHeight() + "");
        
        this.bpField.setText(curPatient.getCurrent_visit().getBlood_pressure());
        
        this.reasonVisitField.setText(curPatient.getCurrent_visit().getReason_for_visit());
        
        this.treatmentField.setText(curPatient.getCurrent_visit().getTreatment());
        
        this.prescriptionField.setText(curPatient.getCurrent_visit().getPrescription());
       
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
            this.paymentInfo.setVisible(false);
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
            TreatmentRecord curTreatment = this.curPatient.getTreatment_record_arr().get(treatments.getSelectionModel().getSelectedIndex());
            
            this.bpField.setText(curTreatment.getBlood_pressure());
            this.weightField.setText(curTreatment.getWeight() + "");
            this.heightField.setText(curTreatment.getHeight() + "");
            this.reasonVisitField.setText(curTreatment.getReason_for_visit());
            this.treatmentField.setText(curTreatment.getTreatment());
            this.prescriptionField.setText(curTreatment.getPrescription());
            
        }else if(e.getSource() == this.chartLandConfirm){
            if(curUser.getPermissions()  < 1 || curUser.getPermissions() > 2){
                this.popupConfirm("Access Denied");
                this.runLoginInterface();
                return;
            }
            int patID = this.queuedPatients.getSelectionModel().getSelectedIndex();
            patID = this.patientQueue.getDocQueue(this.curUser.getId()).get(patID);
            this.runChartInterface(patID);
            this.userInterface.setScene(this.chartScene);
            this.userInterface.show();
            this.userInterface.setTitle(this.curUser.getName() + " | " + this.curPatient.getPatient_name());
        }
        else if( e.getSource() == this.checkInPatient){
                staffInterface.setCheckInScene();
                this.userInterface.setScene(staffInterface.getcheckInScene());
                this.userInterface.show();
                this.userInterface.setTitle("Check-In | " + this.curUser.getName() );
            
        }else if(e.getSource() == this.setUpAppt){
            this.userInterface.setScene(staffInterface.getAppointmentScene());
            this.userInterface.show();
            this.userInterface.setTitle("Check-In | " + this.curUser.getName() );
        }
        else if(e.getSource() == staffInterface.inConfirm || e.getSource() == staffInterface.apptConfirm){            
            if(e.getSource() == staffInterface.inConfirm){
                if(staffInterface.inNameField.getText().length() == 0 || staffInterface.inBdayPicker.getValue() == null){
                    this.popupConfirm("Please enter name and birthday.");
                    
                }else{
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String selectedBday = staffInterface.inBdayPicker.getValue().format(form);
                    if(this.curUser.getPermissions() == 0){
                        int patID = -1;
                        PatientChart inPatient = null;
                        for(PatientChart pc: this.patientList){
                            if(pc.getPatient_name().equals(staffInterface.inNameField.getText())){
                                patID = pc.getPatient_id();
                                inPatient = pc;
                                break;
                            }
                        }
                        String patDoc ="";
                        int patDocID = -1;
                        boolean found = false;
                        for(User u: this.userList){
                            if(u.getPermissions() == 2){
                                ArrayList<Day> day = db.getSingleAvailability(u.getId());
                                for(Day d: day){
                                    int []availTimes = d.getAvailabilityTimes();
                                    for(int i =0 ;i < availTimes.length; i++){
                                        if(availTimes[i] == patID){
                                            availTimes[i] = -patID;
                                            found = true;
                                            d.setAvailabilityTimes(availTimes);
                                            db.saveSingleAvailability(u.getId(), day);
                                            patDoc = u.getName();
                                            patDocID = u.getId();
                                        }
                                    }
                                }
                            }
                        }
                        
                        if(found){
                            
                            this.runChartInterface(patID);
                            this.nurseDocBox.getSelectionModel().select(patDoc);
                                int selectedDocID = -1;
                            for(User u: this.userList){
                                if(u.getName().equals(this.nurseDocBox.getSelectionModel().getSelectedItem())){
                                    selectedDocID = u.getId();
                                }
                            }
                            this.curPatient.setDoctorID(selectedDocID);
                            this.curPatient.setBirthday(selectedBday);
                            
                            
                            this.userInterface.setScene(this.chartScene);
                            this.userInterface.show();
                            this.nurseDocBox.getSelectionModel().select(patDoc);
                        }else{
                            this.popupConfirm("Patient not found");
                        }
                        
                    }else{
                        this.popupConfirm("Access Denied");
                    }
                    
                }
            }
            if(e.getSource() == this.staffInterface.apptConfirm){
            try{
                 
                int patID = -1;
                for(PatientChart pc: this.patientList){
                    if(pc.getPatient_name().equals(staffInterface.apptNameField.getText())){
                        patID = pc.getPatient_id();
                    }
                }
                System.out.println(patID);
                if(patID != -1){
                    for(User u: this.userList){
                        if(u.getPermissions() == 2){
                            for(Day d: db.getSingleAvailability(u.getId()) ){
                                for(int i: d.getAvailabilityTimes()){
                                    if(i == patID){
                                        staffInterface.setAppointmentScene();
                                        this.userInterface.setScene(staffInterface.getAppointmentScene());
                                        this.userInterface.show();
                                        this.popupConfirm("Already have an appointment");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
                if(staffInterface.apptDocCombo.getSelectionModel().getSelectedIndex() < 0 || staffInterface.apptDateCombo.getSelectionModel().getSelectedIndex() < 0 || staffInterface.apptTimeList.getSelectionModel().getSelectedIndex() < 0){
                    this.popupConfirm("Please select a doctor and/or time");
                    return;
                } 
                for(User u: db.getUsers()){
                    if(u.getName().equals(staffInterface.apptDocCombo.getValue())){
                        ArrayList<Day> tempDay = db.getSingleAvailability(u.getId());
                       for(Day day: tempDay){
                           if(day.getDate().equals(staffInterface.apptDateCombo.getValue())){
                               this.patientList = db.getCharts();
                               int avail[] = day.getAvailabilityTimes();
                               int tempPatientID = this.patientList.get(patientList.size() - 1).getPatient_id() + 1;
                                
                               
                               for(PatientChart pc: db.getCharts()){
                                   if(pc.getPatient_name().equals(staffInterface.apptNameField.getText())){
                                        tempPatientID = pc.getPatient_id();
                                        
                                        break;
                                   }
                               }
                                staffInterface.apptTimeList.getSelectionModel().getSelectedIndex();
                                 avail [staffInterface.apptTimeList.getSelectionModel().getSelectedIndex()] = tempPatientID ;
                                 day.setAvailabilityTimes(avail); 
                                 
                                db.saveSingleAvailability(u.getId(), tempDay);
                                
                                this.popupConfirm("Added patient appointment.");
                                Thread.sleep(100);
                                staffInterface.setAppointmentScene();
                                this.userInterface.setScene(staffInterface.getAppointmentScene());
                                break;
                           }
                                                  
                            } 
                            break;
                         }
                     }
                 }catch(Exception exep){exep.printStackTrace();}
            }
            
        }else if(e.getSource() == this.inCheckIn){
            this.curPatient.setPatient_name(this.nameField.getText());
            
            DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            this.curPatient.setBirthday(this.birthdayField.getValue().format(form));
            
            this.curPatient.setAddress(this.addressField.getText());
                  
            String ssn = this.socialField.getText();
            
            this.curPatient.setSsn(Integer.parseInt(ssn));
            
            this.curPatient.setInsurance(this.insuranceField.getText());
            
            
            TreatmentRecord newRec = new TreatmentRecord();
            newRec.setAppointment_date(tm.getTodayDate());
            this.curPatient.setCurrent_visit(newRec);
            db.saveSingleChart(curPatient, curPatient.getPatient_id());
            
            this.patientQueue.addToQueue(-1 , curPatient.getPatient_id());
            this.setLandingPage();
            this.userInterface.setScene(this.landingPageScene);
            this.userInterface.show();
            this.popupConfirm("Checked In patient.");
        }
        else if(e.getSource() == this.changeAppt){
            staffInterface.setChangeAppointment();
            this.userInterface.setScene(staffInterface.getChangeAppointmentScene());
        }
        else if(e.getSource() == staffInterface.changeNameButton){
            int tempPatientID = -1; 
            for(PatientChart pc: this.patientList){
                if(pc.getPatient_name().equals(staffInterface.changeNameField.getText())){
                    tempPatientID = pc.getPatient_id();
                    
                }
            }
            System.out.println(tempPatientID);
            boolean found = false;
            for(User u: this.userList){
                if(u.getPermissions()== 2){
                    for(Day d: this.db.getSingleAvailability(u.getId())){
                        int count = 0;
                        for(int i: d.getAvailabilityTimes()){
                            if(i == tempPatientID){
                                staffInterface.changeDocBox.getSelectionModel().select(u.getName());
                                staffInterface.changeDocBox.getSelectionModel().clearAndSelect(staffInterface.changeDocBox.getSelectionModel().getSelectedIndex());
                                staffInterface.curApptLabel.setText("Current Appointment: " + d.getDate() + " at " + staffInterface.times.get(count));
                                System.out.println("k");
                                found =true;
                                break;
                            }
                            count++;
                        }
                        if(found)
                            break;
                        
                    }
                }
                if(found)
                    break;
            }
            if(!found){
               this.popupConfirm("No appointment"); 
            }
        }
        else if(e.getSource() == staffInterface.changeCancel || e.getSource() == staffInterface.deleteGoBack || e.getSource() == staffInterface.apptCancel){
            this.cancelPrimed = true;
            this.logoutPrimed = false;
            this.popupYesNo("Are you sure you want to exit?");
            
        }
        else if(e.getSource() == staffInterface.changeDateCombo){
            staffInterface.changeTimeList.getItems().clear();
            int tempUserID = -1;
            for(User u: this.userList){
                if(u.getName().equals(staffInterface.changeDocBox.getValue())){
                    tempUserID = u.getId();
                    break;
                }
            }
            for(Day d: db.getSingleAvailability(tempUserID)){
                if(d.getDate().equals(staffInterface.changeDateCombo.getValue())){
                    int availTime[] = d.getAvailabilityTimes();
                    for(int i = 0; i < availTime.length ; i++){
                        String s = staffInterface.times.get(i);
                        if(availTime[i] != 0){
                            for(PatientChart pc: this.patientList){
                                if(pc.getPatient_id() == availTime[i]){
                                    s += " - " + pc.getPatient_name();
                                }
                            }
                        }
                        staffInterface.changeTimeList.getItems().add(s);
                    }
                }
            }
        }else if(e.getSource() == staffInterface.changeConfirm){
            if(staffInterface.changeTimeList.getSelectionModel().getSelectedIndex() < 0){
                this.popupConfirm("Please select a time.");
            }else{
                int tempUserID = -1;
                for(User u: this.userList){
                    if(u.getName().equals(staffInterface.changeDocBox.getValue()))
                    {
                        tempUserID = u.getId();
                        break;
                    }
                }
                
               int tempPatientID = -1;
               for(PatientChart pc: this.patientList){
                   if(pc.getPatient_name().equals(staffInterface.changeNameField.getText()))
                   {
                       tempPatientID = pc.getPatient_id();
                       break;
                   }
               }
                
                ArrayList<Day> days = db.getSingleAvailability(tempUserID);
                boolean setNew = false;
                for(Day d: days){
                    //delete last appointment
                    int [] tempTimes = d.getAvailabilityTimes();
                        for(int i = 0;i < tempTimes.length; i++){
                            if(tempTimes[i] ==tempPatientID){
                                tempTimes[i] = 0;
                            }
                            d.setAvailabilityTimes(tempTimes);
                        }
                    if(d.getDate().equals(staffInterface.changeDateCombo.getValue())){
                        int []availTime = d.getAvailabilityTimes();

                        if( availTime[staffInterface.changeTimeList.getSelectionModel().getSelectedIndex()]  == 0){
                            availTime[staffInterface.changeTimeList.getSelectionModel().getSelectedIndex()] = tempPatientID;
                            d.setAvailabilityTimes(availTime);
                            setNew = true;
                        }
                        else{
                            this.popupConfirm("Please select a different date/time");
                        }
                    }
                    
                    
                }
                
                if(setNew){
                    db.saveSingleAvailability(tempUserID, days);
                    this.popupConfirm("Changed Appointment");
                    staffInterface.setChangeAppointment();
                    this.userInterface.setScene(staffInterface.getChangeAppointmentScene());
                    this.userInterface.show();
                }
            }
            
        }else if(e.getSource() == this.deleteAppt){
            staffInterface.setDeleteAppointmentScene();
            this.userInterface.setScene(staffInterface.getDeleteAppointmentScene());
            this.userInterface.show();
        }
        else if(e.getSource() == staffInterface.deleteNameOK){
            int patID = -1;
            String docName = "";
            String apptDate = "";
            String apptTime = "";
            for(PatientChart pc: this.patientList){
                if(pc.getPatient_name().equals(staffInterface.deleteNameField.getText())){
                    patID = pc.getPatient_id();
                }
            }
            if(patID == -1){
                this.popupConfirm("No Patient Found");
            }else {
                for(User u: this.userList){
                    if(u.getPermissions() == 2){
                        for(Day d: db.getSingleAvailability(u.getId())){
                            int count = 0;
                            for(int i: d.getAvailabilityTimes()){
                                if(patID == i){
                                    docName = u.getName();
                                    apptDate = d.getDate();
                                    apptTime = staffInterface.times.get(count);
                                }
                                count++;
                            }
                        }
                    }
                }
                if(apptTime.length() > 0){
                    staffInterface.deleteCurAppointmentInfo.setText("Current Appointment: \n" + docName + " on " + apptDate + " at " + apptTime);
                    
                }
                else
                    this.popupConfirm("No Appointment Found");
            }
        }
        else if(e.getSource() == staffInterface.cancelAppointment){
            if(staffInterface.deleteNameField.getText().length() > 0){
                int patID = -1;
                for(PatientChart pc: this.patientList){
                    if(pc.getPatient_name().equals(staffInterface.deleteNameField.getText())){
                        patID = pc.getPatient_id();
                        break;
                    }
                }
                boolean found = false;
                for(User u: this.userList){
                    if(u.getPermissions() == 2){
                        ArrayList<Day> days = db.getSingleAvailability(u.getId());
                        for(Day d: days){
                            int availDates [] = d.getAvailabilityTimes();
                            for(int i = 0; i < availDates.length; i++){
                                if(availDates[i] == patID){
                                    found = true;
                                    availDates[i] = 0;
                                    d.setAvailabilityTimes(availDates);
                                    db.saveSingleAvailability(u.getId(), days);
                                    break;
                                }
                            }
                            if(found)
                                break;
                        }
                    }
                    if(found)
                        break;
                }
                this.popupConfirm("Deleted Appointment");
                staffInterface.setDeleteAppointmentScene();
                this.userInterface.setScene(staffInterface.getDeleteAppointmentScene());
                this.userInterface.show();
            }else{
                this.popupConfirm("Please enter a name");
            }
        }else if(e.getSource() == this.paymentInfo){
            if(curUser.getPermissions() != 0){
                popupConfirm("Access Denied");
                return;
            }
            
            staffInterface.setPaymentScene();
            double randomAmount = (double)(Math.random() * 200 + 50);
            window2 = new Stage();
            String str = String.format("$ %.2f", randomAmount);
            staffInterface.amountOweLabel.setText(str);
            window2.setScene(staffInterface.getPaymentScene());
            window2.show();
        }
        else if(e.getSource() == staffInterface.creditButton || e.getSource() == staffInterface.debitButton || e.getSource() == staffInterface.cashButton){
            String paymentType = "";
            float amount = Float.parseFloat(staffInterface.amountOweLabel.getText().replaceAll("[^0-9.]", ""));
            int referenceNumber = (int)(Math.random() * 89999999 + 10000000);
            int pin = -1;
            if(e.getSource() == staffInterface.cashButton){
                 paymentType = "Cash";
                 popupConfirm("Payment Approved");
            }else if(e.getSource() == staffInterface.debitButton){
                paymentType = "Debit";
                pin = (int)(Math.random() * 8999 + 1000);
                
            }else if(e.getSource() == staffInterface.creditButton){
                paymentType = "Credit";
            }
            if( (int)(Math.random()*2 + 1) % 2 != 0 && e.getSource() != staffInterface.cashButton){
                popupConfirm("Payment Declined");
            }else {
                PaymentInformation pi = new PaymentInformation(tm.getTodayDate(), amount, paymentType, referenceNumber, pin);
                this.curPatient.setPatient_payment(pi);
                this.db.saveSingleChart(curPatient, curPatient.getPatient_id());
                this.popupConfirm("Payment Accepted");
                staffInterface.printButton.setVisible(true);
            }
            
        }else if(e.getSource() == staffInterface.printButton){
            this.popupConfirm("Printing Receipt");
        }else if(e.getSource() == staffInterface.exitButton){
            this.window2.close();
        }
        else if(e.getSource() == this.saveChanges ){
            this.patientQueue.removeFromQueue(curUser.getId(), new Integer(curPatient.getPatient_id()));
            if(curUser.getPermissions() == 1 || curUser.getPermissions() == 2){
                try{
                    for(User u: db.getUsers()){
                        if(u.getName().equals(this.nurseDocBox.getValue())){
                            
                            ArrayList<TreatmentRecord> treatRec = this.curPatient.getTreatment_record_arr();
                            TreatmentRecord curTreatment =  treatRec.remove(treatRec.size() -1);
                            curTreatment.setAppointment_date(tm.getTodayDate());
                            curTreatment.setWeight(Double.parseDouble(this.weightField.getText()));
                            curTreatment.setHeight(Double.parseDouble(this.heightField.getText()));
                            curTreatment.setBlood_pressure(this.bpField.getText());
                            curTreatment.setReason_for_visit(this.reasonVisitField.getText());
                            curTreatment.setTreatment(this.treatmentField.getText());
                            curTreatment.setPrescription(this.prescriptionField.getText());
                            curTreatment.setVisited_doctor(u.getName());
                            curPatient.setCurrent_visit(curTreatment);
                            curPatient.setTreatment_record_arr(treatRec);
                            
                            if(curUser.getPermissions() == 1)
                                this.patientQueue.addToQueue(u.getId(), this.curPatient.getPatient_id());
                            
                            this.patientQueue.removeFromQueue(curUser.getId(), curPatient.getPatient_id() );
                            this.db.saveSingleChart(curPatient, curPatient.getPatient_id());
                            this.popupConfirm("Saved changes");
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
        else if(e.getSource() == this.universalLogout || e.getSource() == this.staffInterface.apptCancel || e.getSource() == this.staffInterface.inCancel || e.getSource() == this.goBack){
            System.out.println("pressed");
            this.logoutPrimed = true;
            String message = "logout";
            if(e.getSource() == this.staffInterface.apptCancel || e.getSource() == this.staffInterface.inCancel || e.getSource() == this.goBack){
                message = "go back";
                this.logoutPrimed = false;
                this.cancelPrimed = true;
            }
            this.popupYesNo("Are you sure you want to " + message +  " ?" );
            
        }
        else if(e.getSource() == this.popupYes || e.getSource() == this.popupNo || e.getSource() == this.popupOK){
            if(e.getSource() == this.popupYes){
                popup.close();
                if(this.logoutPrimed){
                    this.runLoginInterface();
                }else if(this.cancelPrimed){
                    if(this.curUser.getPermissions() == 0)
                        this.setLandingPage();
                    else if(this.curUser.getPermissions() <= 2){
                        this.setChartLandingPage();
                    }
                }
                this.logoutPrimed = false;
                this.cancelPrimed = false;
            }           
            else{
                popup.close();
            }
        }
    }   

    public void popupConfirm(String s){
        this.popupYes.setVisible(false);
        this.popupNo.setVisible(false);
        this.popupOK.setVisible(true);
        
        this.popupMessageLabel.setText(s);
        popup = new Stage();
        popup.setScene(this.popupWindow);
        popup.show();
        popup.setTitle("Confirmation");
        popup.setAlwaysOnTop(true);
        
    }
    
    public void popupYesNo(String s){
        this.popupYes.setVisible(true);
        this.popupNo.setVisible(true);
        this.popupOK.setVisible(false);
        
        
        this.popupMessageLabel.setText(s);
        popup = new Stage();
        popup.setScene(this.popupWindow);
        popup.show();
        popup.setTitle("Are you sure?");
        popup.setAlwaysOnTop(true);
        
        
    }

}
