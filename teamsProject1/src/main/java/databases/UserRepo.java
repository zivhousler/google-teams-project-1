package databases;

import com.google.gson.Gson;
import entities.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserRepo {

    private static UserRepo instance;

    public static UserRepo getInstance() {
        if (instance == null) {
            instance = new UserRepo();
        }
        return instance;
    }

    public void writeJsonToFile(String filename, HashMap<String, String> content) {
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

    public boolean addUserToData(String email, String password, String name) {
        HashMap<String, String> mapToJson = new HashMap<String, String>();
        try {

            User user = User.createUser(email, password, name); //creation of the user

            mapToJson.put("email", email);
            mapToJson.put("password", password);
            mapToJson.put("name", name);

            String filename = ".\\teamsProject1\\src\\main\\java\\databases\\userDB\\" + email + ".json";

            writeJsonToFile(filename, mapToJson);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean checkIfUserExistByEmail(String userEmail) throws IOException, ParseException {
        Map<String, User> allAsers = getAllUsersFromDatabase();
        for (String email : allAsers.keySet()) {
            if (email.equals(userEmail))
                return true;
        }
        return false;
    }

    public boolean verifyUserInfo(String email, String password) throws IOException, ParseException {
        Map<String, User> allAsers = getAllUsersFromDatabase();
        User user = allAsers.get(email);
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    public void readUser(String filename, HashMap<String, String> content) { //write to json file
        Gson gson = new Gson();
        writeToFile(filename, gson.toJson(content));
    }

    public Map<String, User> getAllUsersFromDatabase() throws IOException, ParseException {
        Map<String, User> usersMap = new HashMap<>(); // <email, User>
        Path dir = Paths.get(".\\teamsProject1\\src\\main\\java\\databases\\userDB\\");
        JSONParser jsonParser = new JSONParser();
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.json");

        try {
            FileReader reader = null;
            for (Path p : stream) {

                reader = new FileReader(".\\teamsProject1\\src\\main\\java\\databases\\userDB\\" + p.getFileName().toFile());
                Object obj = jsonParser.parse(reader);

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(obj.toString());

                User user = User.createUser(json.get("name").toString(), json.get("email").toString(), json.get("password").toString());
                usersMap.put(user.getEmail(), user);
                reader.close();
            }

        } catch (Exception e) {
            return usersMap;
        }

        return usersMap;
    }

    public boolean updateUser(User user) {
        return addUserToData(user.getEmail(), user.getPassword(), user.getName());
    }

    public void deleteUser(String email) { //write to json file

        File f = new File(".\\teamsProject1\\src\\main\\java\\databases\\userDB\\" + email + ".json");           //file to be delete
        if (f.delete()) {
            System.out.println(f.getName() + " deleted");   //getting and printing the file name
        } else {
            System.out.println("cant delete user !");
        }
    }
}
