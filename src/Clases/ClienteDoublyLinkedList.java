/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jkale
 */
public class ClienteDoublyLinkedList {
    private ClienteNode head;
    private ClienteNode tail;

    public ClienteDoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addCliente(int nroDocumento, int tipoDocumento, String nombre, int nroContacto) {
        ClienteNode newNode = new ClienteNode(nroDocumento, tipoDocumento, nombre, nroContacto);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void removeCliente(int nroDocumento) {
        ClienteNode current = head;
        while (current != null) {
            if (current.nroDocumento == nroDocumento) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    public ClienteNode searchCliente(int nroDocumento) {
        ClienteNode current = head;
        while (current != null) {
            if (current.nroDocumento == nroDocumento) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void updateCliente(int oldNroDocumento, int nroDocumento, int tipoDocumento, String nombre, int nroContacto) {
        ClienteNode current = searchCliente(oldNroDocumento);
        if (current != null) {
            current.nroDocumento = nroDocumento;
            current.tipoDocumento = tipoDocumento;
            current.nombre = nombre;
            current.nroContacto = nroContacto;
        }
    }

    public List<Object[]> getAllClientes() {
        List<Object[]> clientes = new ArrayList<>();
        ClienteNode current = head;
        while (current != null) {
            clientes.add(new Object[]{current.nroDocumento, current.tipoDocumento, current.nombre, current.nroContacto});
            current = current.next;
        }
        return clientes;
    }
}