package sisdis.sistemapedidos;

import java.util.concurrent.BlockingQueue;
//import java.util.Random;

/**
 *
 * @author gabrielcoelho
 */
public class Cozinheiro implements Runnable {
    private long id;
    private BlockingQueue<Pedido> filaPedidos;

    public Cozinheiro(long id, BlockingQueue<Pedido> filaPedidos) {
        this.id = id;
        this.filaPedidos = filaPedidos;
    }

    @Override
    public void run() {
        try {
            Pedido pedido;
            // consuming messages until exit message is received
            while ((pedido = filaPedidos.take()) != null) {
                System.out.println("Cozinheiro "+ id +" está preparando o pedido: " + pedido.getCategoria() + 
                        "\nCliente: "+ pedido.getClienteNome() + 
                        ".\nTempo de espera: "+pedido.getTempoPreparo());

                // Simula o tempo de preparo do pedido
                Thread.sleep(pedido.getTempoPreparo()); // Entre 100 e 5000 milissegundos

                // Notifica o cliente que o pedido está pronto
                pedido.addPedidoPronto(pedido);
                System.out.println("Pedido: " + pedido.getCategoria() + " para " + pedido.getClienteNome() + " está pronto!");
                
                if (filaPedidos.isEmpty()) {
                    break;
                }
            }
        } catch (InterruptedException e) {
        }

    }
}
