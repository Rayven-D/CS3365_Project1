/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.util.ArrayList;
/**
 *
 * @author fract
 */
public class PatientChart {
    
    private String patient_name;
    private String birthday;
    private String address;
    private int ssn;
    private String insurance;
    private PaymentInformation patient_payment;
    private int patient_id;
    private int doctor_id;
    
    private ArrayList treatment_record_arr;
    private TreatmentRecord previous_visit;
    private TreatmentRecord current_visit;

    public PatientChart() {
    }
    
    // Constructor for adding new patient into system
    public PatientChart(String patient_name, String birthday, String address, int ssn, String insurance, PaymentInformation patient_payment, int patient_id) {
        this.patient_name = patient_name;
        this.birthday = birthday;
        this.address = address;
        this.ssn = ssn;
        this.insurance = insurance;
        this.patient_payment = patient_payment;
        this.patient_id = patient_id;
        this.previous_visit = null;
        this.current_visit = new TreatmentRecord();
        
        this.treatment_record_arr.add(this.current_visit);
        this.treatment_record_arr.trimToSize();
    }

    // Constructor for existing patient (has had previous visits) ----- Should run on start-up to add existing patients back into system
    public PatientChart(String patient_name, String birthday, String address, int ssn, String insurance, PaymentInformation patient_payment, int patient_id, ArrayList treatment_record) {
        this.patient_name = patient_name;
        this.birthday = birthday;
        this.address = address;
        this.ssn = ssn;
        this.insurance = insurance;
        this.patient_payment = patient_payment;
        this.patient_id = patient_id;
        
        this.treatment_record_arr = treatment_record;
        this.treatment_record_arr.trimToSize();
        int temp = this.treatment_record_arr.size();
        this.previous_visit = (TreatmentRecord) this.treatment_record_arr.get(temp);
        
        this.current_visit = new TreatmentRecord(this.previous_visit); //To show blank treatment record for current visit and to allow nurse/doctor to "update" previous values (height/weight/etc)
        this.treatment_record_arr.add(this.current_visit); //Adds current visit to ArrayList in order to update Treatment_Record JSON from just the ArrayList.
    }                                                      //NOTE: Current_visit in ArrayList will be replaced with updated version of this.current_visit once any values are changed.

    public int getDoctorID(){
        return this.doctor_id;
    }
    
    public void setDoctorID(int docID){
        this.doctor_id = docID;
    }
    
    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public PaymentInformation getPatient_payment() {
        return patient_payment;
    }

    public void setPatient_payment(PaymentInformation patient_payment) {
        this.patient_payment = patient_payment;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public ArrayList getTreatment_record_arr() {
        return treatment_record_arr;
    }

    public void setTreatment_record_arr(ArrayList treatment_record_arr) {
        this.treatment_record_arr = treatment_record_arr;
    }

    public TreatmentRecord getCurrent_visit() {
        return current_visit;
    }
    public TreatmentRecord getPreviousVisit(){
        return this.previous_visit;
    }
    public void setCurrent_visit(TreatmentRecord current_visit) {
        this.current_visit = current_visit;
        
        int temp = this.treatment_record_arr.size(); //Updates the current_visit in the arraylist with the version that has updated values. 
        this.treatment_record_arr.set(temp, current_visit);
    }
    
    
    
    
}
