package sisdis.sockets;

import java.io.*;
import java.net.*;

public class SimpleJavaServer {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            ServerSocket s = new ServerSocket(9999);
            String str;
            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();
                do {
                    byte[] line = new byte[100];
                    i.read(line);
                    o.write(line);
                    str = new String(line);
                    System.out.println("Server: " + str.trim());
                } while (!str.trim().equals("bye"));
                c.close();
            }
        } catch (IOException err) {
            System.err.println(err);
        }
    }
}
