package utils;

import entities.User;

import java.util.Map;
import java.util.Objects;

public class FindFromData {

    public static User getUserFromDbByEmail(String email, Map<String, User> allUsersMap) {
        try {
            return Objects.requireNonNull(allUsersMap.get(email));
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("This email doesn't exist in the DB");
            // throw new UnsupportedOperationException("getUserFromDbByEmail function is yet to be supported");
        }
    }

    public static Boolean isEmailAlreadyInUse(String email, Map<String, User> allUsersMap) {
        return allUsersMap.containsKey((email));
    }

    public static Boolean isInitializedMap(Map<String, User> allUsersMap) {
        return allUsersMap == null;
    }

}
