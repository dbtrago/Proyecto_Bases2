package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISesionDao extends JpaRepository<Sesion, Integer>{
    Sesion findByUsuario(String usuario);
}
