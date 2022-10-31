package controllers;

// FADI
// call the userController (create a new object of userController -> UserController uc = new UserController
// uc.createNewUser // uc.createAccount (email, password, name)
// userController -> validates the given information -> check if name is good, check if email is valid, check if pw is valid


import services.AuthService;
import services.UserService;

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

    public Boolean createAccount(String email, String password, String name) {
        if (!isValidData(email, password, name)) return null; // TODO: -> Fadi this is you.

        UserService us = UserService.getInstance();
        return Objects.requireNonNull(us.createAccount(email, password, name));

        //   AuthService as = AuthService.getInstance();
        //   return as.authUser(email); // TODO: -> do we need to return a token on account creation?
    }

    public Boolean isValidData(String email, String password, String name) {
        return true; // TODO: -> Fadi this is you.
    }

    public Boolean manipulateUserData(String token, Actions type, String content) {
//        if (!isValidData(content)) return null; // TODO: -> Fadi this is you. You need to make it method generic to check whatever content is send to this function.
        AuthService as = AuthService.getInstance();
        if (!as.validateUser(token)) return false;

        UserService us = UserService.getInstance();
        if (type == Actions.DELETE_USER) return us.deleteUserAccount(content);
        return us.updateAccountInformaion(type, content);
    }

//    public Boolean createNewAccount(String name, String email, String password) {
//        if (nameValidator(name) & emailValidator(email) & passwordValidator(password)) {
//            return UUID.randomUUID().toString();
//        }
//        return "cannot create user !! ";
//    }

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

