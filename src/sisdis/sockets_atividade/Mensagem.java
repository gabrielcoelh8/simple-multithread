package sisdis.sockets_atividade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Collectors;

/**
• Crie um servidor que, ao receber uma mensagem de um cliente,
faça:
* 
• Devolva uma cópia com a mesma frase e todas as letras maiúsculas
• Devolva o quantitativo de caracteres da frase
• Devolva uma cópia da mesma frase em ordem inversa

 */
public class Mensagem {
    public static void main(String[] args) {
        int port = 12345; // Porta do servidor

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor esperando por conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();

                // Cópia da frase em letras maiúsculas
                String copiedMessage = message.toUpperCase();
                out.println("Cópia da frase em letras maiúsculas: " + copiedMessage);

                // Quantidade de caracteres da frase
                int characterCount = message.length();
                out.println("Quantidade de caracteres da frase: " + characterCount);

                // Cópia da frase em ordem inversa
                String reversedMessage = new StringBuilder(message).reverse().toString();
                out.println("Frase em ordem inversa: " + reversedMessage);

                clientSocket.close();
                System.out.println("Conexão encerrada com o cliente: " + clientSocket.getInetAddress().getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}