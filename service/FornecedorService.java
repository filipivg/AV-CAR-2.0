package com.av_car_auto_center.service;

import com.av_car_auto_center.model.Fornecedor;
import com.av_car_auto_center.model.Peca;
import com.av_car_auto_center.respository.FornecedorDao;
import com.av_car_auto_center.respository.PecaDao;
import com.av_car_auto_center.validation.FornecedorValidation;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedorService implements IGenericCadastroService<Fornecedor> {

    private final FornecedorDao fornecedorDao;
    private final PecaDao pecaDao;

    public FornecedorService() {
        this.fornecedorDao = new FornecedorDao();
        this.pecaDao = new PecaDao();
    }

    @Override
    public List<Fornecedor> listarAtivos() {
        return fornecedorDao.SelectAll();
    }

    @Override
    public void desativar(int id) {
        // Desativa todas as peças do fornecedor
        List<Peca> pecas = pecaDao.SelectPorFornecedor(id);
        for (Peca p : pecas) {
            pecaDao.Desativar(p.getID());
        }

        fornecedorDao.Desativar(id);
    }

    @Override
    public void ativar(int id) {
        fornecedorDao.Ativar(id);
    }

    @Override
    public void cadastrar(Fornecedor fornecedor) {
        try {
            FornecedorValidation.validar(
                fornecedor.getNomeFornecedor(),
                fornecedor.getTelefone(),
                fornecedor.getEmail()
            );

            fornecedorDao.insert(fornecedor);
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        try {
            FornecedorValidation.validar(
                fornecedor.getNomeFornecedor(),
                fornecedor.getTelefone(),
                fornecedor.getEmail()
            );

            fornecedorDao.update(fornecedor);
            JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public Fornecedor buscarPorID(int id) {
        return fornecedorDao.SelectPorID(id);
    }

    public Fornecedor buscarPorNome(String nome) {
        return fornecedorDao.SelectPorNome(nome);
    }
}