/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class TablaCampos extends javax.swing.JPanel {

    DefaultTableModel model;

    public TablaCampos() {
        initComponents();

        limpiarTabla(jTable1);
        model = new DefaultTableModel();
        model.addColumn("Nombre");
        model.addColumn("Tipo");
        model.addColumn("Tamaño");
        model.addColumn("Llave");

        if (Main.tipoCam == 1) {
            botonMod.setVisible(false);
            borrarButton.setVisible(false);
            for (Campo c : Main.campos) {
                Object[] row = {c.getNombre(), c.getTipo(), c.getSize(), c.getLlave()};
                model.addRow(row);
            }

            this.jTable1.setModel(model);
        }
        if (Main.tipoCam == 2) {
            limpiarTabla(jTable1);
            botonMod.setVisible(true);
            borrarButton.setVisible(false);
            for (Campo c : Main.campos) {
                Object[] row = {c.getNombre(), c.getTipo(), c.getSize(), c.getLlave()};
                model.addRow(row);
            }

            this.jTable1.setModel(model);
        }
        if (Main.tipoCam == 3) {
            limpiarTabla(jTable1);
            botonMod.setVisible(false);
            borrarButton.setVisible(true);
            for (Campo c : Main.campos) {
                Object[] row = {c.getNombre(), c.getTipo(), c.getSize(), c.getLlave()};
                model.addRow(row);
            }

            this.jTable1.setModel(model);
        }
    }

    public void limpiarTabla(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int rows = tabla.getRowCount();

        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }

        tabla.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        botonMod = new javax.swing.JButton();
        borrarButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setLayout(null);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 70, 370, 160);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/document.png"))); // NOI18N
        jLabel1.setText("CAMPOS");
        add(jLabel1);
        jLabel1.setBounds(120, 10, 170, 50);

        jButton1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(300, 260, 100, 40);

        botonMod.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonMod.setText("Modificar");
        botonMod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonModMouseClicked(evt);
            }
        });
        botonMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonModKeyPressed(evt);
            }
        });
        add(botonMod);
        botonMod.setBounds(140, 240, 120, 30);

        borrarButton.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        borrarButton.setText("Borrar");
        borrarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrarButtonMouseClicked(evt);
            }
        });
        add(borrarButton);
        borrarButton.setBounds(130, 240, 140, 30);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(0, 0, 410, 310);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        MenuCampos mc = new MenuCampos();
        Main.frame.Panel(mc);
    }//GEN-LAST:event_jButton1MouseClicked

    private void botonModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonModKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonModKeyPressed

    private void botonModMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonModMouseClicked

        for (int i = 0; i < Main.campos.size(); i++) {
            Main.campos.get(i).setNombre((String) model.getValueAt(i, 0));
            Main.campos.get(i).setTipo((String) model.getValueAt(i, 1));
            Main.campos.get(i).setSize(Integer.parseInt(model.getValueAt(i, 2).toString()));
            Main.campos.get(i).setLlave((boolean) model.getValueAt(i, 3));
        }
        JOptionPane.showMessageDialog(null, "¡Campos Modificados!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
        MenuCampos mc = new MenuCampos();
        Main.frame.Panel(mc);

    }//GEN-LAST:event_botonModMouseClicked

    private void borrarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrarButtonMouseClicked
        if (jTable1.getSelectedRow() <= 0) {
            JOptionPane.showMessageDialog(null, "¡Seleccione un campo!", "Archivos", JOptionPane.ERROR_MESSAGE);
        } else {
            Main.campos.remove(jTable1.getSelectedRow());
            model.removeRow(jTable1.getSelectedRow());
            JOptionPane.showMessageDialog(null, "¡Campos Borrados!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_borrarButtonMouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrarButton;
    private javax.swing.JButton botonMod;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}