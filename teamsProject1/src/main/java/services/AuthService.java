package services;

import controllers.AuthController;
import databases.UserRepo;
import org.json.simple.parser.ParseException;
import utils.RandomData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

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


    public Boolean validateUserLogin(String email, String password) throws IOException, ParseException {
        UserRepo ur = UserRepo.getInstance();
        return ur.verifyUserInfo(email, password);
    }

    public String giveTokenToUser(String email) {
        if (usersTokens.containsKey(email)) return usersTokens.get(email);
        String token = generateToken();
        usersTokens.put(email, token);
        return token;
    }

    public String validateUser(String token) {
        // make sure there is only one instance of this token in the map:
        Integer instances = usersTokens.values().stream().filter(userToken -> token.equals(userToken)).toArray().length;
        if (instances == 1) return Objects.requireNonNull(getEmailByToken(token));
        return null;
    }

<<<<<<< HEAD
    public String getEmailByToken(String token){
        String email = null;
        Iterator<Map.Entry<String,String>> iterator = usersTokens.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            if(entry.getValue().equals(token)) {
                email = entry.getKey();
            }
        }
        return email;
    }
=======
    public boolean updateEmailForToken(String token, String newEmail){
        if(usersTokens.values().stream().filter(userToken -> token.equals(userToken)).toArray().length <= 0)
            throw new IllegalArgumentException("Such token doesn't exist");
        String oldEmail = null;
        Iterator<Map.Entry<String,String>> iterator = usersTokens.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,String> entry = iterator.next();
            if(entry.getValue() == token){
                oldEmail = entry.getKey();
            }
        }
        if(oldEmail == null) throw new IllegalArgumentException("Couldn't find such an email");
        usersTokens.remove(oldEmail);
        usersTokens.put(newEmail, token);
        return true;
    }

>>>>>>> 550a4da5171370f1f17aceb2a45c6cfb2008753c
}
