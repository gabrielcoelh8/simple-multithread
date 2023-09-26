package sisdis.sockets_atividade;
import java.io.IOException;
import java.net.Socket;
/**
• Implemente um programa Cliente em Java que atue como um
scanner de porta:
* 
• Seu programa irá receber um endereço IP
• Com este endereço, irá checar as portas de 1 a 512 para tentar
descobrir quais estão abertas
• Importante encerrar a conexão daquelas que estão abertas
• Ao final, será exibido quais as portas daquele determinado IP estão
abertas
 */
public class ScannerPorta {
    public static void main(String[] args) {
        String ipAddress = "127.0.0.1";  // Endereço IP a ser verificado

        for (int port = 1; port <= 512; port++) {
            try {
                Socket socket = new Socket(ipAddress, port);
                socket.close();
                System.out.println("Porta " + port + " está aberta.");
            } catch (IOException e) {
                // A exceção indica que a porta está fechada ou ocorreu um problema de conexão
            }
        }
    }
}