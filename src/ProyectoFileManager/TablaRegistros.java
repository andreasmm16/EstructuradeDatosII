/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class TablaRegistros extends javax.swing.JPanel {

    DefaultTableModel model;
    int campos = 0;
    ArrayList<String> fields = new ArrayList<String>();

    /**
     * Creates new form TablaRegistros
     */
    public TablaRegistros() throws IOException {
        initComponents();
        limpiarTabla(jTable1);
        model = new DefaultTableModel();
        campos = 0;
        fields.clear();
        Main.file.seek(0);
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readLong();
        while (Main.file.getFilePointer() < Main.structure) {
            String x = Main.file.readUTF();
            model.addColumn(x);
            Main.file.readChar();
            String ss = Main.file.readUTF();
            fields.add(ss);
            Main.file.readChar();
            Main.file.readInt();
            Main.file.readChar();
            boolean value = Main.file.readBoolean();
            Main.file.readChar();
            campos++;
        }

        int x = 0;
        while (x < Main.cantRegis) {
            boolean esRegistro = true;
            Object[] row = new Object[campos];
            for (int i = 0; i < campos; i++) {

                if (fields.get(i).equals("char")) {
                    row[i] = Main.file.readUTF();
                    if (row[i].toString().charAt(0) == '-' && row[i].toString().charAt(1) == '1') {
                        esRegistro = false;
                    }

                } else {
                    row[i] = (Integer) Main.file.readInt();
                    if (row[i].toString().equals("-1")) {
                        esRegistro = false;
                    }
                }
            }
            if (esRegistro) {
                model.addRow(row);
            }
            x++;
        }
        this.jTable1.setModel(model);

    }
    //Object[] row = {c.getNombre(),c.getTipo(),c.getSize(),c.getLlave()};
    //          model.addRow(row);

    /* Main.file.seek(Main.file.length());
                    while (x < fc) {
                        if (fields.get(cont).equals("char")) {
                            Main.file.writeUTF((String) model.getValueAt(0, x));
                        } else {
                            Main.file.writeInt(Integer.parseInt(model.getValueAt(0, x).toString()));
                        }
                        cont++;
                        x = x + 2;
                    }*/
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 370, 200);

        jButton1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton1.setText("Salir");
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
        jButton1.setBounds(290, 260, 110, 40);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/document.png"))); // NOI18N
        jLabel1.setText("Registros");
        add(jLabel1);
        jLabel1.setBounds(130, 10, 170, 33);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(-6, -6, 420, 320);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);

    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
