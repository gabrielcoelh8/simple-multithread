package sisdis.example;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class fatorialParalelo implements Runnable {

    private int start, end;
    private BigInteger result = BigInteger.ONE;

    public fatorialParalelo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = start; i <= end; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int threadPoolSize = 8; //quantidade de Threads
        int i;

        long timeStart = System.currentTimeMillis();

        //gerenciar Pool de Threads
        ExecutorService executar = Executors.newFixedThreadPool(threadPoolSize);

        fatorialParalelo[] threads = new fatorialParalelo[threadPoolSize];

        for (i = 0; i < threadPoolSize; i++) {
            threads[i] = new fatorialParalelo(75000 * i + 1, 75000 * (i + 1));
            executar.execute(threads[i]); //executa a thread em algum momento no futuro
        }

        //ExecutorService n�o aceita mais Threads e, quando todos os 
        //threads tiverem conclu�do as tarefas atuais, 
        //o ExecutorService ser� encerrado
        executar.shutdown();

        //Bloqueia at� que todas as tarefas concluam a execu��o 
        while (!executar.awaitTermination(24L, TimeUnit.HOURS)) {
            System.out.println("Not yet. Still waiting for termination");
        }

        BigInteger total = threads[0].result;

        for (i = 1; i < threadPoolSize; i++) {
            total = total.multiply(threads[i].result);
        }

        long timeEnd = System.currentTimeMillis();

        System.out.printf("Resultado = %d, Tempo = %.4f%n", total.bitCount(),
                (timeEnd - timeStart) / 1000.0);
    }

    /* OUTRA FORMA DE IMPLEMENTAR THREADS, SEM UTILIZAR POOL  
    public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		final int n = 600000;
    
        long timeStart = System.currentTimeMillis();
        
	    fatorialParalelo p1 = new fatorialParalelo(2, n / 2);
	    fatorialParalelo p2 = new fatorialParalelo(n / 2 + 1, n);
	    // as threads s�o criadas
	    Thread t1 = new Thread(p1);
	    Thread t2 = new Thread(p2);
	    //somente ap�s o star � que v�o para o estado de pronto e podem ser escalonadas
	    t1.start();
	    t2.start();
	    
	    //bloqueia at� a Thread terminar
	    t1.join();
	    t2.join();
	    
	    BigInteger total = p1.result.multiply(p2.result);
	    
	    long timeEnd = System.currentTimeMillis();

        System.out.printf("Resultado = %d, Tempo = %.4f%n", total.bitCount(),
                        (timeEnd - timeStart) / 1000.0);
    }
     */
}
