package sisdis.matriz;

import java.util.Random;
/**
 *
 * @author gabrielcoelho
 */
public class MatrizAxB implements Runnable {
    int A = 5;
    int B = 5;
    int[][] AxB = new int[A][B];

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Inicializando a matriz...");

        //Criacao do objeto executavel
        MatrizAxB matriz = new MatrizAxB();

        //Criacao da Thread
        Thread threadMatriz = new Thread(matriz);
        threadMatriz.start();
        threadMatriz.join();
        
        System.out.println("Fim...");
    }

    @Override
    public void run() {
        int soma = 0;

        preencher();

        for (int i = 0; i < AxB.length; i++) {
            for (int j = 0; j < AxB[0].length; j++) {
                soma += AxB[i][j];
            }
            System.out.printf("Soma Linha %d: %d \n", i, soma);
            soma = 0;
        }
    }

    public void preencher() {
        Random random = new Random();
        for (int i = 0; i < AxB.length; i++) {
            for (int j = 0; j < AxB[0].length; j++) {
                AxB[i][j] = random.nextInt(100) + 1;
                System.out.printf("%d ", AxB[i][j]);
            }
            System.out.println("");
        }
    }
}
