package ProyectoFileManager;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego
 */
public class Cruce extends javax.swing.JFrame {

    /**
     * Creates new form Cruce
     */
    public Cruce() {
        initComponents();
       setLocationRelativeTo(null);
        this.setSize(new Dimension(415, 340));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campos_scroll = new javax.swing.JScrollPane();
        campos_list = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        archivos_list = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        guardar_como_button = new javax.swing.JButton();
        field = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(null);

        campos_scroll.setViewportView(campos_list);

        getContentPane().add(campos_scroll);
        campos_scroll.setBounds(270, 30, 120, 150);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        archivos_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                archivos_listMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(archivos_list);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(40, 120, 210, 60);

        jLabel1.setForeground(new java.awt.Color(150, 150, 150));
        jLabel1.setText("Archivos a Cruzar:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 100, 130, 14);

        jLabel2.setForeground(new java.awt.Color(150, 150, 150));
        jLabel2.setText("Campos Comunes:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(270, 10, 130, 14);

        guardar_como_button.setText("Guardar Como...");
        guardar_como_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardar_como_buttonMouseClicked(evt);
            }
        });
        getContentPane().add(guardar_como_button);
        guardar_como_button.setBounds(60, 210, 140, 23);
        getContentPane().add(field);
        field.setBounds(30, 30, 210, 20);

        jButton1.setText("Buscar Directorio");
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
        getContentPane().add(jButton1);
        jButton1.setBounds(60, 60, 150, 23);

        jButton2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(270, 250, 120, 40);

        jLabel3.setText("Ingrese dirección de directorio");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 10, 170, 14);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/Fondo.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(-6, -6, 420, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void archivos_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archivos_listMouseClicked
        try{
            ArrayList<String> str1 = new ArrayList();
            for(String l : archivos_list.getSelectedValuesList())
                str1.add(field.getText() + "\\" + l);
            Cruce_archivos.leerArchivos(str1);
            ArrayList<String> str = new ArrayList();
            for(Cruce_archivos.Campo campo : Cruce_archivos.getCamposComunes())
                str.add(campo.nombre);
            campos_list.setListData(new Vector(str));
        }catch(Exception e){}
    }//GEN-LAST:event_archivos_listMouseClicked

    private void guardar_como_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardar_como_buttonMouseClicked
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int return_val = fc.showSaveDialog(this);     
        if(return_val == JFileChooser.APPROVE_OPTION){
        try{
            String str1[] = new String[campos_list.getSelectedValuesList().size()];
            for(int i = 0; i < campos_list.getSelectedValuesList().size(); i++)
                str1[i] = campos_list.getSelectedValuesList().get(i);
            Cruce_archivos.cruzar(str1, fc.getSelectedFile().getPath());
            ArrayList<String> str2 = new ArrayList();
            for(String l : archivos_list.getSelectedValuesList())
                str2.add(field.getText() + "\\" + l);
            Cruce_archivos.leerArchivos(str2);
             JOptionPane.showMessageDialog(null, "¡Cruce Realizado!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){}
        
        }
    }//GEN-LAST:event_guardar_como_buttonMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        File dir = new File(field.getText());
        ArrayList<String> list = new ArrayList();
        for(String file : dir.list())
            if(file.contains(".txt"))
                list.add(file);
        archivos_list.setListData(new Vector(list));
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Main.frame.setVisible(true);
       Main.frame2.setVisible(false);
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cruce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cruce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cruce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cruce.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cruce().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> archivos_list;
    private javax.swing.JList<String> campos_list;
    private javax.swing.JScrollPane campos_scroll;
    private javax.swing.JTextField field;
    private javax.swing.JButton guardar_como_button;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
