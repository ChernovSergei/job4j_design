package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.StringJoiner;
import java.sql.DriverManager;
import java.io.FileReader;
import java.sql.Statement;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
    }

    public void initConnection() throws Exception {
        try (FileReader in = new FileReader("data/app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("create table if not exists %s(%s)", tableName, "id serial primary key");
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("drop table %s", tableName);
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("alter table %s add column if not exists %s %s", tableName, columnName, type);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("alter table %s drop column %s", tableName, columnName);
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("alter table %s rename column %s to %s", tableName, columnName, newColumnName);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i)));
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        String tableName = "ideaToSql";
        String columnName = "Name";
        String updatedColumnName = "UpdatedName";
        Properties prop = new Properties();
        TableEditor table = new TableEditor(prop);
        table.initConnection();
        table.createTable(tableName);
        table.addColumn(tableName, columnName, "text");
        table.renameColumn(tableName, columnName, updatedColumnName);
        table.addColumn(tableName, columnName, "text");
        table.dropColumn(tableName, updatedColumnName);
        System.out.println(table.getTableScheme(tableName));
        table.dropTable(tableName);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
