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
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arthr
 */
public class Database {

    private ArrayList<User> data;

    public Database() {
        this.data = new ArrayList();
    }

    private void parseDataFromJSON() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("./dummyData.JSON")) {
            // Convert JSON File to Java Object
            data = gson.fromJson(reader, new TypeToken<ArrayList<User>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> initDatabase() throws Exception {
        this.parseDataFromJSON();
        return data;
    }

    public int saveData(ArrayList users) {
        int checker = 0;
        Gson gson = new Gson();
        String json = gson.toJson(users);
        try (PrintWriter out = new PrintWriter("./dummyData.JSON")) {
            out.println(json);
            checker = 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checker;
    }

//    public int saveNewPatientAppointment() {
//        int checker = 0;
//        return checker;
//    }
//
//    public int deletePatientAppointment() {
//        int checker = 0;
//
//        return checker;
//    }
//
//    public int savePaymentInformation() {
//        int checker = 0;
//
//        return checker;
//    }
//
//    public int deletePaymentInformation() {
//        int checker = 0;
//
//        return checker;
//    }
}
