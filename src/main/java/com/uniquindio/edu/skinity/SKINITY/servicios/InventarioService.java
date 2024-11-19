package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Inventario;
import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import java.util.List;

public interface InventarioService {
    public List<Inventario> listarInventario();
    
    public void guardar(Inventario inventario);
    
    public void eliminar(Inventario inventario);
    
    public Inventario encontrarInventario(Inventario inventario);
    
    public boolean agregarProducto(Producto producto, Inventario inventario);
    
    public Inventario buscarPorProducto(Producto producto);
    
    public boolean actualizarCantidad(Producto producto, Integer cantidadReducida);
    
}
