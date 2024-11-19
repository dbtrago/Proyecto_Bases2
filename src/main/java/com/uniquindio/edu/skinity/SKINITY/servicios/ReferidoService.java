package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Referido;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;

public interface ReferidoService {
    public List<Referido> listarReferido();
    
    public void guardar(Referido referido);
    
    public void eliminar(Referido referido);
    
    public Referido encontrarReferido(Referido referido);
    
    public List<Referido> buscarPorReferidoPrincipal(Vendedor vendedor);
    
    public Referido buscarPorVendedorReferido(Vendedor vendedor);
    
}
