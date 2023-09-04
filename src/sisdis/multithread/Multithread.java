package sisdis.multithread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 *
 * @author gabrielcoelho
 */
public class Multithread implements Runnable {

    int[] vetor = new int[100];
    private int x = 4;
    private int start;
    private int end;
    private ArrayList<Integer> result = new ArrayList<>();
    
    public Multithread(int start, int end, int[] vetor) {
        this.start = start;
        this.end = end;
        this.vetor = vetor;
    }
    
    public static void main(String[] args) throws InterruptedException {
        int threadPoolSize = 10;
        int i;
        int begins = 0;
        int ends = 9;
        int[] vetorInicial = new int[100];
        Random random = new Random();
        
        for (i = 0; i < vetorInicial.length; i++) {
            vetorInicial[i] = random.nextInt(100) + 1;
            System.out.printf("vetor inicial[%d]: %d \n", i, vetorInicial[i]);
        }
        
        ExecutorService executar = Executors.newFixedThreadPool(threadPoolSize);
        Multithread[] threads = new Multithread[threadPoolSize];
        
        for (i = 0; i < threadPoolSize; i++) {
            threads[i] = new Multithread(begins, ends, vetorInicial);
            executar.execute(threads[i]);
            begins += 10;
            ends += 10;
        }
        executar.shutdown();
        
        while (!executar.awaitTermination(24L, TimeUnit.HOURS)) {
            System.out.println("Not yet. Still waiting for termination");
        }
        
        System.out.println("Vetor final:");
         for (i = 0; i < threadPoolSize; i++) {
             ArrayList<Integer> lista = threads[i].result;
            for (Integer elemento : lista) {
                System.out.println(elemento);
            }
        }
    }

    @Override
    public void run() {
       for (int i = start; i < end; i++) {
           vetor[i] = vetor[i] * x;
           result.add(vetor[i]);
       }
       System.out.printf("Thread com início %d foi executada.\n", start);
    }

    public void preencher() {
        Random random = new Random();
        
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(100) + 1;
            System.out.printf("%d \n", vetor[i]);
        }
    }

}
