//CS 2365 OOP Spring 2020 Section 001
//Rayven Jan B. Deray

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.event.*;
import javafx.stage.Stage;

/**
 *
 * @author rjder
 */
public class HealthCareController {
    
    private HealthCareInterface hcInterface;
    private StaffInterface       staffInterface;
    
    public HealthCareController(){}
    
    public HealthCareController(HealthCareInterface hci, StaffInterface si){
        this.hcInterface = hci;
        this.staffInterface = si;
    }
    
    public void handler(Event e){
        if(e.getSource() == hcInterface.usernameTextField || e.getSource() == hcInterface.passwordTextField || e.getSource() == hcInterface.login){
            hcInterface.passAuth= new PasswordAuth();
            hcInterface.loginInfo.getChildren().remove(hcInterface.errorMessage);
            hcInterface.errorMessage.setText("Username or Password is incorrect.");
            if(!hcInterface.passAuth.validateInfo(hcInterface.usernameTextField.getText(), hcInterface.passwordTextField.getText())){
                hcInterface.loginInfo.getChildren().add(hcInterface.errorMessage);
            }else{
                hcInterface.curUser = hcInterface.passAuth.allowUser();
                if(hcInterface.curUser instanceof Staff){
                    hcInterface.setLandingPage();
                    hcInterface.userInterface.show();
                    hcInterface.userInterface.setTitle("Staff | " + hcInterface.curUser.getName());
                }else if(hcInterface.curUser.getPermissions() == 3){//CEO
                    hcInterface.ceoInterfae.setCEOMenuScene();
                    this.hcInterface.userInterface.setScene(hcInterface.ceoInterfae.getCEOScene());
                    hcInterface.userInterface.setTitle("CEO");
                    hcInterface.userInterface.show();
                }
                else{
                    hcInterface.setChartLandingPage();
                    hcInterface.userInterface.setScene(hcInterface.chartLandScene);
                    hcInterface.userInterface.show();
                    hcInterface.userInterface.setTitle( hcInterface.curUser.getName());
                }
            } 
        }else if(e.getSource() == hcInterface.ceoInterfae.viewReportsButton){
            hcInterface.ceoInterfae.setCEOReportsScene();
            this.hcInterface.userInterface.setScene(hcInterface.ceoInterfae.getCEOScene());
            hcInterface.userInterface.setTitle("CEO");
            hcInterface.userInterface.show();
        }
        else if(e.getSource() == hcInterface.treatments){
            TreatmentRecord curTreatment = hcInterface.curPatient.getTreatment_record_arr().get(hcInterface.treatments.getSelectionModel().getSelectedIndex());
            
            hcInterface.bpField.setText(curTreatment.getBlood_pressure());
            hcInterface.weightField.setText(curTreatment.getWeight() + "");
            hcInterface.heightField.setText(curTreatment.getHeight() + "");
            hcInterface.reasonVisitField.setText(curTreatment.getReason_for_visit());
            hcInterface.treatmentField.setText(curTreatment.getTreatment());
            hcInterface.prescriptionField.setText(curTreatment.getPrescription());
            
        }else if(e.getSource() == hcInterface.chartLandConfirm){
            if(hcInterface.curUser.getPermissions()  < 1 || hcInterface.curUser.getPermissions() > 2){
                hcInterface.popupConfirm("Access Denied");
                hcInterface.runLoginInterface();
                return;
            }
            int patID = hcInterface.queuedPatients.getSelectionModel().getSelectedIndex();
            patID = hcInterface.patientQueue.getDocQueue(hcInterface.curUser.getId()).get(patID);
            hcInterface.runChartInterface(patID);
            hcInterface.userInterface.setScene(hcInterface.chartScene);
            hcInterface.userInterface.show();
            hcInterface.userInterface.setTitle(hcInterface.curUser.getName() + " | " + hcInterface.curPatient.getPatient_name());
        }
        else if( e.getSource() == hcInterface.checkInPatient){
                staffInterface.setCheckInScene();
                hcInterface.userInterface.setScene(staffInterface.getcheckInScene());
                hcInterface.userInterface.show();
                hcInterface.userInterface.setTitle("Check-In | " + hcInterface.curUser.getName() );
            
        }else if(e.getSource() == hcInterface.setUpAppt){
            hcInterface.userInterface.setScene(staffInterface.getAppointmentScene());
            hcInterface.userInterface.show();
            hcInterface.userInterface.setTitle("Check-In | " + hcInterface.curUser.getName() );
        }
        else if(e.getSource() == staffInterface.inConfirm || e.getSource() == staffInterface.apptConfirm){            
            if(e.getSource() == staffInterface.inConfirm){
                if(staffInterface.inNameField.getText().length() == 0 || staffInterface.inBdayPicker.getValue() == null){
                    hcInterface.popupConfirm("Please enter name and birthday.");
                    
                }else{
                    DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    String selectedBday = staffInterface.inBdayPicker.getValue().format(form);
                    if(hcInterface.curUser.getPermissions() == 0){
                        int patID = -1;
                        PatientChart inPatient = null;
                        for(PatientChart pc: hcInterface.patientList){
                            if(pc.getPatient_name().equals(staffInterface.inNameField.getText())){
                                patID = pc.getPatient_id();
                                inPatient = pc;
                                break;
                            }
                        }
                        String patDoc ="";
                        int patDocID = -1;
                        boolean found = false;
                        for(User u: hcInterface.userList){
                            if(u.getPermissions() == 2){
                                ArrayList<Day> day = hcInterface.db.getSingleAvailability(u.getId());
                                for(Day d: day){
                                    int []availTimes = d.getAvailabilityTimes();
                                    for(int i =0 ;i < availTimes.length; i++){
                                        if(availTimes[i] == patID){
                                            availTimes[i] = -patID;
                                            found = true;
                                            d.setAvailabilityTimes(availTimes);
                                            hcInterface.db.saveSingleAvailability(u.getId(), day);
                                            patDoc = u.getName();
                                            patDocID = u.getId();
                                        }
                                    }
                                }
                            }
                        }
                        
                        if(found){
                            
                            hcInterface.runChartInterface(patID);
                            hcInterface.nurseDocBox.getSelectionModel().select(patDoc);
                                int selectedDocID = -1;
                            for(User u: hcInterface.userList){
                                if(u.getName().equals(hcInterface.nurseDocBox.getSelectionModel().getSelectedItem())){
                                    selectedDocID = u.getId();
                                }
                            }
                            hcInterface.curPatient.setDoctorID(selectedDocID);
                            hcInterface.curPatient.setBirthday(selectedBday);
                            
                            
                            hcInterface.userInterface.setScene(hcInterface.chartScene);
                            hcInterface.userInterface.show();
                            hcInterface.nurseDocBox.getSelectionModel().select(patDoc);
                        }else{
                            hcInterface.popupConfirm("Patient not found");
                        }
                        
                    }else{
                        hcInterface.popupConfirm("Access Denied");
                    }
                    
                }
            }
            if(e.getSource() == hcInterface.staffInterface.apptConfirm){
            try{
                 
                int patID = -1;
                for(PatientChart pc: hcInterface.patientList){
                    if(pc.getPatient_name().equals(staffInterface.apptNameField.getText())){
                        patID = pc.getPatient_id();
                    }
                }
                if(patID != -1){
                    for(User u: hcInterface.userList){
                        if(u.getPermissions() == 2){
                            for(Day d: hcInterface.db.getSingleAvailability(u.getId()) ){
                                for(int i: d.getAvailabilityTimes()){
                                    if(i == patID){
                                        staffInterface.setAppointmentScene();
                                        hcInterface.userInterface.setScene(staffInterface.getAppointmentScene());
                                        hcInterface.userInterface.show();
                                        hcInterface.popupConfirm("Already have an appointment");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
                else{
                    int lastPatientID = -1;
                    for(PatientChart pc: hcInterface.patientList){
                        if(pc.getPatient_id() > lastPatientID){
                            lastPatientID = pc.getPatient_id();
                        }
                    }
                    lastPatientID++;
                    PatientChart newPatient = new PatientChart(staffInterface.apptNameField.getText(), "", "", 0, "", null, lastPatientID);
                    hcInterface.db.saveSingleChart(newPatient, lastPatientID);
                    
                }
                if(staffInterface.apptDocCombo.getSelectionModel().getSelectedIndex() < 0 || staffInterface.apptDateCombo.getSelectionModel().getSelectedIndex() < 0 || staffInterface.apptTimeList.getSelectionModel().getSelectedIndex() < 0){
                    hcInterface.popupConfirm("Please select a doctor and/or time");
                    return;
                } 
                for(User u: hcInterface.db.getUsers()){
                    if(u.getName().equals(staffInterface.apptDocCombo.getValue())){
                        ArrayList<Day> tempDay = hcInterface.db.getSingleAvailability(u.getId());
                       for(Day day: tempDay){
                           if(day.getDate().equals(staffInterface.apptDateCombo.getValue())){
                               hcInterface.patientList = hcInterface.db.getCharts();
                               int avail[] = day.getAvailabilityTimes();
                               int tempPatientID = hcInterface.patientList.get(hcInterface.patientList.size() - 1).getPatient_id() + 1;
                                
                               
                               for(PatientChart pc: hcInterface.db.getCharts()){
                                   if(pc.getPatient_name().equals(staffInterface.apptNameField.getText())){
                                        tempPatientID = pc.getPatient_id();
                                        
                                        break;
                                   }
                               }
                                staffInterface.apptTimeList.getSelectionModel().getSelectedIndex();
                                 avail [staffInterface.apptTimeList.getSelectionModel().getSelectedIndex()] = tempPatientID ;
                                 day.setAvailabilityTimes(avail); 
                                
                                hcInterface.db.saveSingleAvailability(u.getId(), tempDay);
                                
                                hcInterface.popupConfirm("Added patient appointment.");
                                Thread.sleep(100);
                                staffInterface.setAppointmentScene();
                                hcInterface.userInterface.setScene(staffInterface.getAppointmentScene());
                                break;
                           }
                                                  
                            } 
                            break;
                         }
                     }
                 }catch(Exception exep){exep.printStackTrace();}
            }
            
        }else if(e.getSource() == hcInterface.inCheckIn){
            hcInterface.curPatient.setPatient_name(hcInterface.nameField.getText());
            
            DateTimeFormatter form = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            hcInterface.curPatient.setBirthday(hcInterface.birthdayField.getValue().format(form));
            
            hcInterface.curPatient.setAddress(hcInterface.addressField.getText());
                  
            String ssn = hcInterface.socialField.getText();
            
            hcInterface.curPatient.setSsn(Integer.parseInt(ssn));
            
            hcInterface.curPatient.setInsurance(hcInterface.insuranceField.getText());
            
            
            TreatmentRecord newRec = new TreatmentRecord();
            newRec.setAppointment_date(hcInterface.tm.getTodayDate());
            hcInterface.curPatient.setCurrent_visit(newRec);
            hcInterface.db.saveSingleChart(hcInterface.curPatient, hcInterface.curPatient.getPatient_id());
            
            hcInterface.patientQueue.addToQueue(-1 , hcInterface.curPatient.getPatient_id());
            hcInterface.setLandingPage();
            hcInterface.userInterface.setScene(hcInterface.landingPageScene);
            hcInterface.userInterface.show();
            hcInterface.popupConfirm("Checked In patient.");
        }
        else if(e.getSource() == hcInterface.changeAppt){
            staffInterface.setChangeAppointment();
            hcInterface.userInterface.setScene(staffInterface.getChangeAppointmentScene());
        }
        else if(e.getSource() == staffInterface.changeNameButton){
            int tempPatientID = -1; 
            for(PatientChart pc: hcInterface.patientList){
                if(pc.getPatient_name().equals(staffInterface.changeNameField.getText())){
                    tempPatientID = pc.getPatient_id();
                    
                }
            }
            boolean found = false;
            for(User u: hcInterface.userList){
                if(u.getPermissions()== 2){
                    for(Day d: hcInterface.db.getSingleAvailability(u.getId())){
                        int count = 0;
                        for(int i: d.getAvailabilityTimes()){
                            if(i == tempPatientID){
                                staffInterface.changeDocBox.getSelectionModel().select(u.getName());
                                staffInterface.changeDocBox.getSelectionModel().clearAndSelect(staffInterface.changeDocBox.getSelectionModel().getSelectedIndex());
                                staffInterface.curApptLabel.setText("Current Appointment: " + d.getDate() + " at " + staffInterface.times.get(count));
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
               hcInterface.popupConfirm("No appointment"); 
            }
        }
        else if(e.getSource() == staffInterface.changeCancel || e.getSource() == staffInterface.deleteGoBack || e.getSource() == staffInterface.apptCancel){
            hcInterface.cancelPrimed = true;
            hcInterface.logoutPrimed = false;
            hcInterface.popupYesNo("Are you sure you want to exit?");
            
        }
        else if(e.getSource() == staffInterface.changeDateCombo){
            staffInterface.changeTimeList.getItems().clear();
            int tempUserID = -1;
            for(User u: hcInterface.userList){
                if(u.getName().equals(staffInterface.changeDocBox.getValue())){
                    tempUserID = u.getId();
                    break;
                }
            }
            for(Day d: hcInterface.db.getSingleAvailability(tempUserID)){
                if(d.getDate().equals(staffInterface.changeDateCombo.getValue())){
                    int availTime[] = d.getAvailabilityTimes();
                    for(int i = 0; i < availTime.length ; i++){
                        String s = staffInterface.times.get(i);
                        if(availTime[i] != 0){
                            for(PatientChart pc: hcInterface.patientList){
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
                hcInterface.popupConfirm("Please select a time.");
            }else{
                int tempUserID = -1;
                for(User u: hcInterface.userList){
                    if(u.getName().equals(staffInterface.changeDocBox.getValue()))
                    {
                        tempUserID = u.getId();
                        break;
                    }
                }
                
               int tempPatientID = -1;
               for(PatientChart pc: hcInterface.patientList){
                   if(pc.getPatient_name().equals(staffInterface.changeNameField.getText()))
                   {
                       tempPatientID = pc.getPatient_id();
                       break;
                   }
               }
                
                ArrayList<Day> days = hcInterface.db.getSingleAvailability(tempUserID);
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
                            hcInterface.popupConfirm("Please select a different date/time");
                        }
                    }
                    
                    
                }
                
                if(setNew){
                    hcInterface.db.saveSingleAvailability(tempUserID, days);
                    hcInterface.popupConfirm("Changed Appointment");
                    staffInterface.setChangeAppointment();
                    hcInterface.userInterface.setScene(staffInterface.getChangeAppointmentScene());
                    hcInterface.userInterface.show();
                }
            }
            
        }else if(e.getSource() == hcInterface.deleteAppt){
            staffInterface.setDeleteAppointmentScene();
            hcInterface.userInterface.setScene(staffInterface.getDeleteAppointmentScene());
            hcInterface.userInterface.show();
        }
        else if(e.getSource() == staffInterface.deleteNameOK){
            int patID = -1;
            String docName = "";
            String apptDate = "";
            String apptTime = "";
            for(PatientChart pc: hcInterface.patientList){
                if(pc.getPatient_name().equals(staffInterface.deleteNameField.getText())){
                    patID = pc.getPatient_id();
                }
            }
            if(patID == -1){
                hcInterface.popupConfirm("No Patient Found");
            }else {
                for(User u: hcInterface.userList){
                    if(u.getPermissions() == 2){
                        for(Day d: hcInterface.db.getSingleAvailability(u.getId())){
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
                    hcInterface.popupConfirm("No Appointment Found");
            }
        }
        else if(e.getSource() == staffInterface.cancelAppointment){
            if(staffInterface.deleteNameField.getText().length() > 0){
                int patID = -1;
                for(PatientChart pc: hcInterface.patientList){
                    if(pc.getPatient_name().equals(staffInterface.deleteNameField.getText())){
                        patID = pc.getPatient_id();
                        break;
                    }
                }
                boolean found = false;
                for(User u: hcInterface.userList){
                    if(u.getPermissions() == 2){
                        ArrayList<Day> days = hcInterface.db.getSingleAvailability(u.getId());
                        for(Day d: days){
                            int availDates [] = d.getAvailabilityTimes();
                            for(int i = 0; i < availDates.length; i++){
                                if(availDates[i] == patID){
                                    found = true;
                                    availDates[i] = 0;
                                    d.setAvailabilityTimes(availDates);
                                    hcInterface.db.saveSingleAvailability(u.getId(), days);
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
                hcInterface.popupConfirm("Deleted Appointment");
                staffInterface.setDeleteAppointmentScene();
                hcInterface.userInterface.setScene(staffInterface.getDeleteAppointmentScene());
                hcInterface.userInterface.show();
            }else{
                hcInterface.popupConfirm("Please enter a name");
            }
        }else if(e.getSource() == hcInterface.paymentInfo){
            if(hcInterface.curUser.getPermissions() != 0){
                hcInterface.popupConfirm("Access Denied");
                return;
            }
            
            staffInterface.setPaymentScene();
            double randomAmount = (double)(Math.random() * 200 + 50);
            hcInterface.window2 = new Stage();
            String str = String.format("$ %.2f", randomAmount);
            staffInterface.amountOweLabel.setText(str);
            hcInterface.window2.setScene(staffInterface.getPaymentScene());
            hcInterface.window2.show();
        }
        else if(e.getSource() == staffInterface.creditButton || e.getSource() == staffInterface.debitButton || e.getSource() == staffInterface.cashButton){
            String paymentType = "";
            float amount = Float.parseFloat(staffInterface.amountOweLabel.getText().replaceAll("[^0-9.]", ""));
            int referenceNumber = (int)(Math.random() * 89999999 + 10000000);
            int pin = -1;
            if(e.getSource() == staffInterface.cashButton){
                 paymentType = "Cash";
                 hcInterface.popupConfirm("Payment Approved");
            }else if(e.getSource() == staffInterface.debitButton){
                paymentType = "Debit";
                pin = (int)(Math.random() * 8999 + 1000);
                
            }else if(e.getSource() == staffInterface.creditButton){
                paymentType = "Credit";
            }
            if( (int)(Math.random()*2 + 1) % 2 != 0 && e.getSource() != staffInterface.cashButton){
                hcInterface.popupConfirm("Payment Declined");
            }else {
                PaymentInformation pi = new PaymentInformation(hcInterface.tm.getTodayDate(), amount, paymentType, referenceNumber, pin);
                hcInterface.curPatient.setPatient_payment(pi);
                hcInterface.db.saveSingleChart(hcInterface.curPatient, hcInterface.curPatient.getPatient_id());
                hcInterface.popupConfirm("Payment Accepted");
                staffInterface.printButton.setVisible(true);
            }
            
        }else if(e.getSource() == staffInterface.printButton){
            hcInterface.popupConfirm("Printing Receipt");
        }else if(e.getSource() == staffInterface.exitButton){
            hcInterface.window2.close();
        }
        else if(e.getSource() == hcInterface.saveChanges ){
            hcInterface.patientQueue.removeFromQueue(hcInterface.curUser.getId(), new Integer(hcInterface.curPatient.getPatient_id()));
            if(hcInterface.curUser.getPermissions() == 1 || hcInterface.curUser.getPermissions() == 2){
                try{
                    for(User u: hcInterface.db.getUsers()){
                        if(u.getName().equals(hcInterface.nurseDocBox.getValue())){
                            
                            ArrayList<TreatmentRecord> treatRec = hcInterface.curPatient.getTreatment_record_arr();
                            TreatmentRecord curTreatment =  treatRec.remove(treatRec.size() -1);
                            
                            
                                
                            curTreatment.setAppointment_date(hcInterface.tm.getTodayDate());
                            curTreatment.setWeight(Double.parseDouble(hcInterface.weightField.getText()));
                            curTreatment.setHeight(Double.parseDouble(hcInterface.heightField.getText()));
                            curTreatment.setBlood_pressure(hcInterface.bpField.getText());
                            curTreatment.setReason_for_visit(hcInterface.reasonVisitField.getText());
                            curTreatment.setTreatment(hcInterface.treatmentField.getText());
                            curTreatment.setPrescription(hcInterface.prescriptionField.getText());
                            curTreatment.setVisited_doctor(u.getName());
                            hcInterface.curPatient.setCurrent_visit(curTreatment);
                            hcInterface.curPatient.setTreatment_record_arr(treatRec);
                            
                            if(hcInterface.curUser.getPermissions() == 1)
                                hcInterface.patientQueue.addToQueue(u.getId(), hcInterface.curPatient.getPatient_id());
                            
                            hcInterface.patientQueue.removeFromQueue(hcInterface.curUser.getId(), hcInterface.curPatient.getPatient_id() );
                            hcInterface.db.saveSingleChart(hcInterface.curPatient, hcInterface.curPatient.getPatient_id());
                            hcInterface.popupConfirm("Saved changes");
                            hcInterface.setChartLandingPage();
                            hcInterface.userInterface.setScene(hcInterface.chartLandScene);
                            hcInterface.userInterface.show();
                            hcInterface.userInterface.setTitle(hcInterface.curUser.getName());
                            
                            break;
                        }
                    }
                }catch(Exception exep){exep.printStackTrace();}
            }
        }
        else if(e.getSource() == hcInterface.universalLogout || e.getSource() == hcInterface.staffInterface.apptCancel || e.getSource() == hcInterface.staffInterface.inCancel || e.getSource() == hcInterface.goBack){
            hcInterface.logoutPrimed = true;
            String message = "logout";
            if(e.getSource() == hcInterface.staffInterface.apptCancel || e.getSource() == hcInterface.staffInterface.inCancel || e.getSource() == hcInterface.goBack){
                message = "go back";
                hcInterface.logoutPrimed = false;
                hcInterface.cancelPrimed = true;
            }
            hcInterface.popupYesNo("Are you sure you want to " + message +  " ?" );
            
        }
        else if(e.getSource() == hcInterface.popupYes || e.getSource() == hcInterface.popupNo || e.getSource() == hcInterface.popupOK){
            if(e.getSource() == hcInterface.popupYes){
                hcInterface.popup.close();
                if(hcInterface.logoutPrimed){
                    hcInterface.runLoginInterface();
                }else if(hcInterface.cancelPrimed){
                    if(hcInterface.curUser.getPermissions() == 0)
                        hcInterface.setLandingPage();
                    else if(hcInterface.curUser.getPermissions() <= 2){
                        hcInterface.setChartLandingPage();
                    }else if(hcInterface.curUser.getPermissions() == 3){
                        hcInterface.ceoInterfae.setCEOMenuScene();
                        hcInterface.userInterface.setScene(hcInterface.ceoInterfae.getCEOScene());
                    }
                }
                hcInterface.logoutPrimed = false;
                hcInterface.cancelPrimed = false;
            }           
            else{
                hcInterface.popup.close();
            }
        }
    }
    
}
