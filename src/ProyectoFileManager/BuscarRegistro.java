/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;

public class BuscarRegistro extends javax.swing.JPanel {

    String nombre_campo;
    String tipo;
    int size;

    public BuscarRegistro() throws IOException {
        initComponents();
        // Main.index.getIndex(); //Carga a memoria todos los registros de campo llave al Arbol
        jComboBox1.removeAllItems();
        Main.campos.forEach((campo) -> {//Extraer Campo clave
            jComboBox1.addItem(campo.getNombre());
            if (campo.llave) {
                nombre_campo = campo.getNombre();

                size = campo.getSize();
                tipo = campo.getTipo();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        textfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Campo = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Buscar Registro");
        add(jLabel1);
        jLabel1.setBounds(90, 20, 230, 50);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(190, 180, 100, 23);

        jButton2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.setToolTipText("");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(270, 250, 120, 40);
        add(textfield);
        textfield.setBounds(170, 150, 140, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("INGRESE TEXTO");
        add(jLabel2);
        jLabel2.setBounds(40, 150, 110, 30);

        Campo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Campo.setText("BUSCAR POR CAMPO");
        add(Campo);
        Campo.setBounds(30, 100, 230, 14);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox1);
        jComboBox1.setBounds(170, 100, 150, 20);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(0, 0, 410, 310);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
    String reempl = "";
    boolean camp = false;

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            String x = String.valueOf(jComboBox1.getSelectedItem());
            camp = false;
            File file = new File(Main.fileName + "\\" + Main.name + x + "Index.txt");
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "¡No Existe Ningun Indice Previamente Creado Con Este Campo !", "Busqueda", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + x + "Index.txt", "rw");
            Main.index.cargarIndices();
            //Main.index.getIndex();
            int size = 0;
            Main.campos.forEach((campo) -> {
                if (campo.getNombre().equals(x) && !campo.getTipo().equals("int")) {
                    String tmp = textfield.getText();
                    char[] ch = new char[campo.getSize()];
                    for (int w = 0; w < campo.getSize() - 1; w++) {
                        if (w < tmp.length()) {
                            ch[w] = tmp.charAt(w);
                        } else {
                            ch[w] = ' ';
                        }
                    }
                    reempl = String.valueOf(ch);
                    camp = true;
                }
            });

            int posRegistro = 0;
            if (camp) {
                posRegistro = Main.index.getPunteroRegistro(reempl); //Buscar el Campo llave en el Arbol B
            } else {
                posRegistro = Main.index.getPunteroRegistro(textfield.getText());
            }
            if (posRegistro != -1) {
                Object[] registro = Main.index.getRegistroCompleto(posRegistro);
                if (registro != null) {
                    String data = "\n";
                    for (int i = 0; i < Main.campos.size(); i++) {
                        data += "Campo: " + Main.campos.get(i).nombre + " Tamano: " + Main.campos.get(i).size + " Tipo " + Main.campos.get(i).tipo + " Valor :" + registro[i].toString() + "\n\n";
                    }
                    JOptionPane.showMessageDialog(null, "¡Registro Encontrado Exitosamente!\n" + data, "Busqueda", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro No Encontrado !", "Busqueda", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            Logger.getLogger(BuscarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Campo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textfield;
    // End of variables declaration//GEN-END:variables
}
