/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.av_car_auto_center.service;

import com.av_car_auto_center.model.OrdemServico;
import com.av_car_auto_center.respository.OrdemServicoDao;
import com.av_car_auto_center.validation.OrdemServicoValidation;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gusta
 */
public class OrdemServicoService implements IGenericService<OrdemServico>{
    
    private final OrdemServicoDao ordemServicoDao;
 
    public OrdemServicoService() {
        this.ordemServicoDao = new OrdemServicoDao();
    }
    
    @Override
    public void cadastrar(OrdemServico ordemServico) {
        cadastrarComId(ordemServico);
    }
 
    @Override
    public void atualizar(OrdemServico ordemServico) {
        try {
            OrdemServicoValidation.validar(
                ordemServico.getNomeCliente(),
                ordemServico.getDataAbertura(),
                ordemServico.getDataFinalizacao(),
                ordemServico.getValor(),
                ordemServico.getStatus(),
                ordemServico.getVeiculo().getID()
            );
 
            ordemServicoDao.update(ordemServico);
            JOptionPane.showMessageDialog(null, "Ordem de Serviço atualizada com sucesso!");
 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
 
    @Override
    public OrdemServico buscarPorID(int id) {
        return ordemServicoDao.SelectPorID(id);
    }
 
    public List<OrdemServico> listarTodos() {
        return ordemServicoDao.SelectAll();
    }
    
    public int cadastrarComId(OrdemServico ordemServico) {
        try {
            OrdemServicoValidation.validar(
                ordemServico.getNomeCliente(),
                ordemServico.getDataAbertura(),
                ordemServico.getDataFinalizacao(),
                ordemServico.getValor(),
                ordemServico.getStatus(),
                ordemServico.getVeiculo().getID()
            );

            int idGerado = ordemServicoDao.insertComId(ordemServico);

            if (idGerado != -1) {
                JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada com sucesso!");
            }

            return idGerado;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }
    }
}
