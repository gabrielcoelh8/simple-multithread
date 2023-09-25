package sisdis.sockets;

import java.io.*;
import java.net.*;

public class SimpleJavaClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Socket s = new Socket("127.0.0.1", 9999);
            InputStream i = s.getInputStream();
            OutputStream o = s.getOutputStream();
            String str;
            do {
                byte[] line = new byte[100];
                System.in.read(line);
                o.write(line);
                i.read(line);
                str = new String(line);
                System.out.println(str.trim());
            } while (!str.trim().equals("bye"));
            s.close();
        } catch (IOException err) {
            System.err.println(err);
        }

    }

}
