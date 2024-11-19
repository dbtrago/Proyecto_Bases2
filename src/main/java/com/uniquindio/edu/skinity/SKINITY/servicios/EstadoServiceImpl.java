package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import com.uniquindio.edu.skinity.SKINITY.repositorio.IEstadoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoServiceImpl implements EstadoService{

    @Autowired
    private IEstadoDao estadoDao;

    @Override
    public List<Estado> listarEstado() {
        return (List<Estado>) estadoDao.findAll();
    }

    @Override
    public void guardar(Estado estado) {
        estadoDao.save(estado);
    }

    @Override
    public void eliminar(Estado estado) {
        estadoDao.delete(estado);
    }

    @Override
    public Estado encontrarEstado(Estado estado) {
        return estadoDao.findById(estado.getEstadoId()).orElse(null);
    }

    @Override
    public Estado encontrarPorDescripcion(String descripcion) {
        return estadoDao.findByDescripcion(descripcion);
    }

    

 

    
    
    
}
