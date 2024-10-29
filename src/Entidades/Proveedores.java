/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class Proveedores {
    
    private int ruc;
    private String nombre;

    public Proveedores() {
    }
    
    public Proveedores(int ruc, String nombre) {
        this.ruc = ruc;
        this.nombre = nombre;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(long ruc) {
        this.ruc = (int) ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString(){
        return ruc +" - "+nombre;
    }
    
}
