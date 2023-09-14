package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    System.out.println(str);
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (str.contains("?msg=Hello")) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        out.write("Hello!".getBytes());
                    }
                    if (str.contains("?msg=Exit")) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        server.close();
                    }
                    if (!str.contains("?msg=Exit") && !str.contains("?msg=Hello")) {
                        out.write("What?".getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}
