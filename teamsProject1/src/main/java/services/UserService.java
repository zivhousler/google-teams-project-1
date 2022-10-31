package services;

import controllers.Actions;
import databases.UserRepo;
import entities.User;

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
        users = new HashMap<>(); // TODO: -> need to draw all users from our files!!!
    }

    public Boolean createAccount(String email, String password, String name){
//        return User.createUser(name, email, password);
        return true; // TODO: -> Saray - create the account and return true if successful or false if not.
    }

    public Boolean deleteUserAccount(String email){
        // delete the file answering the relevant email name
        return true;
    }

    public Boolean updateAccountInformaion(Actions type, String content){
        UserRepo ur = UserRepo.getInstance();
        switch (type) {
            case EDIT_PW:
                break;
            case EDIT_NAME:
                break;
            case EDIT_EMAIL:
//                ur.editUserInfo();
                break;
            default:
                throw new IllegalArgumentException("Such action is not possible!");
        }
        return true; // TODO: -> Saray - edit the account and return true if successful or false if not.
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
