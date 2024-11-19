package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;

public interface VendedorService {
    
    public List<Vendedor> listarVendedores();
    
    public void guardar(Vendedor vendedor);
    
    public void eliminar(Vendedor vendedor);
    
    public Vendedor encontrarVendedor(Vendedor vendedor);
    
    public Vendedor actualizarVendedor(Vendedor vendedor);
    
    public Vendedor buscarPorIdentificacion(String identificacion);
    
    public boolean eliminarCompleto(Vendedor vendedor);
    
    public Vendedor buscarPorVendedorId(Integer vendedor);
    
    public boolean calcularGanancia(Vendedor vendedor, Double valorVenta);
}
