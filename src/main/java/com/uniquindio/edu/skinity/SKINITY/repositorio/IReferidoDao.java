package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Referido;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReferidoDao extends JpaRepository<Referido, Integer>{
    List<Referido> findByVendedorPrincipalId(Vendedor vendedor);
    
    Referido findByVendedorReferidoId(Vendedor vendedor);
}
