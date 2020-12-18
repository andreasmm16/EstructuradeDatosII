package ProyectoFileManager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BorrarRegistro extends javax.swing.JPanel {

    String nombre_campo;
    String tipo;
    int size;

    public BorrarRegistro() throws IOException {
        initComponents();

        Main.campos.forEach((campo) -> {//Extraer Campo clave
            if (campo.llave) {
                nombre_campo = campo.getNombre();
                size = campo.getSize();
                tipo = campo.getTipo();
            }
        });
        Campo.setText("CAMPO CLAVE: " + nombre_campo + " Tamano: " + size + " Tipo: " + tipo);
        Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + nombre_campo + "Index.txt", "rw");
        Main.index.cargarIndices();//Carga a memoria todos los registros de campo llave al Arbol

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        textfield = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Campo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Borrar Registro");
        add(jLabel1);
        jLabel1.setBounds(90, 20, 240, 40);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("BORRAR");
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
        jButton1.setBounds(190, 170, 100, 30);

        jButton2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(280, 250, 110, 40);
        add(textfield);
        textfield.setBounds(150, 140, 180, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("INGRESE TEXTO");
        add(jLabel3);
        jLabel3.setBounds(40, 140, 110, 20);

        Campo.setText("Campo Clave : ");
        add(Campo);
        Campo.setBounds(60, 90, 290, 20);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(0, 0, 410, 310);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {

            if (Main.index.borrarRegistro(textfield.getText(), nombre_campo)) {
                JOptionPane.showMessageDialog(null, "¡Registro Borrado Exitosamente!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡Registro No Encontrado!", "Archivos", JOptionPane.ERROR_MESSAGE);
            }
         } catch (IOException ex) {
            Logger.getLogger(BorrarRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);
    }//GEN-LAST:event_jButton2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Campo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textfield;
    // End of variables declaration//GEN-END:variables
}
