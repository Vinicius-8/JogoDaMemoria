package jogo.memoria;


import classes.Save;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Memoria extends javax.swing.JFrame {
    
    private String nivel = "Fácil";

    public Memoria() {
        this.setResizable(false);
        this.setTitle("Jogo Da Memória");
        this.setIconImage(new ImageIcon("src\\_icones\\aang_icon.jpg").getImage());
        initComponents();
        continuar.setEnabled(false);
        
        if (new Save().exists("D:/save.txt")) {
            continuar.setEnabled(true);
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        continuar = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jogar = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("MoolBoran", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Jogo da Memória");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 10, 420, 70);

        continuar.setFont(new java.awt.Font("MoolBoran", 0, 36)); // NOI18N
        continuar.setText("Continuar");
        continuar.setAlignmentX(5.0F);
        continuar.setAlignmentY(1.0F);
        continuar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        continuar.setMargin(new java.awt.Insets(15, 14, 2, 14));
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });
        getContentPane().add(continuar);
        continuar.setBounds(70, 240, 270, 70);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fácil", "Médio", "Difícil" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2);
        jComboBox2.setBounds(180, 100, 110, 26);

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        jLabel2.setText("Nível: ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 100, 50, 30);

        jogar.setFont(new java.awt.Font("MoolBoran", 0, 36)); // NOI18N
        jogar.setText("Jogar");
        jogar.setAlignmentX(5.0F);
        jogar.setAlignmentY(1.0F);
        jogar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jogar.setMargin(new java.awt.Insets(15, 14, 2, 14));
        jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarActionPerformed(evt);
            }
        });
        getContentPane().add(jogar);
        jogar.setBounds(70, 160, 270, 70);

        setSize(new java.awt.Dimension(433, 366));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed
        new Tela_Principal(this, nivel,true,new ArrayList<>()).setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_continuarActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
           nivel = jComboBox2.getSelectedItem()+"";
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarActionPerformed
        try{
            new Tela_Principal(this, nivel,false,new ArrayList<>()).setVisible(true);
            this.setVisible(false);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jogarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Memoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Memoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Memoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Memoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Memoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continuar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton jogar;
    // End of variables declaration//GEN-END:variables

   /* private void setIconImage(ImageIcon imageIcon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setIconImage(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}