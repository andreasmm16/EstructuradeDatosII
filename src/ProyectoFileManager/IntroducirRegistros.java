package ProyectoFileManager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IntroducirRegistros extends javax.swing.JPanel {

    DefaultTableModel model;
    int campos = 0;
    ArrayList<String> fields = new ArrayList<String>();
    ArrayList<String> nombre_Campos = new ArrayList<String>();
    ArrayList<Integer> sizes = new ArrayList<>();
    String tipo;
    String campoClave;
    String tipoCampo;
    int campoSize;
    boolean isKey;

    public IntroducirRegistros() throws IOException {
        initComponents();
        limpiarTabla(jTable1);
        model = new DefaultTableModel();
        tipo = "";
        isKey = false;
        if (Main.cantRegis == 0) {
            fields.clear();
            sizes.clear();
            Main.file.seek(0);
            Main.file.readInt();
            int stotal = 0, cont = 0;
            String ss = "";
            Main.file.readInt();
            Main.file.readInt();
            Main.file.readLong();
            while (Main.file.getFilePointer() < Main.file.length()) {
                String nombre_campo = Main.file.readUTF();
                nombre_Campos.add(nombre_campo);
                Main.file.readChar();
                ss = Main.file.readUTF();
                fields.add(ss);
                Main.file.readChar();
                cont = Main.file.readInt();
                Main.file.readChar();
                Main.file.readBoolean();
                Main.file.readChar();
                if (ss.equals("int")) {
                    stotal += 4;

                } else {

                    stotal += (cont + 2);//mas los dos bytes de fin de cadena del utf
                }
                sizes.add(cont);
                campos++;
            }
            Main.recordSize = stotal;
            long pos = Main.file.getFilePointer();
            Main.file.seek(0);
            Main.file.readInt();
            Main.file.writeInt(Main.recordSize);
            Main.file.readInt();
            Main.file.writeLong(pos);
            Main.structure = pos;
        } else {
            campos = 0;
            fields.clear();
            sizes.clear();
            Main.file.seek(0);
            Main.file.readInt();
            Main.file.readInt();
            Main.file.readInt();
            Main.file.readLong();
            while (Main.file.getFilePointer() < Main.structure) {
                String nombre_campo = Main.file.readUTF();
                nombre_Campos.add(nombre_campo);
                Main.file.readChar();
                String ss = Main.file.readUTF();
                fields.add(ss);
                Main.file.readChar();
                int c = Main.file.readInt();
                Main.file.readChar();
                Main.file.readBoolean();
                Main.file.readChar();
                sizes.add(c);
                campos++;
            }
        }
//de aqui es para las columnas
        Main.file.seek(0);
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readLong();
        while (Main.file.getFilePointer() < Main.structure) {
            model.addColumn(Main.file.readUTF());
            Main.file.readChar();
            Main.file.readUTF();
            Main.file.readChar();
            model.addColumn(String.valueOf(Main.file.readInt()));
            Main.file.readChar();
            Main.file.readBoolean();
            Main.file.readChar();
        }
        System.out.println(Main.cantRegis);
        model.addRow(new Object[]{null});
        this.jTable1.setModel(model);

        Main.file.seek(0);
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readLong();
        while (Main.file.getFilePointer() < Main.structure) {
            String n = Main.file.readUTF();
            Main.file.readChar();
            String t = Main.file.readUTF();
            Main.file.readChar();
            int s = Main.file.readInt();
            Main.file.readChar();
            boolean k = Main.file.readBoolean();
            Main.file.readChar();
            if (n.equals(Main.key)) {
                tipo = n;
            }
        }
        Main.campos.forEach((campo) -> {//Extraer Campo clave
            if (campo.llave) {
                campoClave = campo.getNombre();
                tipoCampo = campo.getTipo();
                campoSize = campo.getSize();
                isKey = campo.getLlave();
            }
        });
    }

    /* for (Campo c : Main.campos) {
                Object[] row = {c.getNombre(),c.getTipo(),c.getSize(),c.getLlave()};
                model.addRow(row);
            }
            
            this.jTable1.setModel(model);}*/
    public void limpiarTabla(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        int rows = tabla.getRowCount();

        for (int i = 0; i < rows; i++) {
            model.removeRow(0);
        }

        tabla.setModel(model);
    }

    /**
     * {
     *
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ingresarButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 30)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Introducir Registros");
        add(jLabel1);
        jLabel1.setBounds(0, 10, 410, 40);

        ingresarButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ingresarButton.setText("Ingresar");
        ingresarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ingresarButtonMouseClicked(evt);
            }
        });
        ingresarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarButtonActionPerformed(evt);
            }
        });
        add(ingresarButton);
        ingresarButton.setBounds(160, 180, 90, 30);

        jButton2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProyectoFileManager/sign-out.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        add(jButton2);
        jButton2.setBounds(290, 260, 110, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 110, 360, 60);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        MenuRegistros mr = new MenuRegistros();
        Main.frame.Panel(mr);
    }//GEN-LAST:event_jButton2MouseClicked

    private void ingresarButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ingresarButtonMouseClicked
        try {

            int fc = campos * 2;
            int x = 0;
            int cont = 0;
            boolean ver = true;
            while (x < fc) {
                if (model.getValueAt(0, x).toString().length() > sizes.get(cont)) {
                    ver = false;
                }
                cont++;
                x = x + 2;
            }
            x = 0;
            cont = 0;

            if (ver) {
                System.out.println(Main.cantRegis);
                Main.file.seek(0);
                Main.cantRegis++;
                Main.file.writeInt(Main.cantRegis);

                // a partir de aquí es el ingreso de el registro al archivo de datos
                if (Main.lista.posDisponible() == -1) {
                    int posicionRegistro = (int) Main.file.length();
                    Main.file.seek(Main.file.length());
                    while (x < fc) {
                        if (fields.get(cont).equals("char")) {
                            String tmp = model.getValueAt(0, x).toString();
                            String reempl = "";
                            char[] ch = new char[sizes.get(cont)];
                            for (int w = 0; w < sizes.get(cont) - 1; w++) {
                                if (w < tmp.length()) {
                                    ch[w] = tmp.charAt(w);
                                } else {
                                    ch[w] = ' ';
                                }
                            }
                            reempl = String.valueOf(ch);

                            if (campoClave.equals(nombre_Campos.get(cont)) && fields.get(cont).equals(tipoCampo) && campoSize == sizes.get(cont) && isKey) {//Evaluar si el campo es llave si lo es ingresar al index file
                                EstructuraIndex index = new EstructuraIndex(reempl, posicionRegistro);
                                Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + reempl + "Index.txt", "rw");
                                index.insertIndex();
                            }
                            
                            Main.file.writeUTF(reempl);
                        } else {
                            if (campoClave.equals(nombre_Campos.get(cont)) && fields.get(cont).equals(tipoCampo) && campoSize == sizes.get(cont) && isKey) {//Evaluar si el campo es llave si lo es ingresar al index file
                                Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + campoClave + "Index.txt", "rw");
                                EstructuraIndex index = new EstructuraIndex(model.getValueAt(0, x).toString(), posicionRegistro);
                                index.insertIndex();
                            }
                            Main.file.writeInt(Integer.parseInt(model.getValueAt(0, x).toString()));
                        }
                        cont++;
                        x = x + 2;
                    }

                    JOptionPane.showMessageDialog(null, "¡Registro Ingresado correctamente!", "Archivos", JOptionPane.INFORMATION_MESSAGE);
                    limpiarTabla(jTable1);
                    model.addRow(new Object[]{null});
                    this.jTable1.setModel(model);
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Datos ingresados no cumplen con tamaño!", "Archivos", JOptionPane.ERROR_MESSAGE);
                model.removeRow(0);
                model.addRow(new Object[]{null});
            }

        } catch (IOException ex) {
            Logger.getLogger(IntroducirRegistros.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_ingresarButtonMouseClicked

    private void ingresarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ingresarButtonActionPerformed
    /*for (int i = 0; i < Main.campos.size(); i++) {
            Main.campos.get(i).setNombre((String)model.getValueAt(i, 0));
            Main.campos.get(i).setTipo((String)model.getValueAt(i, 1));
            Main.campos.get(i).setSize(Integer.parseInt(model.getValueAt(i, 2).toString()));
            Main.campos.get(i).setLlave((boolean)model.getValueAt(i, 3));
        }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ingresarButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
