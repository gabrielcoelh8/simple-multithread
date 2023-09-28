package sisdis.sistemapedidos;

import java.util.Random;

/**
 *
 * @author gabrielcoelho
 */
public class Consumidor implements Runnable {
    private Buffer buffer;
    private Cliente cliente;

    public Consumidor(Buffer buffer, Cliente cliente) {
        this.buffer = buffer;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
                Random rand = new Random();
                int tempoAleatorio = rand.nextInt(4900) + 100; //0 - 4000
                
                Pedido current_pedido = cliente.getPedidos().poll();
                current_pedido = buffer.consumir();
                Thread.sleep(tempoAleatorio); // Simula o consumo do cliente
                System.out.printf("Cliente %d consumiu: %s!%n", cliente.getId(), current_pedido.getNome());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
