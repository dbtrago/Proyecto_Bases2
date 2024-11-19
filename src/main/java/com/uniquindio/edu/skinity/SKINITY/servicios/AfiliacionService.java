package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Afiliacion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;

public interface AfiliacionService {
    public List<Afiliacion> listarAfiliacion();
    
    public void guardar(Afiliacion afiliacion);
    
    public void eliminar(Afiliacion afiliacion);
    
    public Afiliacion encontrarAfiliacion(Afiliacion afiliacion);
    
    public Afiliacion buscarPorVendedor(Vendedor vendedor);
}
