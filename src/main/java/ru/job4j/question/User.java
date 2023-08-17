package ru.job4j.question;

public class User {
    Object o;
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User result = (User) o;
        return id == result.getId() && name.equals(result.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + id;
    }
}
