/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author PC
 */
public class Clientes {
    private int nro_documento;
    private String nombre;

    public Clientes() {
    }

    public Clientes(int nro_documento, String nombre) {
        this.nro_documento = nro_documento;
        this.nombre = nombre;
    }

    public int getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(int nro_documento) {
        this.nro_documento = nro_documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString(){
        return nro_documento+" - "+nombre;
    }
    
}
