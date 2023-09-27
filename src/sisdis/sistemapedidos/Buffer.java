package sisdis.sistemapedidos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author gabrielcoelho
 */
public class Buffer {
    private BlockingQueue<Integer> queue;

    public Buffer() {
        this.queue = new LinkedBlockingQueue<>(10); // Capacidade máxima do buffer é 10
    }

    public void produzir(int item) throws InterruptedException {
        queue.put(item);
    }

    public int consumir() throws InterruptedException {
        return queue.take();
    }
}
