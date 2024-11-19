package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class Inventario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventario_id")
    private Integer inventarioId;
    
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cantidad")
    private Integer cantidad;
    
    @OneToOne
    @JoinColumn(name = "producto_id")
    Producto productoId;
}
