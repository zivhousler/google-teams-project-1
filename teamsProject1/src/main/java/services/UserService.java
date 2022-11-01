package services;

import controllers.Actions;
import databases.UserRepo;
import entities.User;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserService {

    private static UserService instance;
    private Map<String, User> users; // Map<email, token>


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserService() {
        UserRepo ur = UserRepo.getInstance();
        try {
            users = ur.getAllUsersFromDatabase();
        } catch (IOException e) {
            System.out.println("could not get all users !");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean createAccount(String email, String password, String name) throws IOException, ParseException {
        UserRepo ur = new UserRepo();
        if (ur.checkIfUserExistByEmail(email)) {
            System.out.println("user is already existed !");
            return false;
        }
        return ur.addUserToData(email, password, name);
    }

    public Boolean deleteUserAccount(String email) {
        // delete the file answering the relevant email name
        return true;
    }

    public Boolean updateAccountInformaion(Actions type, String content, String email) throws IllegalAccessException, IOException {
        UserRepo ur = UserRepo.getInstance();
        User user = users.get(email); // -> user.email, user.newName, user.newPw (current)
        switch (type) {
            case EDIT_PW:
                user.setPassword(content);
                break;
            case EDIT_NAME:
                user.setName(content);
                break;
            case EDIT_EMAIL:
                User newUser = User.createUser(user.getName(), content, user.getPassword());
                users.remove(user.getEmail()); // -> remove also from db (delete its file)
                users.put(content, newUser); // -> create a new file with the new email and user
                ur.deleteUser(user.getEmail());
                return ur.addUserToData(newUser.getEmail(), newUser.getPassword(), newUser.getName());
            default:
                throw new IllegalArgumentException("Such action is not possible!");
        }
        users.put(user.getEmail(), user);
        return ur.updateUser(user);
    }

//    public void addUserToDB(String id, String email, String password) throws IllegalAccessException {
//        String filePath = ".\\my-app\\src\\dataBase\\" + id;  // address to file.
//        User user = new User(id, email, password); //creation of the user.
//        Gson gson = new Gson();  // creation of new gson
//        JsonReader reader = null; // if I want to read
//        try {
//            JsonReader rxeader = new JsonReader(new FileReader(filePath));
//        } catch (FileNotFoundException e) {
//            try {
//
//                PrintWriter writer = new PrintWriter(filePath, "UTF-8");
//                String userJson = gson.toJson(user);
//                writer.print(userJson);
//                writer.close();
//            } catch (IOException ex) {
//                throw new RuntimeException("file not exist, failed to create config file");
//            }
//        }
//    }

    public void function2(String id, String email, String password) throws IllegalAccessException {
        // delete user-> delete file (function get email)
    }

    public void function3(String id, String email, String password) throws IllegalAccessException {
        // update user-> update file (function get email)
    }

    public void function4(String id, String email, String password) throws IllegalAccessException {
        // reade user-> delete file (function get email)
    }

}
