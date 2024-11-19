package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IProductoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private IProductoDao productoDao;

    @Override
    public List<Producto> listarProducto() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    public void guardar(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    public Producto encontrarProducto(Producto producto) {
        return productoDao.findById(producto.getProductoId()).orElse(null);
    }
    
    
}
