package com.uniquindio.edu.skinity.SKINITY.repositorio;

import com.uniquindio.edu.skinity.SKINITY.modelo.Inventario;
import com.uniquindio.edu.skinity.SKINITY.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventarioDao extends JpaRepository<Inventario, Integer>{
    Inventario findByProductoId(Producto producto);
}
