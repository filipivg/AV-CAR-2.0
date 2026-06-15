package com.av_car_auto_center.factory;

import com.av_car_auto_center.model.Cliente;
import com.av_car_auto_center.model.ClientePF;
import com.av_car_auto_center.model.ClientePJ;

public class ClienteFactory {

    public static Cliente criarCliente(String tipoCliente) {

        if(tipoCliente.equalsIgnoreCase("PF")) {
            return new ClientePF();
        }

        if(tipoCliente.equalsIgnoreCase("PJ")) {
            return new ClientePJ();
        }

        return new Cliente();
    }
}