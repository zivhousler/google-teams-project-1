package services;

import controllers.AuthController;
import databases.UserRepo;
import utils.RandomData;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private static AuthService instance;
    private Map<String, String> usersTokens; // Map<email, token>

    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    private AuthService() {
        usersTokens = new HashMap<>();
    }

    public String generateToken() {
        String token = null;
        boolean flag = false;
        while (!flag) {
            token = RandomData.generateRandomToken();
            if (!usersTokens.values().contains(token)) {
                flag = true;
            }
        }
        return token;
    }


    public Boolean validateUserLogin(String email, String password) {
        UserRepo udb = UserRepo.getInstance();
        // check if there is a user with the same email and password in the db!
        Map<String, String> usersMap = udb.getAllUsers();
//          return udb.filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().get();
        return true;
    }

    public String giveTokenToUser(String email) {
        if (usersTokens.containsKey(email)) return usersTokens.get(email);
        String token = generateToken();
        usersTokens.put(email, token);
        return token;
    }

    public Boolean validateUser(String token) {
        // make sure there is only one instance of this token in the map:
        Integer instances = usersTokens.values().stream().filter(userToken -> token.equals(userToken)).toArray().length;
        if (instances == 1) return true;
        return false;

    }
}
