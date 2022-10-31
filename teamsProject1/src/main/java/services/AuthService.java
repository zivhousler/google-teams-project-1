package services;

import controllers.AuthController;
import databases.UserRepo;
import utils.RandomData;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private static AuthService instance;

    public static AuthService getInstance() {
        return instance == null ? new AuthService() : instance;
    }

    private AuthService() {
    }

     public static Map<String,String> usersTokens = new HashMap<>();

//     public String authUser(String email){
//          String token;
//          boolean flag = false;
//          while(!flag){
//              token = String.valueOf(RandomData.generateRandomNumber(100000000,999999999));
//              if(!usersTokens.contains(token)) {
//                  usersTokens.put(email, token);
//                  flag = true;
//          }
//          return token;
//     }

     public String validateUserLogin(String email, String password){
          UserRepo udb = UserRepo.getInstance();
          Map<String,String> usersMap = udb.getAllUsers();
//          return udb.filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().get();
         return "abc";
     }
}
