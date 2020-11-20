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

/**
 *
 * @author Rayven
 */
public class HealthCareInterface extends Application {
    
    
    Label usernameLabel,passwordLabel;
    TextField usernameTextField;
    PasswordField passwordTextField;
    Label errorMessage;
    Button login, saveChanges;
    HBox passwordBox, usernameBox;
    VBox loginInfo;
    PasswordAuth passAuth;
    ChartController cc = null;
    Stage userInterface;
    Label name, birthday, address, social, insurance, payment, id;
    Label appDate, reasonVisit, weight, height, bp, treatment, prescription, docVisiting;
    TextField nameField, birthdayField, addressField, socialField, insuranceField, paymentField, idField;
    TextField appDateField, weightField, heightField, bpField, docVisitedField;
    TextArea reasonVisitField, treatmentField, prescriptionField;
    ComboBox treatments;
    HBox nameBdayBox,  sipiBox, appDateBox, whbpBox;
    VBox chartBox, topBox, dropDownBox, allBox;
    User curUser;
    PatientChart curPatient;
    
    
    @Override
    public void start(Stage primaryStage) {
        this.runLoginInterface();
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
        userInterface = new Stage();
        userInterface.setScene(patientLogin);
        userInterface.show();
        userInterface.setTitle("Login");
    }
    public void runChartInterface(){
        ArrayList<PatientChart> charts = new ArrayList<PatientChart>();
       try{
           Database d = new Database();
           charts = d.getCharts();
       }catch(Exception exep){
           exep.printStackTrace();
       }
       this.curPatient = charts.get(0);
        LocalDate localDate = LocalDate.now();
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
            
            
        //Save Changes Button
        this.saveChanges = new Button("Save Changes");
        this.saveChanges.setOnAction(e->{handle(e);});
            
        chartBox = new VBox();
        chartBox.getChildren().addAll(appDateBox);
        chartBox.getChildren().addAll(whbpBox,reasonVisit, reasonVisitField);
        chartBox.getChildren().addAll(treatment, treatmentField, prescription, prescriptionField, this.saveChanges);
        chartBox.setSpacing(5);
        
        allBox = new VBox();
        allBox.getChildren().addAll(topBox, dropDownBox, chartBox);
            allBox.setPadding(new Insets(20,20,20,20));
            allBox.setSpacing(10);
        this.setUserFields();

        Scene chartScene = new Scene(allBox, 1250, 500);
        this.userInterface.setScene(chartScene);
        this.userInterface.show();
        this.userInterface.setTitle(charts.get(0).getPatient_name() + " | " + curUser.getName()) ;
        
        fillChartInfo(charts.get(0));
  
        
    }
    
    public void setStaffInterface(){
        
    }
    
    public void fillChartInfo(PatientChart curPatient){
        this.nameField.setText(curPatient.getPatient_name());
        this.birthdayField.setText(curPatient.getBirthday());
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
                    AppointmentInterface apptInterface = new AppointmentInterface();
                    apptInterface.setAppointmentScene();
                    System.out.println("Staf");
                    this.userInterface.setScene(apptInterface.getAppointmentScene());
                    this.userInterface.show();
                    this.userInterface.setTitle(curUser.getName() + " | Staff");
                }else
                    this.runChartInterface();
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
        }else if(e.getSource() == this.saveChanges){
                        
        }
        
    }   


}
