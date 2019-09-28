/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Vinicius
 */
public class Escuta implements ActionListener{
    private Botao b1,b2;
    public Escuta(Botao b1, Botao b2){
        this.b1 = b1;
        this.b2 = b2;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    public Botao getB1() {
        return b1;
    }

    public void setB1(Botao b1) {
        this.b1 = b1;
    }

    public Botao getB2() {
        return b2;
    }

    public void setB2(Botao b2) {
        this.b2 = b2;
    }

  
   

  
    
    
    
}
