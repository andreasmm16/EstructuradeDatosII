package ProyectoFileManager;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template rafObj, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

enum TIPO {
    INT, CHAR
}

public class XML_converter {

    static private class Campo {
        public String nombre;
        public TIPO tipo;
        public int size;
        public boolean is_key;
    }

    static private int cantidad_de_registros;
    static private int record_size;
    static private int head;
    static private long tamano_metadata;

    static private ArrayList<Campo> campos;
    
    public static void convert(String input_file_path) throws IOException{
        convert(input_file_path, input_file_path.replace(".txt", ".xml"));
    }
    
    public static void convert(File input_file) throws IOException{
        convert(input_file.getPath(), input_file.getPath().replace(".txt", ".xml"));
    }

    public static void convert(String input_file_path, String output_file_path) throws IOException {
        campos = new ArrayList<Campo>();
        RandomAccessFile rafObj = new RandomAccessFile(input_file_path, "r");
        rafObj.seek(0);
        File file = new File(output_file_path);
        file.createNewFile();
 
        cantidad_de_registros = rafObj.readInt();
        record_size = rafObj.readInt();
        head = rafObj.readInt();
        tamano_metadata = rafObj.readLong();

        for (long i = 0; i < record_size;) {
            Campo tmp = new Campo();
            tmp.nombre = rafObj.readUTF();
            rafObj.readChar();
            switch (rafObj.readUTF()) {
                case "int":
                    tmp.tipo = TIPO.INT;
                    break;
                case "char":
                    tmp.tipo = TIPO.CHAR;
                    i += 2;
                    break;
            }
            rafObj.readChar();
            tmp.size = rafObj.readInt();
            i += (tmp.tipo == TIPO.INT) ? 4 : tmp.size;
            rafObj.readChar();
            tmp.is_key = rafObj.readBoolean();
            rafObj.readChar();
            campos.add(tmp);
        }
        
        FileWriter fw = new FileWriter(file);       
        fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        for (int i = 0; i < cantidad_de_registros; i++) {
            fw.append("<Registro>\n");
            for (Campo campo : campos) {
                fw.append("\t<" + campo.nombre + '>');
                switch (campo.tipo) {
                    case CHAR:
                        fw.append(rafObj.readUTF());
                        break;
                    case INT:
                        fw.append(rafObj.readInt() + "");
                        break;
                }
                fw.append("</" +  campo.nombre + ">\n"); 
            }
            fw.append("</Registro>\n");
        }
        fw.close();
    }

  //  public static void main(String args[]) throws IOException {
    //    convert("otrofile.txt");
    //}
}
