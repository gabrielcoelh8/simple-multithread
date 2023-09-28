package sisdis.sistemapedidos;

import java.util.Queue;
import java.util.Random;

/**
 *
 * @author gabrielcoelho
 */
public class Cozinheiro implements Runnable {

    private final Queue<Pedido> filaPedidos;

    public Cozinheiro(Queue<Pedido> filaPedidos) {
        this.filaPedidos = filaPedidos;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            synchronized (filaPedidos) {
                
                while (filaPedidos.isEmpty()) {
                    try {
                        filaPedidos.wait(); // Espera por pedidos na fila
                    } catch (InterruptedException e) {
                    }
                }
                
                Pedido pedido = filaPedidos.poll();
                System.out.println("Cozinheiro está preparando o pedideo de " + pedido.getCategoria() + " para " + pedido.getNome());

                // Simula o consumo do pedido pelo cliente
                try {
                    Thread.sleep(rand.nextInt(4900) + 100); // Entre 100 e 5000 milissegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Notifica o cliente que o pedido está pronto
                System.out.println("Pedido de " + pedido.getCategoria() + " para " + pedido.getNome() + " está pronto!");
            }
        }
    }
}
