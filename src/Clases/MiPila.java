/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.EmptyStackException;

/**
 *
 * @author jkale
 */
public class MiPila {
     private ClienteNode top; 
     
    public MiPila() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int nroDocumento, int tipoDocumento, String nombre, int nroContacto) {
        ClienteNode newNode = new ClienteNode(nroDocumento, tipoDocumento, nombre, nroContacto);
        newNode.next = top; 
        top = newNode;
    }

    public ClienteNode pop() {
        if (isEmpty()) {
            throw new EmptyStackException(); 
        }
        ClienteNode temp = top; 
        top = top.next; 
        temp.next = null; 
        return temp; 
    }

    public ClienteNode peek() {
        if (isEmpty()) {
            throw new EmptyStackException(); 
        }
        return top; 
    }

 
    public int size() {
        int count = 0;
        ClienteNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}