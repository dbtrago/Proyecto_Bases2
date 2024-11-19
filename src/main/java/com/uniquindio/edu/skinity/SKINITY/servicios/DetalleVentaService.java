package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Cliente;
import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;

public interface DetalleVentaService {
    public List<DetalleVenta> listarDetalleVenta();
    
    public void guardar(DetalleVenta detalleVenta);
    
    public void eliminar(DetalleVenta detalleVenta);
    
    public DetalleVenta encontrarDetalleVenta(DetalleVenta detalleVenta);
    
    public boolean agregarDetalleVenta(Vendedor vendedor, DetalleVenta detalleVenta, Cliente cliente, Producto producto);
    
    public List<DetalleVenta> buscarDetalleVentaPorVendedor(Vendedor vendedor);
}
