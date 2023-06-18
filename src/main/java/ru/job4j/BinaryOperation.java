package ru.job4j;

public class BinaryOperation {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 4 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int h = 1;
        System.out.println(binary(h));
        System.out.println(binary(h >>> 16));
        System.out.println(binary(h ^ (h >>> 16)));
        System.out.println();
        System.out.println(binary(16));
        System.out.println(binary(16 - 1));
        System.out.println(binary((16 - 1) & h));
        System.out.println("KING".hashCode() ^ "KING".hashCode() >>> 16);
    }
}
