package databases;

import entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    private static UserRepo repo;

    public static UserRepo getInstance() {
        return repo == null ? new UserRepo() : repo;
    }

    private UserRepo() {
    }

     public Boolean addUserToDb(User user){
          // TODO: write the new user to a new file!
         return true;
     }
     public Map<String,String> getAllUsers(){
          // TODO: get all the users from the db and return them in a map of <email:password>
         return new HashMap<String,String>();
     }
}
