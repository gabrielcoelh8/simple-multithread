package sisdis.sistemapedidos;

/**
 *
 * @author gabrielcoelho
 */
public class Consumidor implements Runnable {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                int item = buffer.consumir();
                System.out.println("Consumiu: " + item);
                Thread.sleep(2000); // Simula o consumo de um item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
