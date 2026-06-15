/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av_car_auto_center.Decorator;

/**
 *
 * @author gusta
 */
public class MensagemComSistema extends MensagemDecorator {

    public MensagemComSistema(IMensagem mensagem) {
        super(mensagem);
    }

    @Override
    public String getMensagem() {

        return mensagem.getMensagem() + "\nSistema: AV Car Auto Center";
    }
}