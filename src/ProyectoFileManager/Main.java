/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Main {
    public static Frame frame;
    public static RandomAccessFile file;
    public static boolean open;
    public static String name; //guardo nombre de archivo
    public static String fileName; //guardo nombre de carpeta
    public static ArrayList<Campo> campos;
    public static int tipoCam;
    public static File carpeta;
    public static String key; //aqui guardo nombre del campo que sea llave
    public static int recordSize; //aqui guardo el tamaño de registro y con misma variable hago restricciones de campos
    public static int cantRegis;
    public static RandomAccessFile indexFile;
    public static int head;
    public static LinkedList lista;
    public static long structure;
    public static BTree arbol;
    public static EstructuraIndex index ;
    
    public static void main(String[] args) {
        frame = new Frame();
        open = false;
        campos = new ArrayList();
        tipoCam = 0;
        key="";
        lista = new LinkedList();
        frame.setVisible(true);
        frame.setResizable(false);
        MenuPrincipal mp = new MenuPrincipal();
        arbol = new BTree();
        index = new EstructuraIndex();
        frame.Panel(mp);
    }
}
