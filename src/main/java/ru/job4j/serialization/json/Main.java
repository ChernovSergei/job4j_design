package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final Cafeteria cafeteria = new Cafeteria(1976,
                "Blossom Time",
                new Contact(123654, "911"),
                false,
                new String[] {"Maria", "Anna", "John", "Sarah"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cafeteria));
        final String cafeteriaJson =
                "{"
                + "\"foundationYear\":1999,"
                + "\"name\":\"Infinite Sky\","
                + "\"contact\":"
                + "{"
                + "\"zipCode\":987123,"
                + "\"phone\":\"+7 (983) 123-23-43\""
                + "},"
                + "\"isChainBusiness\":true,"
                + "\"employees\":"
                + "[\"Marat\",\"Mike\",\"Margaret\"]"
                + "}";
        final Cafeteria cafeteriaMod = gson.fromJson(cafeteriaJson, Cafeteria.class);
        System.out.println(cafeteriaMod);
        System.out.println();
        Cafeteria podium = new Cafeteria(1997, "Podium", new Contact(123654, "+90 434 5043"), false, new String[] {"Gogia", "Maka", "Kunduzai"});
        System.out.println(new JSONObject(podium));
    }
}
