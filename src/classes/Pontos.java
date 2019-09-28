package classes;

import java.io.Serializable;

/**
 *
 * @author Vinicius
 */
public class Pontos implements Serializable{
    private int valor;
    private String nome;

    public Pontos(int valor, String nome) {
        this.valor = valor;
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    
    
    
}


