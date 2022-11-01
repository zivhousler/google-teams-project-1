package controllers;

import org.json.simple.parser.ParseException;
import services.AuthService;

import java.io.IOException;
import java.util.Objects;

public class AuthController {

    private static AuthController instance;

    public static AuthController getInstance() {
        if (instance == null) {
            instance = new AuthController();
        }
        return instance;
    }

    private AuthController() {

    }

    public String loginUser(String email, String password) throws IOException, ParseException {
//          isValidData(email, password) -> true/false -> throw new exception if needed TODO: fadi's validation
        AuthService as = AuthService.getInstance();
        boolean isEqual = as.validateUserLogin(email, password);
        if (!isEqual) {
            System.out.println("AuthController-loginUser: Passwords are not equal !");
            throw new IllegalArgumentException("The given password didn't match to the database");
        }
        return Objects.requireNonNull(as.giveTokenToUser(email));
    }
}
