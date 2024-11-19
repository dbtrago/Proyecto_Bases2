package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Cliente;
import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Envio;
import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import java.util.List;

public interface EnvioService {
    public List<Envio> listarEnvio();
    
    public void guardar(Envio envio);
    
    public void eliminar(Envio envio);
    
    public Envio encontrarEnvio(Envio envio);
    
    public boolean crearEnvioCompleto(DetalleVenta detalleVenta);
    
    public Envio buscarPorDetalleVenta(DetalleVenta detalleVenta);
    
}
