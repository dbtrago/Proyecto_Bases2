package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Cliente;
import java.util.List;

public interface ClienteService {
    public List<Cliente> listarCliente();
    
    public void guardar(Cliente cliente);
    
    public void eliminar(Cliente cliente);
    
    public Cliente encontrarCliente(Cliente cliente);
    
}
