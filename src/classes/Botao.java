package classes;

import javax.swing.ImageIcon;



/**
 *
 * @author Vinicius
 */
public class Botao extends javax.swing.JButton{
    private String memoria;
    private String verso;

    

    

    public Botao(String verso) {
        super(new ImageIcon(verso));
        this.verso = verso;
    }
    

    public void whatMemory(){
        this.setIcon(new ImageIcon(this.getMemoria()));
    }
    
    public void backing(){
        this.setIcon(new ImageIcon(this.getVerso()));
    }

    public String getVerso() {
        return verso;
    }

    public void setVerso(String verso) {
        this.verso = verso;
    }
  



    
    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    @Override
    public String toString() {
        return memoria;
    }
    
    
}
