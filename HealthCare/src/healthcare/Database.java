package healthcare;

import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import com.google.gson.*;
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
    private ArrayList<PatientChart> charts;
    private ArrayList<Availability> availabilityTimes;

    /**
     * Constructor
     */
    public Database() {
        this.data = new ArrayList();
    }

    private void parseDataFromJSON() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("./DB/users.json")) {
            data = gson.fromJson(reader, new TypeToken<ArrayList<BaseResponse>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("Something wrong with getting Users JSON file.");
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("./DB/charts.json")) {
            charts = gson.fromJson(reader, new TypeToken<ArrayList<PatientChart>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("Something wrong with getting Charts JSON file.");
            e.printStackTrace();
        }

        try (Reader reader = new FileReader("./DB/availabilityTimes.json")) {
            availabilityTimes = gson.fromJson(reader, new TypeToken<ArrayList<Availability>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("Something wrong with getting Availability Times JSON file.");
            e.printStackTrace();
        }
    }

    /**
     * getUsers
     *
     * @return An ArrayList containing all the user data.
     * @throws Exception To catch any file issues
     */
    public ArrayList<User> getUsers() throws Exception {
        this.parseDataFromJSON();
        return data;
    }

    /**
     * getCharts
     *
     * @return An ArrayList containing all the charts data.
     * @throws Exception To catch any file issues
     */
    public ArrayList<PatientChart> getCharts() throws Exception {
        this.parseDataFromJSON();
        return charts;
    }
    /**
     * getSingleChart
     *
     * @return An ArrayList containing all the charts data.
     * @throws Exception To catch any file issues
     */
    public PatientChart getSingleChart(int userId) {
        this.parseDataFromJSON();
        PatientChart singleChart = new PatientChart();
        for (PatientChart chart : charts) {
            if(userId == chart.getPatient_id()) {
                singleChart = chart;
            }
        }
        return singleChart;
    }

    /**
     * getSingleAvailability
     *
     * @return An ArrayList containing all the charts data.
     * @throws Exception To catch any file issues
     */
    public ArrayList<Day> getSingleAvailability(int userId) {
        this.parseDataFromJSON();
        ArrayList<Day> singleTime = null;
        for (Availability availabilityTime : availabilityTimes) {
            if(userId == availabilityTime.getUserId()) {
                singleTime = availabilityTime.getAvailabilityDates();
            }
        }
        return singleTime;
    }

    /**
     * saveData will save the data to a JSON file.
     *
     * @param users An ArrayList of users.
     * @return Will return 1 or 0 depending if the save was successful.
     */
    public int saveData(ArrayList users) {
        int checker = 0;
        Gson gson = new Gson();
        String json = gson.toJson(users);
        try (PrintWriter out = new PrintWriter("./DB/users.JSON")) {
            out.println(json);
            checker = 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checker;
    }
}
