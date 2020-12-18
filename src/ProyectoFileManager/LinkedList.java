/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

import java.io.IOException;

/**
 *
 * @author andre
 */
public class LinkedList {

    Node head;

    public LinkedList() {
        this.head = new Node();
        this.head.setValor(-1);
    }

    public boolean estaVacia() {

        if(this.head == null){
            return true;
        }else
            return false;
    }

    //cuando elimine un registro 
    public void agregarNodo(int posicion) throws IOException {
        Node nuevo = new Node();
        nuevo.setValor(posicion);

        if (estaVacia()) {
            head = nuevo;
            Main.file.seek(0);
            Main.file.readInt();
            Main.file.readInt();
            Main.file.writeInt(head.getPosicion());
        } else {
            Node tmp = head;
            while (tmp.getSiguiente() != null) {
                tmp = tmp.getSiguiente();
            }

            tmp.setSiguiente(nuevo);
            nuevo.setAnterior(tmp);
            nuevo.setSiguiente(null);
        }
    }

    //para armar lista e introducir registros
    public int posDisponible() throws IOException {
        if (estaVacia()) {
            return -1;
        } else {
            int pos = 0;
            if (head.getSiguiente() == null) {
                pos = head.getPosicion();
                Main.file.seek(0);
                Main.file.readInt();
                Main.file.readInt();
                Main.file.writeInt(-1);
                head = null;
                return pos;
            } else {
                pos = head.getPosicion();
                head = head.getSiguiente();
                head.setAnterior(null);
                Main.file.seek(0);
                Main.file.readInt();
                Main.file.readInt();
                Main.file.writeInt(head.getPosicion());
                return pos;
            }

        }

    }
    
     public void Imprimir() {
        if (estaVacia()) {
            System.out.println("¡Lista está vacia!");
        } else {
            Node tmp = head;

            while (tmp != null) {
                System.out.println("Nombre: " + tmp.getPosicion()+ " ");
                tmp = tmp.getSiguiente();
            }
        }

    }

}
