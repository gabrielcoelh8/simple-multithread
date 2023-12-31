package sisdis.sistemapedidos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabrielcoelho
 */
public class Pedido {

    private Cliente cliente;
    private long tempoPreparo;
    private Categoria categoria;

    public enum Categoria {
        entrada,
        prato_principal,
        sobremesa
    };

    public Pedido(Cliente cliente, long tempoPreparo, Categoria categoria) {
        this.cliente = cliente;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    public String getClienteNome() {
        return cliente.getNome();
    }

    public void addPedidoPronto(Pedido pedidoPronto) {
        try {
            cliente.addPedidoPronto(pedidoPronto);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public long getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
