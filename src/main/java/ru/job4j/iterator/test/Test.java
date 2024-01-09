package ru.job4j.iterator.test;

import java.io.*;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.nio.*;

public class Test {
    public static void main(String[] args) throws IOException {
        String letter = "My letter starts there\r\n";
        String letter2 = "My letter ends there";
        Path path = Path.of(".");
        System.out.println(path.isAbsolute());
        System.out.println(path.getRoot());
        System.out.println(path.getParent());
        System.out.println(path.toAbsolutePath());
    }
}
