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
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String line = in.readLine();
                    if (line.matches("^GET /\\?msg=Exit\\b.*")) {
                        server.close();
                    }
                    String outMsg = line.matches("^GET /\\?msg=Hello\\b.*") ? "Hello" : "What";
                    out.write(outMsg.getBytes());
                    out.flush();
                }
            }
        }
    }
}