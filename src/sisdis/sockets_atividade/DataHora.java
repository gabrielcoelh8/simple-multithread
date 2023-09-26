package sisdis.sockets_atividade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import sisdis.sockets.ServidorDeChat;

/**
 * • Servidor de Data e Hora
 *
 * • Implemente um servidor que, quando um cliente se conecta a ele, o mesmo
 * envia a Data e Hora da máquina para o cliente; • Implemente um cliente para o
 * servidor
 */
public class DataHora {

    public static void main(String[] args) {
            int port = 12345; // Porta do servidor

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor esperando por conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                out.println(dateTime);

                clientSocket.close();
                System.out.println("Conexão encerrada com o cliente: " + clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException e) {
        }
    }
}
