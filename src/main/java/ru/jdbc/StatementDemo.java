package ru.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static ru.jdbc.Schemas.getTableScheme;

public class StatementDemo {

    private static Connection getConnection() throws Exception {
        ConnectionDemo connection = new ConnectionDemo();
        connection.connect("data/app.properties");
        return DriverManager.getConnection(connection.getUrl(), connection.getLogin(), connection.getPassword());
    }

    public static void main(String[] args) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS demo_table(%s, %s);",
                        "id SERIAL PRIMARY KEY",
                        "name TEXT"
                );
                statement.execute(sql);
                System.out.println(getTableScheme(connection, "demo_table"));
            }
        }
    }

}
