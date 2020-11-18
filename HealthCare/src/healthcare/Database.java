package healthcare;

import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
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

    private ArrayList<User_Old> data;

    /**
     * Constructor
     */
    public Database() {
        this.data = new ArrayList();
    }

    private void parseDataFromJSON() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("./dummyData.JSON")) {
            data = gson.fromJson(reader, new TypeToken<ArrayList<User_Old>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("Something wrong with getting Users JSON file.");
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("./DB/dailyreports.json")) {
            reports = gson.fromJson(reader, new TypeToken<ArrayList<Report>>() {
            }.getType());
        } catch (IOException e) {
            System.out.println("Something wrong with getting Daily Reports JSON file.");
            e.printStackTrace();
        }
    }

    /**
     * initDatabases
     *
     * @return An ArrayList containing all the user data.
     * @throws Exception To catch any file issues
     */
    public ArrayList<User_Old> initDatabase() throws Exception {
        this.parseDataFromJSON();
        return data;
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
