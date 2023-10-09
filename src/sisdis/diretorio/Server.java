package sisdis.diretorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

///////https://www.tutorialspoint.com/java/java_files_io.htm
/**
 * Comandos: -- INDEX: o servidor responde enviando uma lista de nomes de todos
 * os arquivos que estão disponíveis no servidor. -- GET <nome do arquivo>: onde
 * <nome do arquivo> é um nome de arquivo. O servidor verifica se o arquivo
 * solicitado realmente existe. Em caso afirmativo, ele primeiro envia a palavra
 * "OK" como uma mensagem para o cliente. Em seguida, ele envia o conteúdo do
 * arquivo e fecha a conexão. Caso contrário, ele envia uma linha começando com
 * a palavra "ERROR" para o cliente e fecha a conexão. (A resposta de erro pode
 * incluir uma mensagem de erro no restante da linha.)
 *
 * Seu programa deve usar uma sub-rotina (thread) para lidar com cada
 * solicitação que o servidor recebe. Ele não deve parar após o tratamento de
 * uma solicitação; ele deve permanecer aberto e continuar a aceitar novas
 * solicitações.
 *
 * Cliente Inteface -- 1: Obter uma lista de arquivos que estão disponíveis no
 * servidor e exibir a lista na saída padrão; e -- 2: Obter uma cópia de um
 * arquivo especificado do servidor e salve-o em um arquivo local (no computador
 * em que o cliente está sendo executado).
 *
 *
 */
public class Server {

    public static void main(String[] args) throws IOException, URISyntaxException {

        String directoryName;       // Nome do diretorio especificado pelo usuario.
        File directory;                 // Objeto de arquivo referente ao diretorio.
        String[] files;                 // Array com os nomes dos arquivos do diretorio.
        Scanner scanner;       // Leitura da string digitada pelo usuario.

        scanner = new Scanner(System.in);

        String user_dir = System.getProperty("user.dir"); //diretorio raiz do projeto

        System.out.print("Digite o nome de um diretorio: "); /// C:/path/to/directory/file
        directoryName = scanner.nextLine().trim();
        //directoryName = user_dir + "/src/sisdis/diretorio/pasta1/";
        directory = new File(directoryName);
      
        if (directory.isDirectory() == false) {
            if (directory.exists() == false) {
                System.out.println("Este diretorio nao existe!");
                System.out.println("ERROR: "+directoryName);
            } else {
                System.out.println("Este arquivo nao eh um diretorio.");
            }
        } else {
            
            files = directory.list();
            System.out.println("Lista de arquivos no servidor, pasta: \"" + directory + "\":");
            for (String file : files) {
                System.out.println("   " + file);
            }
            
            System.out.print("GET: ");
            String get = scanner.nextLine();
            String fileName = directory.getAbsolutePath() + "/" +get;
            
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            
            try (BufferedReader br = new BufferedReader(isr)) {
                System.out.println("OK");
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            
        }

    } // end main()

} // end class DirectoryList
