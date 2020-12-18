package ProyectoFileManager;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diego_ramos
 */
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

//enum TIPO {
//    INT, CHAR
//}

public class Cruce_archivos {
    
    static public class Campo{
      
        public String nombre;
        public TIPO tipo;
        public int size;
        public boolean is_key;
       
        @Override
        public String toString() {
            return nombre;
        }
       
        @Override
        public boolean equals(Object o){
            if(o instanceof Campo)
                return nombre.equals(((Campo)o).nombre);
            return false;
        }
    }
    static private int cantidad_de_registros[];
    static private int record_size[];
    static private int head[];
    static private long tamano_metadata[];    
    static private RandomAccessFile rafs[];
    static private ArrayList<Campo> campos[];
    static private ArrayList<Campo> campos_comunes;
    
    static public ArrayList<Campo>[] getCampos(){
        return campos;
    }
    
    static public ArrayList<Campo> getCamposComunes(){
        return campos_comunes;
    }
    
    public static void leerArchivos(ArrayList<String> file_paths) throws IOException{
        String paths[] = new String[file_paths.size()];
        for(int i = 0; i < paths.length; i++)
            paths[i] = file_paths.get(i);
        leerArchivos(paths);
    }
   
    public static void leerArchivos(String file_paths[]) throws IOException{
        
        campos_comunes = new ArrayList();
        cantidad_de_registros = new int[file_paths.length];
        record_size = new int[file_paths.length];
        head = new int[file_paths.length];
        tamano_metadata = new long[file_paths.length];
        campos = new ArrayList[file_paths.length];
        rafs = new RandomAccessFile[file_paths.length];
        
        for(int i = 0; i < file_paths.length; i++){ 
            campos[i] = new ArrayList();
            rafs[i] = new RandomAccessFile(file_paths[i], "r");
            rafs[i].seek(0);
            cantidad_de_registros[i] = rafs[i].readInt();
            record_size[i] = rafs[i].readInt();
            head[i] = rafs[i].readInt();
            tamano_metadata[i] = rafs[i].readLong();

            for (int j = 0; j < record_size[i];) {
                Campo tmp = new Campo();
                tmp.nombre = rafs[i].readUTF();
                rafs[i].readChar();
                switch (rafs[i].readUTF()) {
                    case "int":
                        tmp.tipo = TIPO.INT;
                        break;
                    case "char":
                        tmp.tipo = TIPO.CHAR;
                        j += 2;
                        break;
                }
                rafs[i].readChar();
                tmp.size = rafs[i].readInt();
                j += (tmp.tipo == TIPO.INT) ? 4 : tmp.size;
                rafs[i].readChar();
                tmp.is_key = rafs[i].readBoolean();
                rafs[i].readChar();
                campos[i].add(tmp);
            }
        }
        verificarCamposComunes();
    }
    
    static private boolean verificarCamposComunes(int indice, Campo campo_comun){
        if(indice == campos.length){
            campos_comunes.add(campo_comun);
            return true;
        }
        for(Campo campo : campos[indice]){
            if(campo.equals(campo_comun))
                if(verificarCamposComunes(indice + 1, campo))
                    return true;
        }
        return false;
    }
    
    static private void verificarCamposComunes(){
        for(int i = 0; i < campos[0].size(); i++)
            verificarCamposComunes(1, campos[0].get(i));
    }
    
    static private boolean existe(ArrayList<Campo> lista, Campo campo){
        for(Campo _campo : lista){
            if(_campo.equals(campo))
                return true;
        }
        return false;
    }
    
    static public void cruzar(String nombres_de_campos[], String output_file) throws IOException{
        ArrayList<Campo> lista = new ArrayList();
        for(int i = 0; i < nombres_de_campos.length; i++){
            for(Campo campo : campos_comunes){
                String lol = nombres_de_campos[i];
                if(campo.nombre.equals(lol))
                    lista.add(campo);
            }
        }
        cruzar(lista, output_file);
    }
    
    static public void cruzar(ArrayList<Campo> campos_seleccionados, String output_file) throws IOException{
        long metadata_size = 0;
        RandomAccessFile raf_out = new RandomAccessFile(output_file, "rw");
        ArrayList<Campo> output_campos = new ArrayList(campos_seleccionados);
        int registros_count = 0;
        int record_size = 0;
        int head = 0;
        for(Campo campo : campos_seleccionados){
            metadata_size += campo.nombre.length() * 2 + 2; //nombre
            metadata_size += (campo.tipo == TIPO.INT) ? 4 : 5; //tipo
            metadata_size += 4; //size
            metadata_size += 1; //key
        }
        for(int i = 0; i < campos.length; i++)
            for(Campo campo : campos[i])
                if(!existe(output_campos, campo)){                   
                    metadata_size += campo.nombre.length() * 2 + 2; //nombre
                    metadata_size += (campo.tipo == TIPO.INT) ? 4 : 5; //tipo
                    metadata_size += 4; //size
                    metadata_size += 1; //key
                    output_campos.add(campo);
                }
        raf_out.writeInt(0); //Cantidad de Registros
        raf_out.writeInt(0); //Record Size
        raf_out.writeInt(0); //Head
        raf_out.writeLong(metadata_size + 12); //Tamaño de metadata
        for(Campo campo : output_campos){  //Escribir metadata de campos
            record_size += (campo.tipo == TIPO.INT) ? 4 : campo.size + 2;
            System.out.print(campo.nombre + ',');
            raf_out.writeUTF(campo.nombre);
            raf_out.writeChar(':');
            switch(campo.tipo){
                case INT:
                    raf_out.writeUTF("int"); break;
                case CHAR:
                    raf_out.writeUTF("char"); break;
            }
            raf_out.writeChar(':');
            raf_out.writeInt(campo.size);
            raf_out.writeChar(':');
            raf_out.writeBoolean(campo.is_key);
            raf_out.writeChar('|');
        }
        System.out.println();
        for(int i = 0; i < cantidad_de_registros[0]; i++){
            Object registro_tmp[] = new Object[output_campos.size()];
            for(int k = 0; k < campos[0].size(); k++){
                TIPO tipo = campos[0].get(k).tipo;
                int index = output_campos.indexOf(campos[0].get(k));
                registro_tmp[index] = (tipo == TIPO.INT) ? rafs[0].readInt() : rafs[0].readUTF();
            }
            if(verificar_cruce(1, registro_tmp, campos_seleccionados, output_campos)){  
                registros_count++;
                for(int k = 0; k < registro_tmp.length; k++){
                    switch(output_campos.get(k).tipo){
                        case INT:
                            int tmp_int = (int)registro_tmp[k];
                            System.out.print(tmp_int + ",");                          
                            raf_out.writeInt(tmp_int); 
                            break;
                        case CHAR:
                            String tmp_str = registro_tmp[k].toString();
                            System.out.print(tmp_str + ',');   
                            raf_out.writeUTF(tmp_str); break;
                    }
                }
                System.out.println();
            }
        }
        raf_out.seek(0);
        raf_out.writeInt(registros_count);
        raf_out.writeInt(record_size);
    }
    
    static private boolean verificar_cruce(int archivo_indice, Object registro_tmp[], ArrayList<Campo> campos_seleccionados, ArrayList<Campo> output_campos) throws IOException{
        if(archivo_indice == campos.length)
            return true;
        rafs[archivo_indice].seek(tamano_metadata[archivo_indice]);
        for(int i = 0; i < cantidad_de_registros[archivo_indice]; i++){
            boolean next = false;
            for(int k = 0; k < campos[archivo_indice].size(); k++){
                TIPO tipo = campos[archivo_indice].get(k).tipo;
                int index = output_campos.indexOf(campos[archivo_indice].get(k));
                if(existe(campos_seleccionados, campos[archivo_indice].get(k))){
                    switch(tipo){
                        case INT: next = (int)registro_tmp[index] == rafs[archivo_indice].readInt(); break;
                        case CHAR: next = (((String)registro_tmp[index]).trim()).equals(rafs[archivo_indice].readUTF().trim()); break;
                    }                
                }else 
                    registro_tmp[index] = (tipo == TIPO.INT) ? rafs[archivo_indice].readInt() : rafs[archivo_indice].readUTF();
            }
            if(next)
                if(verificar_cruce(archivo_indice + 1, registro_tmp, campos_seleccionados, output_campos))
                    return true;
        }
        return false;
    }
       
    public static void main(String args[]) throws IOException{
        leerArchivos(new String[]{"ciudadanos.txt", "empleados.txt"});
        for(ArrayList<Campo> c : campos){
            for(Campo campo : c)
                System.out.println(campo.nombre);
            System.out.println();
        }
        System.out.println(); 
        cruzar(new String[]{"Ciudad", "Nombre"}, "cruce2.txt");
    }
}