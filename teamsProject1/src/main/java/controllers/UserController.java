package controllers;

// FADI
// call the userController (create a new object of userController -> UserController uc = new UserController
// uc.createNewUser // uc.createAccount (email, password, name)
// userController -> validates the given information -> check if name is good, check if email is valid, check if pw is valid


import java.util.UUID;
import java.util.regex.Pattern;

public class UserController {

    public String createNewAccount(String name, String email, String password) {
        if (nameValidator(name) & emailValidator(email) & passwordValidator(password)) {
            return UUID.randomUUID().toString();
        }
        return "cannot create user !! ";
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
