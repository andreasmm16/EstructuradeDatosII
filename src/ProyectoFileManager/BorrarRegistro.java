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
        Main.index.getIndex(); //Carga a memoria todos los registros de campo llave al Arbol

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

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Borrar Registro");
        add(jLabel1);
        jLabel1.setBounds(90, 20, 240, 40);

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
        jButton1.setBounds(100, 210, 100, 23);

        jButton2.setText("ATRAS");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(200, 210, 100, 23);
        add(textfield);
        textfield.setBounds(150, 140, 120, 20);

        jLabel3.setText("INGRESE TEXTO");
        add(jLabel3);
        jLabel3.setBounds(50, 140, 110, 30);

        Campo.setText("Campo Clave : ");
        add(Campo);
        Campo.setBounds(110, 90, 230, 14);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
           
            if (Main.index.borrarRegistro(textfield.getText(), nombre_campo)) {
                
                Main.index.getIndex();

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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField textfield;
    // End of variables declaration//GEN-END:variables
}
