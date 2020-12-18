/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class MenuPrincipal extends javax.swing.JPanel {

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        archivoButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();
        camposButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jLabel2.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Standard File Manager");
        add(jLabel2);
        jLabel2.setBounds(70, 30, 270, 40);

        archivoButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        archivoButton.setText("Archivos");
        archivoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivoButtonMouseClicked(evt);
            }
        });
        archivoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                archivoButtonActionPerformed(evt);
            }
        });
        add(archivoButton);
        archivoButton.setBounds(120, 80, 170, 30);

        salirButton.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        salirButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        salirButton.setText("Salir");
        salirButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salirButtonMouseClicked(evt);
            }
        });
        add(salirButton);
        salirButton.setBounds(290, 260, 110, 40);

        camposButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        camposButton.setText("Campos");
        camposButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                camposButtonMouseClicked(evt);
            }
        });
        add(camposButton);
        camposButton.setBounds(120, 120, 170, 30);

        jButton4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton4.setText("Registros");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(120, 160, 170, 30);

        jButton5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton5.setText("Utilidades");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        add(jButton5);
        jButton5.setBounds(120, 200, 170, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 410, 310);
    }// </editor-fold>//GEN-END:initComponents

    private void archivoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivoButtonMouseClicked
       MenuArchivos ma = new MenuArchivos();
        Main.frame.Panel(ma);
        
       

    }//GEN-LAST:event_archivoButtonMouseClicked

    private void salirButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salirButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_salirButtonMouseClicked

    private void camposButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_camposButtonMouseClicked
       MenuCampos mc = new MenuCampos();
       Main.frame.Panel(mc);
    }//GEN-LAST:event_camposButtonMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        if(Main.open){
         MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);   
        }else{
            JOptionPane.showMessageDialog(null, "¡No existe archivo abierto!", "Archivos", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton4MouseClicked

    private void archivoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_archivoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_archivoButtonActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        convertidor_panel mu = new convertidor_panel();
        Main.frame.Panel(mu);
    }//GEN-LAST:event_jButton5MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton archivoButton;
    private javax.swing.JButton camposButton;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton salirButton;
    // End of variables declaration//GEN-END:variables

}
