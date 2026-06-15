/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av_car_auto_center.Decorator;

/**
 *
 * @author gusta
 */
public class MensagemComUsuario extends MensagemDecorator {

    private String usuario;

    public MensagemComUsuario(IMensagem mensagem,String usuario) {
        super(mensagem);
        this.usuario = usuario;
    }

    @Override
    public String getMensagem() {

        return mensagem.getMensagem() + "\nUsuário: " + usuario;
    }
}