package sisdis.sockets;

import java.io.*;
import java.net.*;

public class ClienteDeChat extends Thread {
    // Flag que indica quando se deve terminar a execucao.

    private static boolean done = false;

    public static void main(String args[]) {
        try {
            // Para se conectar a algum servidor, basta se criar um
            // objeto da classe Socket. O primeiro parametro eh o IP ou
            // o endereco da maquina a qual se quer conectar e o
            // segundo parametro eh a porta da aplicacao. Neste caso,
            // utiliza-se o IP da maquina local (127.0.0.1) e a porta
            // da aplicacao ServidorDeChat. Nada impede a mudanca
            // desses valores, tentando estabelecer uma conexao com
            // outras portas em outras maquinas.
            Socket conexao = new Socket("127.0.0.1", 2222);

            // uma vez estabelecida a comunicacao, deve-se obter os
            // objetos que permitem controlar o fluxo de comunicacao
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            // enviar antes de tudo o nome do usuario
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Entre com o seu nome: ");
            String meuNome = teclado.readLine();
            saida.println(meuNome);

            // Uma vez que tudo esta pronto, antes de iniciar o loop
            // principal, executar a thread de recepcao de mensagens.
            Thread t = new ClienteDeChat(conexao);
            t.start();

            // loop principal: obtendo uma linha digitada no teclado e
            // enviando-a para o servidor.
            String linha;

            while (true) {
                // ler a linha digitada no teclado
                System.out.print("> ");
                linha = teclado.readLine();
                // antes de enviar, verifica se a conexao nao foi fechada
                if (done) {
                    break;
                }
                // envia para o servidor
                saida.println(linha);
            }
        } catch (IOException e) {
            // Caso ocorra alguma excessao de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }

    // parte que controla a recepcao de mensagens deste cliente
    private Socket conexao;

    // construtor que recebe o socket deste cliente
    public ClienteDeChat(Socket s) {
        conexao = s;
    }

    // execucao da thread
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            while (true) {
                // pega o que o servidor enviou
                linha = entrada.readLine();
                // verifica se eh uma linha valida. Pode ser que a conexao
                // foi interrompida. Neste caso, a linha eh null. Se isso
                // ocorrer, termina-se a execucao saindo com break
                if (linha == null) {
                    System.out.println("Conexao encerrada!");
                    break;
                }
                // caso a linha nao seja nula, deve-se imprimi-la
                System.out.println();
                System.out.println(linha);
                System.out.print("...> ");
            }
        } catch (IOException e) {
            // caso ocorra alguma excecao de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
        // sinaliza para o main que a conexao encerrou.
        done = true;
    }
}
