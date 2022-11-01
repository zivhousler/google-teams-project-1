package controllers;

// FADI
// call the userController (create a new object of userController -> UserController uc = new UserController
// uc.createNewUser // uc.createAccount (email, password, name)
// userController -> validates the given information -> check if name is good, check if email is valid, check if pw is valid


import org.json.simple.parser.ParseException;
import services.AuthService;
import services.UserService;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserController {

    private static UserController instance;

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    private UserController() {

    }

    public Boolean createAccount(String email, String password, String name) throws IOException, ParseException {
        if (!isValidData(email, password, name))
            return false;

        UserService us = UserService.getInstance();
        return Objects.requireNonNull(us.createAccount(email, password, name));
    }

    public Boolean isValidData(String email, String password, String name) {
        if (!emailValidator(email)) {
            System.out.println("email is not valid !");
            return false;
        }

        if (!passwordValidator(password)) {
            System.out.println("password is not valid");
            return false;
        }

        if (!nameValidator(name)) {
            System.out.println("name is not valid");
            return false;
        }

        return true;
    }

    public Boolean manipulateUserData(String token, Actions type, String content) throws IOException, IllegalAccessException {
//        if (!isValidData(content)) return null; // TODO: -> Fadi this is you. You need to make it method generic to check whatever content is send to this function.
        AuthService as = AuthService.getInstance();
        String userEmailByToken = as.validateUser(token);
        if (userEmailByToken == null) return false;

        UserService us = UserService.getInstance();
        if (type == Actions.DELETE_USER) return us.deleteUserAccount(content);

        //return us.updateAccountInformaion(type, content, userEmailByToken);

        Boolean response = us.updateAccountInformaion(type, content, userEmailByToken);
        if (response && type == Actions.EDIT_EMAIL) {
            return as.updateEmailForToken(token, content);
        }
        return response;

    }

    private boolean emailValidator(String emailAddress) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@" + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
        return (emailAddress instanceof String) & (Pattern.compile(regexPattern).matcher(emailAddress).matches());
    }

    private boolean nameValidator(String name) {
        return (name instanceof String) & (name.length() > 3) & (name.chars().allMatch(Character::isLetter));
    }

    private boolean passwordValidator(String password) {
        return password.length() > 8;
    }
}

