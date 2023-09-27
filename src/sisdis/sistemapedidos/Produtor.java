package sisdis.sistemapedidos;

/**
 *
 * @author gabrielcoelho
 */
public class Produtor implements Runnable{
    private Buffer buffer;

    public Produtor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.produzir(i);
                System.out.println("Produziu: " + i);
                Thread.sleep(1000); // Simula a produção de um item
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
   
}
