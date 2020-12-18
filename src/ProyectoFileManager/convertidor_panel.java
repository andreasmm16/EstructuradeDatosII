package ProyectoFileManager;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class convertidor_panel extends javax.swing.JPanel {

    /**
     * Creates new form convertidor_panel
     */
    public convertidor_panel() {
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

        RadioGroup = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        abrir_archivo = new javax.swing.JButton();
        nombre_archivo = new javax.swing.JLabel();
        guardar_como = new javax.swing.JButton();
        XML_Radio = new javax.swing.JRadioButton();
        CSV_Radio = new javax.swing.JRadioButton();

        setLayout(null);

        jLabel3.setForeground(new java.awt.Color(110, 110, 110));
        jLabel3.setText("Archivo a Convertir: ");
        jLabel3.setAutoscrolls(true);
        jLabel3.setFocusable(false);
        jLabel3.setRequestFocusEnabled(false);
        add(jLabel3);
        jLabel3.setBounds(140, 90, 130, 30);

        abrir_archivo.setText("Abrir Archivo");
        abrir_archivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrir_archivoMouseClicked(evt);
            }
        });
        add(abrir_archivo);
        abrir_archivo.setBounds(130, 160, 140, 29);

        nombre_archivo.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        nombre_archivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre_archivo.setText("'Sin Seleccion'");
        nombre_archivo.setAutoscrolls(true);
        nombre_archivo.setFocusable(false);
        nombre_archivo.setRequestFocusEnabled(false);
        add(nombre_archivo);
        nombre_archivo.setBounds(10, 120, 380, 30);

        guardar_como.setText("Guardar como");
        guardar_como.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardar_comoMouseClicked(evt);
            }
        });
        add(guardar_como);
        guardar_como.setBounds(140, 230, 120, 29);

        RadioGroup.add(XML_Radio);
        XML_Radio.setText("XML");
        add(XML_Radio);
        XML_Radio.setBounds(210, 60, 90, 23);

        RadioGroup.add(CSV_Radio);
        CSV_Radio.setSelected(true);
        CSV_Radio.setText("CSV");
        add(CSV_Radio);
        CSV_Radio.setBounds(120, 60, 70, 23);
    }// </editor-fold>//GEN-END:initComponents

    private void abrir_archivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrir_archivoMouseClicked
        JFileChooser fc = new JFileChooser();
        if(nombre_archivo.getText() != "\'Sin Seleccion\'")
            fc.setCurrentDirectory(new java.io.File(nombre_archivo.getText()).getParentFile());
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
                FILE_TYPE tipo_archivo = (RadioGroup.getSelection() == XML_Radio.getModel()) ? FILE_TYPE.XML : FILE_TYPE.CSV;
                XML_converter.convert(nombre_archivo.getText(), fc.getSelectedFile().getPath(), tipo_archivo);
            }catch(Exception e){
                
            }
        
        }
    }//GEN-LAST:event_guardar_comoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CSV_Radio;
    private javax.swing.ButtonGroup RadioGroup;
    private javax.swing.JRadioButton XML_Radio;
    private javax.swing.JButton abrir_archivo;
    private javax.swing.JButton guardar_como;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel nombre_archivo;
    // End of variables declaration//GEN-END:variables
}
