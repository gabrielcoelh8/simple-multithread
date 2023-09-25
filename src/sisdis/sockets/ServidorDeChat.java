package sisdis.sockets;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorDeChat implements Runnable {

    public static void main(String args[]) {
        // instancia o vetor de clientes conectados
        clientes = new Vector();
        try {
            // criando um socket que fica escutando a porta 2222.
            ServerSocket s = new ServerSocket(2222);
            // Loop principal.
            while (true) {
                // aguarda algum cliente se conectar. A execucao do
                // servidor fica bloqueada na chamada do metodo accept da
                // classe ServerSocket. Quando algum cliente se conectar
                // ao servidor, o metodo desbloqueia e retorna com um
                // objeto da classe Socket, que eh porta da comunicacao.
                System.out.print("Esperando alguem se conectar...");
                Socket conexao = s.accept();
                System.out.println(" Conectou!");
                
                // cria uma nova thread para tratar essa conexao
                ServidorDeChat servC = new ServidorDeChat(conexao);
                Thread t = new Thread(servC);
                t.start();
                // voltando ao loop, esperando mais alguem se conectar.
            }
        } catch (IOException e) {
            // caso ocorra alguma excessao de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }

    //Parte que controla as conexoes por meio de threads.
    //Note que a instanciacao esta no main.
    private static Vector clientes;
    //socket deste cliente
    private Socket conexao;
    //nome deste cliente
    private String meuNome;
    //construtor que recebe o socket deste cliente

    public ServidorDeChat(Socket s) {
        conexao = s;
    }

    //execu��o da thread
    public void run() {
        try {
            //objetos que permitem controlar fluxo de comunicacao
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            //primeiramente, espera-se pelo nome do cliente
            meuNome = entrada.readLine();
            //agora, verifica se string recebida eh valida, pois
            //sem a conexao foi interrompida, a string eh null.
            //Se isso ocorrer, deve-se terminar a execucao.
            if (meuNome == null) {
                return;
            }
            //Uma vez que se tem um cliente conectado e conhecido,
            //coloca-se fluxo de saida para esse cliente no vetor de
            //clientes conectados.
            clientes.add(saida);
            //clientes eh objeto compartilhado por varias threads!
            //De acordo com o manual da API, os metodos sao
            //sincronizados. Portanto, nao ha problemas de acessos
            //simultaneos.
            //Loop principal: esperando por alguma string do cliente.
            //Quando recebe, envia a todos os conectados ate que o
            //cliente envie linha em branco.
            //Verificar se linha eh null (conexao interrompida)
            //Se nao for nula, pode-se compara-la com metodos string
            String linha = entrada.readLine();
            while (linha != null && !(linha.trim().equals(""))) {
                //reenvia a linha para todos os clientes conectados
                sendToAll(saida, " disse: ", linha);
                //espera por uma nova linha.
                linha = entrada.readLine();
            }
            //Uma vez que o cliente enviou linha em branco, retira-se
            //fluxo de saida do vetor de clientes e fecha-se conexao.
            sendToAll(saida, " saiu ", "do chat!");
            clientes.remove(saida);
            conexao.close();
        } catch (IOException e) {
            //Caso ocorra alguma excessao de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }
    //enviar uma mensagem para todos, menos para o proprio

    public void sendToAll(PrintStream saida, String acao,
            String linha) throws IOException {
        Enumeration e = clientes.elements();
        while (e.hasMoreElements()) {
            //obtem o fluxo de saida de um dos clientes
            PrintStream chat = (PrintStream) e.nextElement();
            //envia para todos, menos para o proprio usuario
            if (chat != saida) {
                chat.println(meuNome + acao + linha);
            }
        }
    }
}
