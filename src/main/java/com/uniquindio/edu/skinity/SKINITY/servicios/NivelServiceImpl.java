package com.uniquindio.edu.skinity.SKINITY.servicios;

import com.uniquindio.edu.skinity.SKINITY.modelo.Nivel;
import com.uniquindio.edu.skinity.SKINITY.repositorio.INivelDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NivelServiceImpl implements NivelService{

    @Autowired
    private INivelDao nivelDao;
    
    @Override
    public List<Nivel> listarNivel() {
        return (List<Nivel>) nivelDao.findAll();
    }

    @Override
    public void guardar(Nivel nivel) {
        nivelDao.save(nivel);
    }

    @Override
    public void eliminar(Nivel nivel) {
        nivelDao.delete(nivel);
    }

    @Override
    public Nivel encontrarNivel(Nivel nivel) {
        return nivelDao.findById(nivel.getNivelId()).orElse(null);
    }

    @Override
    public Nivel encontrarNivelPorNombre(String nombre) {
        return nivelDao.findByNombre(nombre);
    }
    
    
}
