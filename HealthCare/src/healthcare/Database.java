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
     * saveUsers
     *
     * @param users An arraylist of users
     * @return a boolean to tell you if it saved properly
     */
    public int saveUsers(ArrayList users) {
        int checker = 0;
        Gson gson = new Gson();
        String json = gson.toJson(users);
        try (PrintWriter out = new PrintWriter("./DB/users.json")) {
            out.println(json);
            checker = 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checker;
    }

    /**
     * getSingleAvailability
     *
     * @param userId The ID of the Doctor
     * @return The availability times of the doctor.
     */
    public ArrayList<Day> getSingleAvailability(int userId) {
        this.parseDataFromJSON();
        ArrayList<Day> singleTime = null;
        for (Availability availabilityTime : availabilityTimes) {
            if (userId == availabilityTime.getUserId()) {
                singleTime = availabilityTime.getAvailabilityDates();
            }
        }
        return singleTime;
    }

    /**
     * saveSingleAvailability
     *
     * @param doctorId The Doctor's ID
     * @param days The Days arraylist for their availability
     * @return a boolean to tell you if it saved properly
     */
    public int saveSingleAvailability(int doctorId, ArrayList<Day> days) {
        int checker = 0;
        ArrayList<Day> temp = getSingleAvailability(doctorId);
        Availability availability = new Availability();
        if (temp == null) {
            availability.setUserId(doctorId);
            availability.setAvailabilityDates(days);
            availabilityTimes.add(availability);
            saveAvailabilityTimes(availabilityTimes);
        } else {
            int i = 0;
            for (Availability availabilityIter : availabilityTimes) {
                if (availabilityIter.getUserId() == doctorId) {
                    availabilityTimes.remove(i);
                    availabilityTimes.add(availability);
                    break;
                }
                i++;
            }
        }
        return checker;
    }

    /**
     * saveUsers
     *
     * @param availabilityTimes An arraylist of availabilityTimes
     * @return a boolean to tell you if it saved properly
     */
    public int saveAvailabilityTimes(ArrayList availabilityTimes) {
        int checker = 0;
        Gson gson = new Gson();
        String json = gson.toJson(availabilityTimes);
        try (PrintWriter out = new PrintWriter("./DB/availabilityTimes.json")) {
            out.println(json);
            checker = 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checker;
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
     * @param patientID The ID of the patient
     * @return A single chart
     */
    public PatientChart getSingleChart(int patientID) {
        this.parseDataFromJSON();
        PatientChart singleChart = null;
        for (PatientChart chart : charts) {
            if (patientID == chart.getPatient_id()) {
                singleChart = chart;
            }
        }
        return singleChart;
    }

    /**
     * saveUsers
     *
     * @param charts An arraylist of charts
     * @return a boolean to tell you if it saved properly
     */
    public int saveCharts(ArrayList charts) {
        int checker = 0;
        Gson gson = new Gson();
        String json = gson.toJson(charts);
        try (PrintWriter out = new PrintWriter("./DB/charts.json")) {
            out.println(json);
            checker = 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checker;
    }

    /**
     * saveSingleChart
     *
     * @param chart The new chart
     * @param patientID The patientID for the chart
     * @return a boolean to tell you if it saved properly
     */
    public int saveSingleChart(PatientChart chart, int patientID) {
        int checker = 0;
        PatientChart temp = getSingleChart(patientID);
        if (temp == null) {
            charts.add(chart);
            saveCharts(charts);
        } else {
            int i = 0;
            for (PatientChart chartIter : charts) {
                if (chartIter.getPatient_id() == chart.getPatient_id()) {
                    charts.remove(i);
                    charts.add(chart);
                    break;
                }
                i++;
            }
        }
        return checker;
    }

}
