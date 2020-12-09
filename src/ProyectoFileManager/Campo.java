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
public class Campo {

    String nombre, tipo;
    int size;
    boolean llave;
    String valor; //Agregue Eger

    public Campo(String nombre, String tipo, int size, boolean llave) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.size = size;
        this.llave = llave;
    }

    public Campo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getLlave() {
        return llave;
    }

    public void setLlave(boolean llave) {
        this.llave = llave;
    }

}
