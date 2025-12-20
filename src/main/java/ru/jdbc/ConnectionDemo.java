package ru.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import ru.io.Config;

public class ConnectionDemo {

    private String driver;
    private String url;
    private String login;
    private String password;

    public ConnectionDemo() {

    }

    public void connect(String path) throws ClassNotFoundException {
        Config config = new Config(path);
        config.load();
        this.driver = config.value("driver");
        this.url = config.value("url");
        this.login = config.value("login");
        this.password = config.value("password");
        Class.forName(driver);
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ConnectionDemo connectionDemo = new ConnectionDemo();
        connectionDemo.connect("data/app.properties");
        try (Connection connection = DriverManager.getConnection(
                connectionDemo.getUrl(), connectionDemo.getLogin(), connectionDemo.getPassword())) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }

    }
}
