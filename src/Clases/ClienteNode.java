/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author jkale
 */
public class ClienteNode {
    int nroDocumento;
    int tipoDocumento;
    String nombre;
    int nroContacto;
    ClienteNode prev;
    ClienteNode next;
    
    public ClienteNode(int nroDocumento, int tipoDocumento, String nombre, int nroContacto){
        this.nroDocumento = nroDocumento;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.nroContacto = nroContacto;
        this.prev = null;
        this.next = null;
    }
}
