package client;

import controllers.Actions;
import controllers.AuthController;
import controllers.UserController;

public class Main {
    public static void main(String[] args) {
        // FADI
        // call the userController (create a new object of userController -> UserController uc = new UserController
        // uc.createNewUser // uc.createAccount (email, password, name)
        // userController -> validates the given information -> check if name is good, check if email is valid, check if pw is valid

        // SARAY /////
        // create class userService -> (name, email, password)
        // create a function that checks if such a user already exists:
        // download all the db from the "users-file" to a MAP<id(string),user(user)>
        // user.foreach(user - check if this email is already used)

        // enum Actions {EDIT_PASSWORD, EDIT_NAME, DELETE_ACCOUNT} -> TODO: add a function for each type of enum

        // // -> Client//
        String email = "ziv@gmail.com";
        String password = "123456789";
        String name = "Hello World";

        AuthController ac = AuthController.getInstance();
        System.out.println(ac.loginUser(email, password));

        UserController uc = UserController.getInstance();
        System.out.println(uc.createAccount(email, password, name));

        String token = ac.loginUser(email, password);
        System.out.println(token);

        uc.manipulateUserData(token, Actions.EDIT_PW, password); //
        uc.manipulateUserData(token, Actions.EDIT_NAME, name);   // TODO: implement enum function
        uc.manipulateUserData(token, Actions.EDIT_EMAIL, email); //

        // // AuthController class has to receive the data from the client, validate it in order to check
        // // if it answers the regex from email and password.
        // // -> AuthController
        // public String loginUser(String email, String password){
        //      isValidData(email, password) -> true/false -> throw new exception if needed TODO: fadi's validation
        //      AuthService as = new AuthService();
        //      return as.validateUserLogin(email, password);
        // }
        // DONE!

        // // UserController class is responsible only for validating the information it receives from the client.
        // // -> UserController
        // public String createAccount(String email, String password, String name){
        //      isValidData(data) -> true/false -> throw new exception if the data is incorrect
        //      UserService us = new UserService();
        //      return requireNotNull(us.createAccount(email, password, name));
        //      if(us.createAccount(email, password, name)){
        //          AuthService as = new AuthService();
        //          return as.authUser(email);
        //      }
        // }
        // public Boolean isValidData(String email, String password, String name){
        //      // return if the data is validated
        // }
        // public Boolean editInfo(){
        //      // return ture or false if it worked
        // }

        // // UserService class has to communicate directly with our database in order to manipulate it.
        // // Among the functionality: ask for users from the db, request a register for new users in the db,
        // // update user's info and remove their account.
        // // -> UserService
        // public String createAccount(String email, String password, String name){
        //      UsersDatabase udb = new UserDatabase();
        //      Map<String,String> users = udb.getAllUsers();
        //
        //      //check if there's a user with this email already
        //      if(FindFromData.getUserFromDbByEmail(email)){
        //          return null;
        //      }
        //
        //      User newUser = User.createNewUser(email, password, name);
        //      if(udb.addUserToDb(user)){
        //          AuthService as = new AuthService();
        //          return as.validateUserLogin(email, password);
        //      }
        // }

        // // AuthService class has to take the verified data from the controller and check if there is any reason
        // // to create a new user out of it and send it to the DB to store it.
        // // -> AuthService
        // public static Map<String,String> usersTokens = new HashMap<>(); -> needs to draw data from the database
        // public String authUser(String email){
        //      String token;
        //      boolean flag = false;
        //      while(!flag){
        //          token = String.valueOf(RandomData.generateRandomNumber(100000000,999999999));
        //          if(!usersTokens.contains(token)) {
        //              usersTokens.put(email, token);
        //              flag = true;
        //      }
        //      return token;
        // }
        // public String validateUserLogin(String email, String password){
        //      UserDatabase udb = new UserDatabase();
        //      Map<String,String> usersMap = udb.getAllUsers();
        //      return udb.filter(user -> user.getEmail().equals(email) && user.getPassword().equals(password)).findFirst().get();
        // }

        // // Repo (UsersDatabase) class is responsible for fetching data from the database and writing back to it.
        // // -> Repo
        // public Boolean addUserToDb(User user){
        //      // TODO: write the new user to a new file!
        // }
        // public Map<String,String> getAllUsers(){
        //      // TODO: get all the users from the db and return them in a map of <email:password>
        // }
    }
}

