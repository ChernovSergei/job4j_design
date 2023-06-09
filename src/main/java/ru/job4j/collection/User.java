package ru.job4j.collection;

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

    public static void main(String[] args) {
        User user1 = new User("Sergei", 1, new GregorianCalendar(1902, 02, 24));
        User user2 = new User("Sergei", 1, new GregorianCalendar(1902, 02, 24));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map.get(user1));
        System.out.println(map.get(user2));
     }

     @Override
     public int hashCode() {
        return Objects.hash(name, children, birthday);
     }

     @Override
     public boolean equals(Object o) {
        if (this == o) {
             return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name) && user.children == children && birthday.equals(user.birthday);
     }
}
