package services;

public class UserSevice {

//    public void addUserToDB(String id, String email, String password) throws IllegalAccessException {
//        String filePath = ".\\my-app\\src\\dataBase\\" + id;  // address to file.
//        User user = new User(id, email, password); //creation of the user.
//        Gson gson = new Gson();  // creation of new gson
//        JsonReader reader = null; // if I want to read
//        try {
//            JsonReader rxeader = new JsonReader(new FileReader(filePath));
//        } catch (FileNotFoundException e) {
//            try {
//
//                PrintWriter writer = new PrintWriter(filePath, "UTF-8");
//                String userJson = gson.toJson(user);
//                writer.print(userJson);
//                writer.close();
//            } catch (IOException ex) {
//                throw new RuntimeException("file not exist, failed to create config file");
//            }
//        }
//    }

    public void function2(String id, String email, String password) throws IllegalAccessException {
        // delete user-> delete file (function get email)
    }

    public void function3(String id, String email, String password) throws IllegalAccessException {
        // update user-> update file (function get email)
    }

    public void function4(String id, String email, String password) throws IllegalAccessException {
        // reade user-> delete file (function get email)
    }

}
