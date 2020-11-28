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
    private HealthCareController controller;
    
     boolean logoutPrimed, cancelPrimed;
     Database db;
     SystemTimer tm;
     String popupMessage;
     Label popupMessageLabel;
     Button popupOK, popupYes, popupNo;
     Scene popupWindow;
     Stage popup;
     Stage window2;
    
     VBox popupBox;
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
    CEOInterface ceoInterfae;
    
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
        this.tm = new SystemTimer();
        staffInterface = new StaffInterface(this.patientQueue, this, this.db);
        this.controller = new HealthCareController(this, staffInterface);
        this.ceoInterfae = new CEOInterface(this);
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
                        if(u.getId() == curPatient.getDoctorID()){
                            
                            this.nurseDocBox.getSelectionModel().selectLast();
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
        ArrayList<TreatmentRecord> treatRec = curPatient.getTreatment_record_arr();
        TreatmentRecord prevTreat = null;
        if(treatRec.size() > 1){
            prevTreat = treatRec.get(treatRec.size() -2);
        }
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
            if(prevTreat != null)
                this.weightField.setText(prevTreat.getWeight() + "");
        
        this.heightField.setText(curPatient.getCurrent_visit().getHeight() + "");
            if(prevTreat != null)
                this.heightField.setText(prevTreat.getHeight() + "");
        
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
        this.controller.handler(e);
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
