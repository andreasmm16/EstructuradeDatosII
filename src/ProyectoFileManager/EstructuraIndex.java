package ProyectoFileManager;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class EstructuraIndex {

    String valor;
    int puntero_registro;

    public EstructuraIndex(String valor, int puntero) {
        this.valor = valor;
        this.puntero_registro = puntero;
    }

    public EstructuraIndex() {
        this.valor = "";
        this.puntero_registro = -1;
    }

    public void insertIndex() throws IOException {//Escribir estructura de el index file
        Main.indexFile.seek(Main.indexFile.length());
        Main.indexFile.writeUTF(this.valor + ",");
        Main.indexFile.writeInt(this.puntero_registro);
        Main.indexFile.writeUTF("|");
    }

    public void cargarIndices() throws IOException {
        EstructuraIndex es;
        Main.indexFile.seek(0);
        Main.arbol = new BTree();
        while (Main.indexFile.getFilePointer() < Main.indexFile.length()) {
            es = new EstructuraIndex();
            //es.nombre_campo = Main.indexFile.readUTF().split(",")[0];
            es.valor = Main.indexFile.readUTF().split(",")[0];
            es.puntero_registro = Main.indexFile.readInt();
            String puntero = Main.indexFile.readUTF();
            Object indice = es;
            int value = es.valor.chars().reduce(0, Integer::sum);
            Main.arbol.add(value, es);
        }
        Main.indexFile.seek(Main.indexFile.length());
    }

    public int getPunteroRegistro(String k) {//obtener el puntero del registro que se esta buscando
        int value = k.chars().reduce(0, Integer::sum);
        EstructuraIndex registro = Main.arbol.search(value);
        if (registro != null) {
            return registro.puntero_registro;
        }
        return -1;
    }

    public Object[] getRegistroCompleto(int posRegistro) throws IOException {//Obtener el registro completo con todos sus campos
        ArrayList<String> fields = new ArrayList<String>();
        int campos = getCantidadCampos(fields);
        Object[] row = new Object[campos];
        Main.file.seek(posRegistro); //Posicionarnos en el puntero del registro buscado
        for (int i = 0; i < campos && posRegistro < Main.file.length(); i++) {
            if (fields.get(i).equals("char")) {
                row[i] = Main.file.readUTF();
            } else {
                row[i] = (Integer) Main.file.readInt();
            }
        }
        if (row != null) {
            return row;
        }
        return null;
    }

    public int getCantidadCampos(ArrayList<String> fields) throws IOException {//Extrae la cantidad de campos que tiene el registro
        int cantidadCampos = 0;
        Main.file.seek(0);
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readInt();
        Main.file.readLong();
        while (Main.file.getFilePointer() < Main.structure) {
            Main.file.readUTF();
            Main.file.readChar();
            fields.add(Main.file.readUTF());
            Main.file.readChar();
            Main.file.readInt();
            Main.file.readChar();
            Main.file.readBoolean();
            Main.file.readChar();
            cantidadCampos++;
        }
        return cantidadCampos;
    }

    public boolean borrarRegistro(String k, String name) throws IOException {
        int value = k.chars().reduce(0, Integer::sum);
        EstructuraIndex registro = Main.arbol.search(value);
        if (registro != null) {
            Main.arbol.delete(value);
            Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + name + "Index.txt", "rw");
            Main.indexFile.setLength(0);
            System.out.println(Main.arbol.ReWriteIndexFile()); 
            return true;
        }
        return false;
    }

//    public boolean ModificarRegistro(String k, String nameFile, String nuevo) throws IOException {
//        int value = k.chars().reduce(0, Integer::sum);
//        EstructuraIndex nodo = Main.arbol.search(value);
//        //  nodo.
//        if (nodo != null) {
//            Main.arbol.Modify(value, nuevo);
//            //Files.deleteIfExists(Paths.get(Main.fileName + "\\" + Main.name + name + "Index.txt"));
//            Main.indexFile = new RandomAccessFile(Main.fileName + "\\" + Main.name + nameFile + "Index.txt", "rw");
//            Main.indexFile.setLength(0);
//            Main.indexFile.seek(0);
//            return true;
//        }
//        return false;
//    }

    public void CrearIndex(String nombre) throws IOException {
        int position = 0;
        for (int i = 0; i < Main.campos.size(); i++) {
            if (nombre.equals(Main.campos.get(i).getNombre())) {
                break;
            }
            position++;
        }

        ArrayList<String> fields = new ArrayList<String>();
        int campos = getCantidadCampos(fields);
        Object[] row = new Object[campos];
        Main.indexFile.seek(Main.indexFile.length());

        for (int i = 0; i < Main.cantRegis; i++) {
            int PosRegistro = (int) Main.file.getFilePointer();
            for (int j = 0; j < campos; j++) {
                if (position == j) {
                    if (fields.get(j).equals("char")) {
                        row[j] = Main.file.readUTF();
                        Main.indexFile.writeUTF(row[j] + ",");
                        Main.indexFile.writeInt(PosRegistro);
                        Main.indexFile.writeUTF("|");
                    } else {
                        row[j] = (Integer) Main.file.readInt();
                        Main.indexFile.writeUTF(row[j] + ",");
                        Main.indexFile.writeInt(PosRegistro);
                        Main.indexFile.writeUTF("|");
                    }
                } else {
                    if (fields.get(j).equals("char")) {
                        row[j] = Main.file.readUTF();
                    } else {
                        row[j] = (Integer) Main.file.readInt();
                    }
                }

            }
        }
    }
//}
}
