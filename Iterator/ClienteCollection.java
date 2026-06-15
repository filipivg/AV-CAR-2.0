package com.av_car_auto_center.Iterator;

import com.av_car_auto_center.model.Cliente;
import java.util.List;

public class ClienteCollection {

    private List<Cliente> clientes;

    public ClienteCollection(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ClienteIterator iterator() {
        return new ClienteIterator(clientes);
    }
}
