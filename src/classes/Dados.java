package classes;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Vinicius
 */
public class Dados implements Serializable{ 
    private Botao[] botao;
    private byte pont, count;
    private short tenta;
    private String nivel;
    private JFrame anterior;
    private ArrayList<Pontos> records;

    public Dados(Botao[] botao, byte pont, byte count, short tenta, String nivel,JFrame anterior,ArrayList<Pontos> records) {
        this.botao = botao;
        this.pont = pont;
        this.count = count;
        this.tenta = tenta;
        this.nivel = nivel;
        this.anterior = anterior;
        this.records = records;
    }

    public ArrayList<Pontos> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<Pontos> records) {
        this.records = records;
    }

    public JFrame getAnterior() {
        return anterior;
    }

    public void setAnterior(JFrame anterior) {
        this.anterior = anterior;
    }

    public Botao[] getBotao() {
        return botao;
    }

    public void setBotao(Botao[] botao) {
        this.botao = botao;
    }

    public byte getPont() {
        return pont;
    }

    public void setPont(byte pont) {
        this.pont = pont;
    }

    public byte getCount() {
        return count;
    }

    public void setCount(byte count) {
        this.count = count;
    }

    public short getTenta() {
        return tenta;
    }

    public void setTenta(short tenta) {
        this.tenta = tenta;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
    
}
