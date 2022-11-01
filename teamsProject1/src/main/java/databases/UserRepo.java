package databases;

import com.google.gson.Gson;
import entities.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    private static UserRepo instance;

    public static UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
    }

    private UserRepo() {
    }

    public Boolean addUserToDb(User user) {
        // TODO: write the new user to a new file!
        return true;
    }

    public Map<String, String> getAllUsers() {
        // TODO: get all the users from the db and return them in a map of <email:password>
        return new HashMap<String, String>();
    }

    public Boolean editUserInfo(String info) {
        //.......
        return true;
    }


    public void writeJsonToFile(String filename, HashMap<String, String> content) { //write to json file
        Gson gson = new Gson();
        writeToFile(filename, gson.toJson(content));
    }

    public void writeToFile(String filename, String content) { //
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addUserToData(String email, String password, String name) throws IllegalAccessException {
        HashMap<String, String> mapToJson = new HashMap<String, String>();
        try {

            User user = User.createUser(email, password, name); //creation of the user

            mapToJson.put("email", email);
            mapToJson.put("password", password);
            mapToJson.put("name", name);

            String filename = ".\\teamsProject1\\src\\main\\java\\databases\\userDB\\" + email + ".json";

            writeJsonToFile(filename, mapToJson);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //TODO: reade data from file//
    //TODO: write user to file//
    //TODO: delete user from file//
    //TODO: update user from file//
    //TODO: checks if user exist by id or mail
    //TODO: add all user's to data and to map<String, User>//

    public void readUser(String filename, HashMap<String, String> content) { //write to json file
        Gson gson = new Gson();
        writeToFile(filename, gson.toJson(content));
    }

    public static Map<String, User> getAllUsersFromDatabase() throws IOException, ParseException {
        Map<String, User> usersMap = new HashMap<>(); // <email, User>
        Path dir = Paths.get(".\\teamsProject1\\src\\main\\java\\databases\\userDB\\");
        JSONParser jsonParser = new JSONParser();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.json")) {
            for (Path p : stream) {

                FileReader reader = new FileReader(".\\teamsProject1\\src\\main\\java\\databases\\userDB\\" + p.getFileName().toFile());
                Object obj = jsonParser.parse(reader);

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(obj.toString());

                User user = User.createUser(json.get("name").toString(), json.get("email").toString(), json.get("password").toString());
                usersMap.put(user.getEmail(), user);
                reader.close();
            }
        }

        return usersMap;
    }

    public boolean updateUser(User user) throws IOException, IllegalAccessException {
        return addUserToData(user.getEmail(), user.getPassword(), user.getName());
    }


    public void deleteUser(String email) { //write to json file
        try {

            File f = new File(".\\teamsProject1\\src\\main\\java\\databases\\userDB\\" + email + ".json");           //file to be delete
            if (f.delete()) {
                System.out.println(f.getName() + " deleted");   //getting and printing the file name
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
