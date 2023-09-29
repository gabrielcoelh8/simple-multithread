package sisdis.sistemapedidos;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author gabrielcoelho
 */
public class Cliente implements Runnable {

    private final String nome;
    private BlockingQueue<Pedido> filaPedidos;
    private BlockingQueue<Pedido> pedidosProntos;

    public Cliente(BlockingQueue<Pedido> filaPedidos, String nome) {
        this.nome = nome;
        this.filaPedidos = filaPedidos;
        this.pedidosProntos = new LinkedBlockingQueue<>();
    }

    public String getNome() {
        return nome;
    }
    
    public void addPedidoPronto(Pedido pedidoPronto) throws InterruptedException {
        pedidosProntos.put(pedidoPronto);
    }

    @Override
    public void run() {
        Random rand = new Random();
        // String[] categorias = {"entrada", "prato principal", "sobremesa"};

        for (Pedido.Categoria categoria : Pedido.Categoria.values()) {
            long tempoPreparo = rand.nextInt(3900) + 100; // Entre 100 e 4000 milissegundos
            Pedido pedido = new Pedido(this, tempoPreparo, categoria);

            synchronized (filaPedidos) {
                try {
                    filaPedidos.put(pedido);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(nome + " fez um pedido de " + categoria);
            }
            
            synchronized (pedidosProntos) {
                try {
                    pedidosProntos.take();
                    long tempoConsumo = rand.nextInt(4900) + 100; // Entre 100 e 5000 milissegundos
                    Thread.sleep(tempoConsumo);
                    System.out.println(nome + " consumiu o pedido de " + categoria + " por " + tempoConsumo +"mm!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }
    }
}
