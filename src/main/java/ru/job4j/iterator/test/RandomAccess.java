package ru.job4j.iterator.test;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
    public static void main(String[] args) {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("C:\\projects\\Test\\random.txt", "rw");
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());
            randomAccess.seek(4);
            System.out.println();
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.getFilePointer());
            System.out.println();
            randomAccess.seek(4);
            randomAccess.writeChar('B');
            randomAccess.seek(4);
            System.out.println(randomAccess.readChar());
            randomAccess.seek(randomAccess.length());
            randomAccess.writeDouble(3.01);
            randomAccess.seek(7);
            System.out.println(randomAccess.readDouble());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
