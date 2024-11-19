package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoDao extends JpaRepository<Estado, Integer>{
    Estado findByDescripcion(String descripcion);
}
