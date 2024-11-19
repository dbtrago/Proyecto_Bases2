package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Vendedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleVentaDao extends JpaRepository<DetalleVenta, Integer>{
    List<DetalleVenta> findByVendedorId(Vendedor vendedor);
}
