package ru.tdd.userService;

public class UserService {
    String getUsernameById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        return "User: " + id;
    }
}
