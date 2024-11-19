package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "detalleventa")
public class DetalleVenta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalleventa_id")
    private Integer detalleventaId;
    
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "valortotal")
    private Double valortotal;
   
    @OneToOne
    @JoinColumn(name = "vendedor_id")
    Vendedor vendedorId;
    
    @OneToOne
    @JoinColumn(name = "producto_id")
    Producto productoId;
    
    @OneToOne
    @JoinColumn(name = "cliente_id")
    Cliente clienteId;
}
