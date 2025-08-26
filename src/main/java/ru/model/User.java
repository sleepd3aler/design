package ru.model;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User first = new User("Alex", 2, new GregorianCalendar(1993, Calendar.MAY, 20));
        User second = new User("Alex", 2, new GregorianCalendar(1993, Calendar.MAY, 20));
        Map<User, Object> users = new HashMap<>();
        users.put(first, new Object());
        users.put(second, new Object());
        int firstHash = first.hashCode();
        int secondHash = second.hashCode();
        int length = 16;
        int firstIndex = (firstHash ^ (firstHash >> 16)) & (length - 1);
        int secondIndex = (secondHash ^ (secondHash >> 16)) & (length - 1);
        users.entrySet().forEach(System.out::println);

    }
}
