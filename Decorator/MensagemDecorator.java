/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av_car_auto_center.Decorator;

/**
 *
 * @author gusta
 */
public abstract class MensagemDecorator implements IMensagem {

    protected IMensagem mensagem;

    public MensagemDecorator(IMensagem mensagem) {
        this.mensagem = mensagem;
    }
}