package sisdis.sistemapedidos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import sisdis.sistemapedidos.Pedido.Categoria;

/**
 *
 * @author gabrielcoelho
 */
public class Cliente implements Runnable {
    private final String nome;
    private Queue<Pedido> filaPedidos;

    public Cliente(Queue<Pedido> filaPedidos, String nome) {
        this.nome = nome;
        this.filaPedidos = new LinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public Queue<Pedido> getPedidos() {
        return filaPedidos;
    }

    public void addPedido(Pedido pedido){
        //add no final da fila
        this.filaPedidos.add(pedido);
    }
    
    public void removePedido() {
        //remove o primeiro da fila
        this.filaPedidos.remove();
    }

    @Override
    public void run() {
        Random rand = new Random();
        //String[] categorias = {"entrada", "prato principal", "sobremesa"};

        for (Categoria categoria : Categoria.values()) {
            long tempoPreparo = rand.nextInt(3900) + 100; // Entre 100 e 4000 milissegundos
            Pedido pedido = new Pedido(nome, tempoPreparo, categoria);

            synchronized (filaPedidos) {
                filaPedidos.offer(pedido);
                System.out.println(nome + " fez um pedido de " + categoria);
                filaPedidos.notifyAll(); // Notifica os cozinheiros
            }

            try {
                Thread.sleep(tempoPreparo); // Cliente aguarda o pedido ficar pronto
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
