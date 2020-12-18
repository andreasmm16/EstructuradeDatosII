package ProyectoFileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class ModificarRegistros extends javax.swing.JPanel {

    int posicionRegistro = 0;

    DefaultTableModel model;
    int campos = 0;
    ArrayList<String> fields = new ArrayList<String>();
    ArrayList<Campo> campos_archivo = new ArrayList<Campo>();
    ArrayList<String> modificacion_previa = new ArrayList<String>();
    int Llave = 0;

    public ModificarRegistros() throws IOException {
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
            Campo n = new Campo();
            String name_field = Main.file.readUTF();
            model.addColumn(name_field);
            Main.file.readChar();
            String type_field = Main.file.readUTF();
            fields.add(type_field);
            Main.file.readChar();
            int size = Main.file.readInt();
            Main.file.readChar();
            boolean isKey = Main.file.readBoolean();
            n.setNombre(name_field);
            n.setSize(size);
            n.setTipo(type_field);
            n.setLlave(isKey);
            if (isKey) {
                Llave = campos;
            }
            campos_archivo.add(n);
            Main.file.readChar();
            campos++;
        }

        int x = 0;
        while (x < Main.cantRegis) {
            boolean esRegistro = true;

            Object[] row = new Object[campos];
            for (int i = 0; i < campos; i++) {

                if (fields.get(i).equals("char")) {
                    String value = Main.file.readUTF();
                    row[i] = trim(value);
                    if (row[i].toString().charAt(0) == '*') {
                        esRegistro = false;
                    }
                } else {
                    row[i] = (Integer) Main.file.readInt();
                    if (row[i].toString().equals("-999")) {
                        esRegistro = false;
                    }
                }
            }
            if (esRegistro) {
                model.addRow(row);

            }
            x++;
        }
        System.out.println("Campos" + fields.toArray());
        this.jTable1.setModel(model);
        campos_archivo.forEach((campo) -> {
            if (campo.llave) {
                try {
                    Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + campo.getNombre() + "Index.txt", "rw");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ModificarRegistros.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        JTextField f = new JTextField();
        f.setEditable(false);
        jTable1.getColumnModel().getColumn(Llave).setCellEditor(new DefaultCellEditor(f));
    }

    public void limpiarTabla(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int rows = tabla.getRowCount();

        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }
        tabla.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modificar Registros");
        jLabel1.setToolTipText("");
        add(jLabel1);
        jLabel1.setBounds(60, 10, 280, 40);

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 370, 190);

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
        jButton1.setBounds(280, 260, 110, 40);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("MODIFICAR");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        add(jButton4);
        jButton4.setBounds(20, 260, 120, 30);

        jButton2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(280, 260, 110, 40);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(0, 0, 410, 310);
    }// </editor-fold>//GEN-END:initComponents
      public static String trim(String str) {
        int len = str.length();
        int st = 0;

        char[] val = str.toCharArray();

        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        return str.substring(st, len);
    }
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        try {
            Main.index.cargarIndices();
            campos_archivo.forEach((campo) -> {
                if (campo.llave) {
                    posicionRegistro = Main.index.getPunteroRegistro(campo.valor);
                }
            });
            Main.file.seek(posicionRegistro); //Me posiciono en el puntero del registro a modificar
            for (int i = 0; i < campos_archivo.size(); i++) { // Modificar Registros
                int row = jTable1.getSelectedRow();
                String header = jTable1.getColumnName(i);
                String value = jTable1.getModel().getValueAt(row, i).toString();
                if (campos_archivo.get(i).tipo.equals("char")) {
                    String tmp = value;
                    String reempl = "";
                    char valor[] = new char[campos_archivo.get(i).size];
                    for (int w = 0; w < campos_archivo.get(i).size - 1; w++) {
                        if (w < tmp.length()) {
                            valor[w] = tmp.charAt(w);
                        } else {
                            valor[w] = ' ';
                        }
                    }
                    reempl = String.valueOf(valor);
                    Main.file.writeUTF(reempl);
//                    if (campos_archivo.get(i).llave) {
//                            Main.index.ModificarRegistro(campos_archivo.get(i).valor,campos_archivo.get(i).getNombre(), reempl);
//                    }
                } else {
//                    if (campos_archivo.get(i).llave) {
//                        Main.index.ModificarRegistro(campos_archivo.get(i).valor,campos_archivo.get(i).getNombre(), value);
//                    }

                    Main.file.writeInt(Integer.parseInt(value));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ModificarRegistros.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "¡Campos Modificados!", "Archivos", JOptionPane.INFORMATION_MESSAGE);

//   

    }//GEN-LAST:event_jButton4MouseClicked
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        JTable source = (JTable) evt.getSource();
        String s = "";
        int row = source.rowAtPoint(evt.getPoint());
        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            s = source.getModel().getValueAt(row, i).toString();
            campos_archivo.get(i).valor = s;
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        InputMap iMap1
                = jTable1.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        KeyStroke stroke = KeyStroke.getKeyStroke("ENTER");
        iMap1.put(stroke, "none");
    }//GEN-LAST:event_jTable1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
