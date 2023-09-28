package sisdis.sistemapedidos;

import java.util.Random;

/**
 *
 * @author gabrielcoelho
 */
public class Pedido {
    private String nome;
    private int tempoPreparo;
    private Categoria categoria;
    
    public enum Categoria {
        entrada,
        prato_principal, 
        sobremesa
    };

    public Pedido(String nome, Categoria categoria) {
        Random rand = new Random();
        int tempoAleatorio = rand.nextInt(3900) + 100; //0 - 4000
        
        this.nome = nome;
        this.tempoPreparo = tempoAleatorio;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempoPreparo() {
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
