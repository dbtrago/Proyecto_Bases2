package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Afiliacion;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAfiliacionDao extends JpaRepository<Afiliacion, Integer>{
    Afiliacion findByVendedorId(Vendedor vendedor);
    
}
