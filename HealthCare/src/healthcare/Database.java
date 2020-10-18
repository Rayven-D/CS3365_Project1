/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthcare;

import java.io.FileReader;
import java.util.ArrayList;
import java.nio.charset.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.google.gson.Gson;
/**
 *
 * @author Arthr
 */
public class Database {

    private ArrayList<User> data;

    public Database() {
        this.data = new ArrayList();
    }

    private void parseDataFromJSON() throws Exception {
        User user = new User();
        Object obj = new JSONParser().parse(new FileReader("./dummyData.JSON"));
        JSONObject jo = (JSONObject) obj;
        JSONArray ja = (JSONArray) jo.get("users");

        Iterator itr2 = ja.iterator();
        Iterator<Map.Entry> itr1;
        while (itr2.hasNext()) {
            user = new User();
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                if (pair.getKey().toString().equals("firstName")) {
                    user.setFirstName(pair.getValue().toString());
                }
                if (pair.getKey().equals("lastName")) {
                    user.setLastName(pair.getValue().toString());
                }
                if (pair.getKey().equals("userId")) {
                    user.setUserId((long) pair.getValue());
                }
                if (pair.getKey().equals("password")) {
                    user.setPassword(pair.getValue().toString());
                }
                if (pair.getKey().equals("permissions")) {
                    user.setPermissions((long) pair.getValue());
                }
                if (pair.getKey().equals("dob")) {
                    user.setDob(pair.getValue().toString());
                }
                if (pair.getKey().equals("address")) {
                    user.setAddress(pair.getValue().toString());
                }
                if (pair.getKey().equals("phonenumber")) {
                    user.setPhoneNumber((long) pair.getValue());
                }
                if (pair.getKey().equals("ssn")) {
                    user.setSSN((long) pair.getValue());
                }
                if (pair.getKey().equals("healthInsurance")) {
                    user.setHealthInsurance(pair.getValue().toString());
                }
                if (pair.getKey().equals("appointmentInformation")) {
                    JSONObject ai = (JSONObject) pair.getValue();
                    JSONArray appointInfo = (JSONArray) ai.get("appointmentInformation");
                    Iterator appointInfoItr2 = appointInfo.iterator();
                    Iterator<Map.Entry> appointInfoItr1;
                    appointInfoItr1 = ((Map) appointInfoItr2.next()).entrySet().iterator();
                    ArrayList<AppointmentInformation> appointments = new ArrayList();
                    while (appointInfoItr2.hasNext()) {
                        AppointmentInformation appointment = new AppointmentInformation();
                        while (appointInfoItr1.hasNext()) {
                            if (pair.getKey().equals("appointmentId")) {
                                appointment.setAppointmentId((long) pair.getValue());
                            }
                            if (pair.getKey().equals("doctorName")) {
                                appointment.setDoctorName(pair.getValue().toString());
                            }
                            if (pair.getKey().equals("date")) {
                                appointment.setDate(pair.getValue().toString());
                            }
                            if (pair.getKey().equals("time")) {
                                appointment.setTime(pair.getValue().toString());
                            }
                            if (pair.getKey().equals("active")) {
                                appointment.setActive((long)pair.getValue());
                            }
                            if (pair.getKey().equals("chart")) {
                            }
                        }
                    }
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }
                if (pair.getKey().equals("paymentInformation")) {
                    System.out.println(pair.getKey() + " : " + pair.getValue());
                }
            }
            data.add(user);
        }
    }
    
    private void parseDataFromJSON2() {
        
    }
    public ArrayList<User> initDatabase() throws Exception {
        this.parseDataFromJSON();
        return data;
    }

    public void saveData(ArrayList users) {

    }

    public int saveNewPatientAppointment() {
        int checker = 0;
        return checker;
    }

    public int deletePatientAppointment() {
        int checker = 0;

        return checker;
    }

    public int savePaymentInformation() {
        int checker = 0;

        return checker;
    }

    public int deletePaymentInformation() {
        int checker = 0;

        return checker;
    }
}
