package ru.job4j.test;

import java.io.*;

public class DataStream {

    public static void main(String[] args) throws Exception {
        String path = "C:\\projects\\Test\\dataOutput.txt";
        String[] names = {"unit1", "unit2", "unit3"};
        int[] amount = {5, 7, 2};
        boolean[] checked = {true, false, true};

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
             DataInputStream in = new DataInputStream(new FileInputStream(path))) {
            for (int i = 0; i < names.length; i++) {
                out.writeUTF(names[i]);
                out.writeInt(amount[i]);
                out.writeBoolean(checked[i]);
            }
            while (true) {
                String n = in.readUTF();
                int a = in.readInt();
                boolean c = in.readBoolean();
                System.out.println("Name: " + n + ", amount: " + a + ", checked: " + c);
            }
        } catch (EOFException e) {
            System.out.println("End of file had been reached");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
