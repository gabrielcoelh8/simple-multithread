package sisdis.sistemapedidos;

/**
 *
 * @author gabrielcoelho
 */
public class Pedido {
    private String nome;
    private long tempoPreparo;
    private Categoria categoria;
    
    public enum Categoria {
        entrada,
        prato_principal, 
        sobremesa
    };

    public Pedido(String nome, long tempoPreparo, Categoria categoria) {        
        this.nome = nome;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
