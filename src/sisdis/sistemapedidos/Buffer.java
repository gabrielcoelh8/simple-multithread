package sisdis.sistemapedidos;

//import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author gabrielcoelho
 * Cozinha
 */
public class Buffer {
    private BlockingQueue<Pedido> queue;

    public Buffer() {
        this.queue = new LinkedBlockingQueue<>(1); // Capacidade máxima do cozinheiro
    }

    public void produzir(Pedido item) throws InterruptedException {
        queue.put(item);
    }

    public Pedido consumir() throws InterruptedException {
        return queue.take();
    }
}
