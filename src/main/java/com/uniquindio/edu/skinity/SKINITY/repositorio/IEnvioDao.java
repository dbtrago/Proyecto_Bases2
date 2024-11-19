package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.DetalleVenta;
import com.uniquindio.edu.skinity.SKINITY.modelo.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnvioDao extends JpaRepository<Envio, Integer>{
    Envio findByDetalleventaId(DetalleVenta detalleVenta);
}
