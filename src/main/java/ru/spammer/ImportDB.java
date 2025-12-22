package ru.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ru.spammer.model.User;

public class ImportDB {

    private final String config;
    private final String dump;

    public ImportDB(String config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(string -> {
                String[] parts = string.split(";", 2);
                validate(parts);
                users.add(
                        new User(
                                parts[0],
                                parts[1]
                        )
                );
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connection = initConnection(config);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO users (name, email) values (?, ?)")
        ) {
            for (User user : users) {
                preparedStatement.setString(1, user.name());
                preparedStatement.setString(2, user.email());
                preparedStatement.execute();
            }
        }
    }

    private Connection initConnection(String properties) throws IOException, SQLException, ClassNotFoundException {
        Properties config = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream(properties)) {
            config.load(in);
            Class.forName(config.getProperty("jdbc.driver"));
            return DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password"));

        }
    }

    private void validate(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Must contain Username & Email.");
        }
        if (parts[0].isEmpty()) {
            throw new IllegalArgumentException("Username is missing!");
        }
        if (parts[1].isEmpty()) {
            throw new IllegalArgumentException("Email is missing");
        }
    }

    public static void main(String[] args) throws Exception {
        ImportDB dataBase = new ImportDB("app.properties", "./data/dump.txt");
        dataBase.save(dataBase.load());
        try (Statement statement = dataBase.initConnection("app.properties").createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                System.out.printf("Username: %s%sEmail: %s%n", resultSet.getString("name"),
                        System.lineSeparator(), resultSet.getString("email"));
            }
        }
    }
}
