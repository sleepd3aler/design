package ru.report_generation_app.configurations;

import java.sql.*;

public class SqlConfig implements Config, AutoCloseable {
    private final Connection connection;

    public SqlConfig(Config config) {
        try {
            this.connection = getConnection(config);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getInterval(String key) {
        int interval = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "select \"interval\" from formats where name = ?"
        )) {
            statement.setString(1, key);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                interval = resultSet.getInt("interval");
            }
            return interval;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String string) {
        String format = "";
        try (PreparedStatement statement = connection.prepareStatement(
                "select pattern from formats where name = ?"
        )) {
            statement.setString(1, string);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                format = resultSet.getString("pattern");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return format;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private Connection getConnection(Config config) throws SQLException {
        return DriverManager.getConnection(config.get("url"),
                config.get("username"),
                config.get("password"));
    }
}