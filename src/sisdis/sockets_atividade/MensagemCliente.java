/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisdis.sockets_atividade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class MensagemCliente {
    public static void main(String[] args) {
        String serverIPAddress = "127.0.0.1";  // Endereço IP do servidor
        int serverPort = 12345;  // Porta do servidor

        try {
            Socket socket = new Socket(serverIPAddress, serverPort);
            Scanner read = new Scanner(System.in);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("Escreva a mensagem: ");
            
            String message = read.nextLine();
            out.println(message);

            // Receber as respostas do servidor
            String copiedMessage = in.readLine();
            String characterCount = in.readLine();
            String reversedMessage = in.readLine();

            System.out.println("Resposta do servidor:");
            System.out.println(copiedMessage);
            System.out.println(characterCount);
            System.out.println(reversedMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
