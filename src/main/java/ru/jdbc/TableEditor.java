package ru.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static ru.jdbc.Schemas.getTableScheme;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private String sql = "";

    public TableEditor(String propertiesFile) throws Exception {
            initConnection(propertiesFile);
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();", tableName
            );
            statement.execute(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            sql = String.format(
                    "DROP TABLE %s;", tableName
            );
            statement.execute(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName, columnName, type
            );
            statement.execute(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName, columnName
            );
            statement.execute(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = this.connection.createStatement()) {
            sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName, columnName, newColumnName
            );
            statement.execute(sql);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    private void initConnection(String propertiesFile) throws IOException, SQLException, ClassNotFoundException {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty("driver"));
            this.connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("login"),
                    properties.getProperty("password")
            );
        }
    }

    public static void main(String[] args) {
        try (TableEditor editor = new TableEditor("app.properties")) {
            Connection connection = editor.getConnection();
            editor.createTable("test");
            System.out.println(getTableScheme(connection, "test"));
            editor.addColumn("test", "name", "varchar(50)");
            System.out.println(getTableScheme(connection, "test"));
            editor.renameColumn("test", "name", "surname");
            System.out.println(getTableScheme(connection, "test"));
            editor.dropColumn("test", "surname");
            System.out.println(getTableScheme(connection, "test"));
            editor.dropTable("test");
            System.out.println(getTableScheme(connection, "test"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}