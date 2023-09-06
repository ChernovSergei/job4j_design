package ru.job4j.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Pattern pattern1 = Pattern.compile("I participate Job4j online courses");
        Pattern pattern2 = Pattern.compile("I participate Job4j online courses", Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile("Job4j");

        String text1 = "I participate Job4j online courses";
        String text2 = "i participate Job4j online courses";
        String text3 = "Job4j and Job4j and Job4j";

        Matcher matcher1;
        Matcher matcher2;
        Matcher matcher3;

        matcher1 = pattern1.matcher(text1);
        System.out.println("matcher1 == text1 is " + matcher1.matches());

        matcher1 = pattern1.matcher(text2);
        System.out.println("matcher1 == text2 is " + matcher1.matches());

        matcher2 = pattern2.matcher(text1);
        System.out.println("matcher2 == text1 is " + matcher2.matches());

        matcher2 = pattern2.matcher(text2);
        System.out.println("matcher2 == text2 is " + matcher2.matches());

        matcher3 = pattern3.matcher(text2);
        System.out.println("matcher3 appears in text2 is " + matcher3.find());

        matcher3 = pattern3.matcher(text3);
        while (matcher3.find()) {
            System.out.println(matcher3.group() + " was found in text 3." + " It starts from - " + matcher3.start() + " and end with - " + matcher3.end());
        }
        System.out.println("After text change - " + matcher3.replaceAll("Hello!"));

    }
}
