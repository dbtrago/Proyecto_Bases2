package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "envio")
public class Envio implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "envio_id")
    private Integer envioId;
    
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha")
    private String fecha;
    
    @OneToOne
    @JoinColumn(name = "detalleventa_id")
    DetalleVenta detalleventaId;
    
    @OneToOne
    @JoinColumn(name = "estado_id")
    Estado estadoId;
    
}
