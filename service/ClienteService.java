package com.av_car_auto_center.service;

import com.av_car_auto_center.Decorator.IMensagem;
import com.av_car_auto_center.Decorator.MensagemComData;
import com.av_car_auto_center.Decorator.MensagemComSistema;
import com.av_car_auto_center.Decorator.MensagemComUsuario;
import com.av_car_auto_center.Decorator.MensagemSimples;
import com.av_car_auto_center.Iterator.ClienteCollection;
import com.av_car_auto_center.Iterator.ClienteIterator;
import com.av_car_auto_center.model.Cliente;
import com.av_car_auto_center.model.OrdemServico;
import com.av_car_auto_center.model.Veiculo;
import com.av_car_auto_center.respository.ClienteDao;
import com.av_car_auto_center.respository.OrdemServicoDao;
import com.av_car_auto_center.respository.VeiculoDao;
import com.av_car_auto_center.validation.ClienteValidation;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteService implements IGenericCadastroService<Cliente> {

    private final ClienteDao clienteDao;
    private final OrdemServicoDao ordemServicoDao;
    private final VeiculoDao veiculoDao;

    public ClienteService() {
        this.clienteDao = new ClienteDao();
        this.ordemServicoDao = new OrdemServicoDao();
        this.veiculoDao = new VeiculoDao();
    }

    @Override
    public List<Cliente> listarAtivos() {
        return clienteDao.SelectAll();
    }

    @Override
    public void desativar(int id) {
        try {
            if (possuiOrdensAbertas(id)) {
                throw new Exception("Não é possível desativar o cliente, pois ele possui ordens de serviço em aberto.");
            }

            // Desativa todos os veículos do cliente
            List<Veiculo> veiculos = veiculoDao.SelectPorCliente(id);
            for (Veiculo v : veiculos) {
                veiculoDao.Desativar(v.getID());
            }

            clienteDao.Desativar(id);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void ativar(int id) {
        clienteDao.Ativar(id);
    }

    @Override
    public void cadastrar(Cliente cliente) {
        try {
            ClienteValidation.validar(
                cliente.getTipoCliente(),
                cliente.getNomeRazaoSocial(),
                cliente.getCPF(),
                cliente.getTelefone(),
                cliente.getEmail()
            );

            if (cpfJaCadastrado(cliente.getCPF())) {
                throw new Exception("Já existe um cliente cadastrado com este CPF.");
            }

            clienteDao.insert(cliente);
            IMensagem msg = new MensagemSimples("Cliente cadastrado com sucesso!");

            msg = new MensagemComData(msg);
            msg = new MensagemComUsuario(msg,"Administrador");
            msg = new MensagemComSistema(msg);

            JOptionPane.showMessageDialog(null, msg.getMensagem());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            ClienteValidation.validar(
                cliente.getTipoCliente(),
                cliente.getNomeRazaoSocial(),
                cliente.getCPF(),
                cliente.getTelefone(),
                cliente.getEmail()
            );

            Cliente clienteExistente = clienteDao.SelectPorID(cliente.getID());

            if (clienteExistente != null && !clienteExistente.getCPF().equals(cliente.getCPF()) && cpfJaCadastrado(cliente.getCPF())) {
                throw new Exception("Já existe um cliente cadastrado com este CPF.");
            }

            clienteDao.update(cliente);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorID(int id) {
        return clienteDao.SelectPorID(id);
    }

    private boolean cpfJaCadastrado(String cpf) {
        List<Cliente> clientes = clienteDao.SelectAll();

        ClienteCollection collection = new ClienteCollection(clientes);

        ClienteIterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();

            if (cliente.getCPF().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    private boolean possuiOrdensAbertas(int idCliente) {
        Cliente cliente = clienteDao.SelectPorID(idCliente);
        if (cliente == null) return false;

        List<OrdemServico> ordens = ordemServicoDao.SelectPorCliente(cliente.getNomeRazaoSocial());
        for (OrdemServico o : ordens) {
            if (!o.getStatus().equals("Finalizada")) {
                return true;
            }
        }
        return false;
    }

    public Cliente buscarPorCPF(String cpf) {
        return clienteDao.SelectPorCPF(cpf);
    }
}