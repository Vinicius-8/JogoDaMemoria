package jogo.memoria;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import classes.Botao;
import classes.Dados;
import classes.Escuta;
import classes.Pontos;
import classes.Save;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;



/**
 *
 * @author aluno
 */
public class Tela_Principal extends javax.swing.JFrame {
    JFrame anterior;
    Recordes itf;
    ComoJogar howToPlay;
    JPanel jp;
     
    private Botao b1,b2;
    private byte pont;
    private byte count;
    private short tenta;
    private String nivel;
    private boolean isReady;
    private Botao[] cards;
    private final byte CARTAS = 36;
    
    
    //Falta a documentação e o manuel
    private ArrayList<Pontos> records;
    public Tela_Principal(JFrame jf, String nivel, boolean go, ArrayList<Pontos> recordis) {
        boolean goSave = go;
        this.isReady = true;
        this.nivel = nivel;
        this.records = recordis;
        
        
        design();
        
        
        initComponents();
        this.anterior = jf; //frame anterior
        this.setIconImage(new ImageIcon("src\\_icones\\aang_icon.jpg").getImage()); //icone
        
        
        this.itf = (Recordes)new Recordes(records);// frame de recordes 
        this.howToPlay = new ComoJogar();
        this.jp = new JPanel(); //criação do painel
        
        jp.setSize(new Dimension(1000, 700));// dimensões do painel
        jp.setVisible(true);// visibilidade do painel
        
        
        this.getContentPane().add(itf); // adição do frame de recordes
        this.getContentPane().add(howToPlay);
        this.getContentPane().add(jp); // adição do painel
       
        nivelLabel.setText(nivel);
        
        
        Save s = new Save();
        if (s.exists("D:/save.txt") && goSave) {
            Dados d = (Dados) s.recordar();
            recordando(d);
        }else{
            confBot();//configuração dos botões 
        }
    }
    
    private void recordando(Dados d){
      this.anterior = d.getAnterior();
      this.cards = d.getBotao();
      this.count = d.getCount();
      this.nivel = d.getNivel();
      this.pont = d.getPont();
      this.tenta = d.getTenta();
      this.records = d.getRecords();
      this.pontuacao.setText(pont+"");
      this.tentativas.setText(tenta + "");
      this.nivelLabel.setText(nivel+"");
      
        for (Botao card : cards) {
            card.setBorder(null);
            card.addActionListener((ActionEvent e) -> {
                if (isReady) {
                    Botao b = (Botao) e.getSource();
                    if (b1 == null) {
                        b1 = b;
                        b1.whatMemory();
                    } else if (b1.hashCode() != b.hashCode()) {
                        b2 = b;
                        b2.whatMemory();
                        isReady = false;
                        comp();
                        
                    }

                }
            });
            jp.add(card);
        }
    }
    
    private void design(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Memoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private byte pontNivel(String nivel, byte pontuacao) {
        
        if(nivel.equals("Fácil"))
            pontuacao -= 2;
        else if(nivel.equals("Médio"))
            pontuacao -= 3;
        else if(nivel.equals("Difícil"))
            pontuacao -= 5;
        
        return pontuacao;
    }
      
    private void confBot(){
        this.cards = new Botao[CARTAS]; // vetor de botões
        
        File []m = new File("src\\imagens").listFiles(); //lista com todos as cartas
        ArrayList<String> em = new ArrayList<>(); // de embaralhamento
        
         for (File m1 : m) {
             //adicionando ao arraylist de embaralhamento
             em.add(m1.getPath());
         }
        
        Collections.shuffle(em);
        
        //-
        ArrayList<String> memorias = new ArrayList<>(); //arraylist com o caminho das cartas
     
        for (int i = 0; i < (cards.length)/2; i++) {
            memorias.add(em.get(i));
            memorias.add(em.get(i));
        }
        
        em.clear();
        
        Collections.shuffle(memorias);
        
        String back = "src\\verso\\beifong.jpg"; //verso da carta        
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Botao(back);            
            cards[i].setMemoria(memorias.get(i));
            cards[i].setVisible(true);   
            cards[i].setBorder(null); 
            cards[i].addActionListener((ActionEvent e) -> {
                if (isReady) {
                    Botao b = (Botao) e.getSource();
                    if (b1 == null) {
                        b1 = b;
                        b1.whatMemory();
                    } else if (b1.hashCode() != b.hashCode()) {
                        b2 = b;
                        b2.whatMemory();
                        isReady = false;
                        comp();
                    }
                }
            });
            
            
            jp.add(cards[i]);
            
        }
    }
    
    private void comp(){
        
      Timer ts = new Timer(1300, new Escuta(b1, b2) {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (getB1().getMemoria().equals(getB2().getMemoria())) {
                    pont+=10;
                    pontuacao.setText(pont + "");
                    getB1().setEnabled(false);
                    getB2().setEnabled(false);
                    count++;
                } else {
                    pont = pontNivel(nivel, pont);
                    pontuacao.setText(pont + "");
                    getB1().backing();
                    getB2().backing();
                }
                isReady = true;
                fim();
            }
        });
        ts.setRepeats(false);
        ts.start();
        b1 = b2 = null;
        tentativas.setText(++tenta+"");
    }
    
    private void fim(){
        if (count == (cards.length)/2) {
            String nome = JOptionPane.showInputDialog("Fim de Jogo, sua pontuação foi "+pont+". Digite seu nome: ");
            records.add(new Pontos(pont,nome));

            if (JOptionPane.showConfirmDialog(null, "Deseja reiniciar?") == 0) {
                this.dispose();
                new Tela_Principal(this.anterior, this.nivel,false,this.records).setVisible(true);
            }
        }
       
    }
  
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pontuacao = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tentativas = new javax.swing.JLabel();
        nivelLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aang's Memory");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Nível:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Pontuação:");

        pontuacao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        pontuacao.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Tentativas:");

        tentativas.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tentativas.setText("0");

        nivelLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nivelLabel.setText("Nivel");

        jMenu1.setText("Opções");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_icones/restart.png"))); // NOI18N
        jMenuItem1.setText("Reiniciar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_icones/save.png"))); // NOI18N
        jMenuItem2.setText("Salvar Jogo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_icones/trophy.png"))); // NOI18N
        jMenuItem3.setText("Recordes");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_icones/exit.png"))); // NOI18N
        jMenuItem4.setText("Sair");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ajuda");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/_icones/how_to_play.png"))); // NOI18N
        jMenuItem5.setText("Como jogar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1019, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nivelLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pontuacao))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tentativas)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nivelLabel))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pontuacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tentativas))
                .addContainerGap(371, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1216, 618));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    
    //item 1 de menu
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         if (JOptionPane.showConfirmDialog(null,"Deseja realmente reiniciar o jogo?") == 0) {
             this.dispose();
             new Tela_Principal(this.anterior, nivel,false,this.records).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed
   ///-----------------
    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed
    //item 4 de menu
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (JOptionPane.showConfirmDialog(null,"Deseja realmente fechar o jogo?") == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    //item 2 de menu
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            
            new Save().salvar("D:/save.txt"  ,   new Dados(cards, pont, count, tenta, nivel,anterior,itf.getRecordes()));
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Tela_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Botao card : cards) {
            card.setBorder(null);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    //item 3 de menu
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.itf.setRecordes(records);
        this.itf.setVisible(true);
        this.itf.pontua();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        this.howToPlay.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JLabel nivelLabel;
    private javax.swing.JLabel pontuacao;
    private javax.swing.JLabel tentativas;
    // End of variables declaration//GEN-END:variables
}
