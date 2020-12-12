package ProyectoFileManager;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author diego
 */
public class Convertidor_XML extends javax.swing.JPanel {

    /**
     * Creates new form Convertidor_XML
     */
    
    public Convertidor_XML() {
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

        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        abrir_archivo = new javax.swing.JButton();
        nombre_archivo = new javax.swing.JLabel();
        guardar_como = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setLayout(null);

        jLabel2.setForeground(new java.awt.Color(110, 110, 110));
        jLabel2.setText("Archivo a Convertir: ");
        jLabel2.setAutoscrolls(true);
        jLabel2.setFocusable(false);
        jLabel2.setRequestFocusEnabled(false);
        add(jLabel2);
        jLabel2.setBounds(140, 90, 130, 30);

        abrir_archivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        abrir_archivo.setText("Abrir Archivo");
        abrir_archivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrir_archivoMouseClicked(evt);
            }
        });
        add(abrir_archivo);
        abrir_archivo.setBounds(130, 160, 140, 23);

        nombre_archivo.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        nombre_archivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre_archivo.setText("'Sin Seleccion'");
        nombre_archivo.setAutoscrolls(true);
        nombre_archivo.setFocusable(false);
        nombre_archivo.setRequestFocusEnabled(false);
        add(nombre_archivo);
        nombre_archivo.setBounds(10, 120, 380, 30);

        guardar_como.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        guardar_como.setText("Guardar como");
        guardar_como.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardar_comoMouseClicked(evt);
            }
        });
        add(guardar_como);
        guardar_como.setBounds(140, 220, 120, 23);

        jButton1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        add(jButton1);
        jButton1.setBounds(290, 260, 110, 40);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Convertidor XML");
        add(jLabel1);
        jLabel1.setBounds(80, 30, 260, 40);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(-6, -6, 420, 320);
    }// </editor-fold>//GEN-END:initComponents

    private void abrir_archivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrir_archivoMouseClicked
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int return_val = fc.showOpenDialog(this);
        if(return_val == JFileChooser.APPROVE_OPTION){
            nombre_archivo.setText(fc.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_abrir_archivoMouseClicked

    private void guardar_comoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardar_comoMouseClicked
        if(nombre_archivo.getText() == "\'Sin Seleccion\'"){
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int return_val = fc.showSaveDialog(this);
        if(return_val == JFileChooser.APPROVE_OPTION){
            try{
                XML_converter.convert(nombre_archivo.getText(), fc.getSelectedFile().getPath());
                 JOptionPane.showMessageDialog(null, "¡Archivo creado exitosamente!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){

            }

        }
    }//GEN-LAST:event_guardar_comoMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
       MenuUtilidades mu = new MenuUtilidades();
       Main.frame.Panel(mu);
    }//GEN-LAST:event_jButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir_archivo;
    private javax.swing.JButton guardar_como;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel nombre_archivo;
    // End of variables declaration//GEN-END:variables
}
