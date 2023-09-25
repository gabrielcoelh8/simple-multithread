package sisdis.sockets_atividade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import sisdis.sockets.ServidorDeChat;

/**
 * • Servidor de Data e Hora
 *
 * • Implemente um servidor que, quando um cliente se conecta a ele, o mesmo
 * envia a Data e Hora da máquina para o cliente; • Implemente um cliente para o
 * servidor
 */
public class DataHora implements Runnable {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(2222);
            while (true) {
                System.out.print("Esperando...");
                Socket conexao = s.accept();
                
                System.out.println("Alguém se conectou");
                // cria uma nova thread para tratar essa conexao
                ServidorDeChat servC = new ServidorDeChat(conexao);
                Thread t = new Thread(servC);
                t.start();
                // voltando ao loop, esperando mais alguem se conectar.
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
    
    private Socket conexao;
    public DataHora(Socket s) {
        conexao = s;
    }
    
    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex);
        }
            
    }
}
