package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INivelDao extends JpaRepository<Nivel, Integer>{
    Nivel findByNombre(String nombre);
    
}
