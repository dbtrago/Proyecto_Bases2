package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendedorDao extends JpaRepository<Vendedor, Integer>{
    Vendedor findByIdentificacion(String identificacion);
    
    Vendedor findByVendedorId(Integer vendedor);
}
