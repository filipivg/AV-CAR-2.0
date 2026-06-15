/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av_car_auto_center.Iterator;

/**
 *
 * @author gusta
 */
import com.av_car_auto_center.model.Cliente;
import java.util.List;

public class ClienteIterator {

    private List<Cliente> clientes;
    private int posicao = 0;

    public ClienteIterator(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean hasNext() {
        return posicao < clientes.size();
    }

    public Cliente next() {
        return clientes.get(posicao++);
    }
}
