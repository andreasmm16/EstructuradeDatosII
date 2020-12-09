/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFileManager;

/**
 *
 * @author andre
 */
public class Node {
    protected int posicion;
    protected Node siguiente;
    protected Node anterior;

    public Node() {
        this.posicion = 0;
        this.siguiente = null;
        this.anterior = null;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setValor(int pos) {
        this.posicion = pos;
    }

    public Node getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Node siguiente) {
        this.siguiente = siguiente;
    }

    public Node getAnterior() {
        return anterior;
    }

    public void setAnterior(Node anterior) {
        this.anterior = anterior;
    }
}
