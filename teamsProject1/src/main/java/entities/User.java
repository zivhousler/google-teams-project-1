package entities;

import utils.RandomData;

import java.time.LocalDate;

public class User {

    private final String name;
    private final String email;
    private String password;
    private final LocalDate creationDate;

    private User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.creationDate = LocalDate.now();
    }

    public User createUser(String name, String email, String password) {
        return new User(name, email, password);
    }

    public User createRandomUser() {
        return new User(RandomData.generateRandomName(), RandomData.generateRandomName(), String.valueOf(RandomData.generateRandomNumber(10000000, 99999999)));
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
