package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import ru.job4j.io.Config;
import java.util.StringJoiner;

public class ConnectionDemo {
    public static Connection getConnection() throws Exception {
        Config config = new Config("data/app.properties");
        config.load();
        Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        return DriverManager.getConnection(url, login, password);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "name", "type");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
               "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
               String sql = String.format(
                       "create table if not exists demo_table(%s, %s);",
                       "id serial primary key",
                       "name text"
               );
               statement.execute(sql);
               System.out.println(getTableScheme(connection, "demo_table"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
