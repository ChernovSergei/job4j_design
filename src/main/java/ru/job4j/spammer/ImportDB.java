package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Connection connection;
    private Properties config;
    private String dump;

    private boolean argsCheck(String arg1, String arg2) {
        if (arg1.length() == 0 || arg2.length() == 0 || arg2 == ";") {
            String error = "Error - wrong format in the dump.txt file. Data in the dump.txt file should have next format - name; email;.";
            throw new IllegalArgumentException(error);
        }
        return true;
    }

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public void connectToDB() throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
                );
    }

    public void createTable() {
        try (Statement statement =  connection.createStatement()) {
            String query = String.format("create table if not exists users(id serial primary key, name text, email text)");
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines()
                    .filter(u -> argsCheck(u.split(";", 3)[0], u.split(";", 3)[1]))
                    .forEach(u -> users.add(new User(u.split(";", 3)[0], u.split(";", 3)[1])));
        }
        return users;
    }

    public void save(List<User> users) throws SQLException {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("insert into users (name, email) values (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            config.load(in);
        }
        ImportDB dataBase = new ImportDB(config, "./src/main/resources/dump.txt");
        dataBase.connectToDB();
        dataBase.createTable();
        dataBase.save(dataBase.load());
    }
}
