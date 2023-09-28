package sisdis.sistemapedidos;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author gabrielcoelho
 */
public class Produtor implements Runnable{
    private Buffer cozinheiro;
    private ArrayList<Cliente> clientes;
    
    public Produtor(Buffer cozinheiro, ArrayList<Cliente> clientes) {
        this.cozinheiro = cozinheiro;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        try {
                Cliente cliente = clientes.get(0);
                Queue<Pedido> pedidos = cliente.getPedidos();
                Pedido current_pedido = pedidos.peek();
                
                cozinheiro.produzir(current_pedido);
                Thread.sleep(current_pedido.getTempoPreparo()); // Simula a produção de um item
                System.out.printf("Caro, cliente %d. O pedido %s está pronto!%n", cliente.getId(), current_pedido.getNome());
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
   
}
