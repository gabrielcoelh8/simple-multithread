package sisdis.sistemapedidos;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author gabrielcoelho
 */
public class Cliente {
    private long id;
    private Queue<Pedido> pedidos;

    public Cliente(long id) {
        this.id = id;
        this.pedidos = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Queue<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Queue<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public void addPedido(Pedido pedido){
        //add no final da fila
        this.pedidos.add(pedido);
    }
    
    public void removePedido() {
        //remove o primeiro da fila
        this.pedidos.remove();
    }
}
