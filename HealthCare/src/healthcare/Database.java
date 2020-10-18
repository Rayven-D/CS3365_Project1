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

/**
 *
 * @author Arthr
 */
public class Database {

    private ArrayList data = new ArrayList();

    private String loadData(String filename) {
        String json = "";
        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private User parseDataFromJSON() throws Exception {
        User user = new User();
        Object obj = new JSONParser().parse(new FileReader("./dummyData.JSON"));
        JSONObject jo = (JSONObject) obj;
        JSONArray ja = (JSONArray) jo.get("users");
        Iterator itr2 = ja.iterator();
        Iterator<Map.Entry> itr1;
        while (itr2.hasNext()) {
            itr1 = ((Map) itr2.next()).entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }
        }
        return user;
    }

    public ArrayList<User> initDatabase() throws Exception {
        ArrayList users = new ArrayList<>();
        this.parseDataFromJSON();
        return users;
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
