package controllers;

import services.AuthService;

public class AuthController {

    private static AuthController instance;

    public static AuthController getInstance(){
        return instance == null ? new AuthController() : instance;
    }

    private AuthController(){};

     public String loginUser(String email, String password){
//          isValidData(email, password) -> true/false -> throw new exception if needed TODO: fadi's validation
          AuthService as = AuthService.getInstance();
          return as.validateUserLogin(email, password);
     }
}
