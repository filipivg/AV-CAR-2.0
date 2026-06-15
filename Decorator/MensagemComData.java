/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av_car_auto_center.Decorator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MensagemComData extends MensagemDecorator {

    public MensagemComData(IMensagem mensagem) {
        super(mensagem);
    }

    @Override
    public String getMensagem() {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return mensagem.getMensagem() + "\nData: " + LocalDateTime.now().format(formato);
    }
}
