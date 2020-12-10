/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauricio
 */
public class ReIndexarArchivos extends javax.swing.JPanel {

    /**
     * Creates new form ReIndexarArchivos
     */
    public ReIndexarArchivos() {
        initComponents();
        jComboBox2.removeAllItems();
        Main.campos.forEach((campo) -> {//Extraer Campo clave
            if (!campo.llave) {
                File file = new File(Main.fileName + "\\" + Main.name + campo.getNombre() + "Index.txt");
                if (file.exists()) {
                    jComboBox2.addItem(campo.getNombre());
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        jButton4.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton4.setText("Salir");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(280, 243, 110, 40);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("RE INDEXAR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(135, 169, 160, 23);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox2);
        jComboBox2.setBounds(190, 110, 140, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("CAMPOS DISPONIBLES");
        add(jLabel5);
        jLabel5.setBounds(15, 115, 170, 14);

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("RE INDEXAR INDICES");
        add(jLabel4);
        jLabel4.setBounds(60, 30, 290, 30);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(-6, -6, 420, 320);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);
    }//GEN-LAST:event_jButton4MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        try {                                      
            int size = jComboBox2.getItemCount();
            
            if (size == 0) {
                JOptionPane.showMessageDialog(null, "¡Campo seleccionado Invalido!", "Archivos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Files.deleteIfExists(Paths.get(Main.fileName + "\\" + Main.name + jComboBox2.getSelectedItem() + "Index.txt"));
            
            String x = String.valueOf(jComboBox2.getSelectedItem());
            
            try {
                Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + x + "Index.txt", "rw");
                Main.index.CrearIndex(x);
                JOptionPane.showMessageDialog(null, "¡Indice Actualizado correctamente!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
                MenuRegistros mr = new MenuRegistros();
                Main.frame.Panel(mr);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CrearIndices.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CrearIndices.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ReIndexarArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
