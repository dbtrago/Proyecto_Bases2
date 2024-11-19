package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Inventario;
import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IInventarioDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioServiceImpl implements InventarioService{

    @Autowired
    private IInventarioDao inventarioDao;
    @Autowired
    private ProductoService productoService;

    @Override
    public List<Inventario> listarInventario() {
        return (List<Inventario>) inventarioDao.findAll();
    }

    @Override
    public void guardar(Inventario inventario) {
        inventarioDao.save(inventario);
    }

    @Override
    public void eliminar(Inventario inventario) {
        inventarioDao.delete(inventario);
    }

    @Override
    public Inventario encontrarInventario(Inventario inventario) {
        return inventarioDao.findById(inventario.getInventarioId()).orElse(null);
    }

    @Override
    public boolean agregarProducto(Producto producto, Inventario inventario) {
        if (producto != null && inventario != null) {
            productoService.guardar(producto);
            
            inventario.setProductoId(producto);
            guardar(inventario);
            return true;
        }
        return false;
    }

    @Override
    public Inventario buscarPorProducto(Producto producto) {
        return inventarioDao.findByProductoId(producto);
    }

    @Override
    public boolean actualizarCantidad(Producto producto, Integer cantidadReducida) {
        
        Inventario inventario = buscarPorProducto(producto);
        if (inventario != null) {
            inventario.setCantidad(inventario.getCantidad() - cantidadReducida);
            return true;
        }
        return false;
    }
}
    

    

    

 

    
    
    
