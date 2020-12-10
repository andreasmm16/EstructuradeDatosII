package ProyectoFileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CrearIndices extends javax.swing.JPanel {

    String nombre_campo;
    String tipo;
    int size;

    public CrearIndices() {
        initComponents();
        jComboBox1.removeAllItems();
        Main.campos.forEach((campo) -> {//Extraer Campo clave
            if (campo.llave) {
                nombre_campo = campo.getNombre();
                size = campo.getSize();
                tipo = campo.getTipo();
            } else {
                File file = new File(Main.fileName + "\\" + Main.name + campo.getNombre() + "Index.txt");
                if (!file.exists()) {
                    jComboBox1.addItem(campo.getNombre());
                }

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CREAR INDICES");
        add(jLabel1);
        jLabel1.setBounds(90, 20, 220, 40);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(jComboBox1);
        jComboBox1.setBounds(200, 120, 140, 20);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("CREAR INDICE");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(140, 180, 160, 23);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("CAMPOS DISPONIBLES");
        add(jLabel2);
        jLabel2.setBounds(20, 120, 170, 20);

        jButton3.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        add(jButton3);
        jButton3.setBounds(280, 250, 110, 40);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(-6, -6, 420, 320);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        int size = jComboBox1.getItemCount();

        if (size == 0) {
            JOptionPane.showMessageDialog(null, "¡Campo seleccionado Invalido!", "Archivos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String x = String.valueOf(jComboBox1.getSelectedItem());

        try {
            Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + x + "Index.txt", "rw");
            Main.index.CrearIndex(x);
            JOptionPane.showMessageDialog(null, "¡Indice Creado correctamente!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
            MenuRegistros mr = new MenuRegistros();
            Main.frame.Panel(mr);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrearIndices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrearIndices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);
    }//GEN-LAST:event_jButton3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
