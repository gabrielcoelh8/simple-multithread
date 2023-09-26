/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sisdis.sockets_atividade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class DataHoraCliente {
        public static void main(String[] args) {
        String serverAddress = "localhost"; // Endereço IP ou nome do servidor
        int serverPort = 12345; // Porta do servidor

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dateTime = in.readLine();

            System.out.println("Data e Hora recebidas do servidor: " + dateTime);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
