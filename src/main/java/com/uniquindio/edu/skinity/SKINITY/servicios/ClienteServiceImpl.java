package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Cliente;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IClienteDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private IClienteDao clienteDao;

    @Override
    public List<Cliente> listarCliente() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getClienteId()).orElse(null);
    }
    
    
}
