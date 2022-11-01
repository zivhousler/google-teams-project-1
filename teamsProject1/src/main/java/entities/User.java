package entities;

import utils.RandomData;

import java.time.LocalDate;

public class User {

    private String name;
    private final String email;
    private String password;
    private final LocalDate creationDate;

    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }

    private User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.creationDate = LocalDate.now();
    }

    public static User createUser(String name, String email, String password) {
        return new User(name, email, password);
    }

    public static User createRandomUser() {
        return new User(RandomData.generateRandomName(), RandomData.generateRandomName(), String.valueOf(RandomData.generateRandomNumber(10000000, 99999999)));
    }

    public String getName() {
        return name;
    }
    public String getPassword(){ return password;}
    public String getEmail() {
        return email;
    }
}
