package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "afiliacion")
public class Afiliacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "afiliacion_id")
    private Integer afiliacionId;
    
    @Column(name = "fecha_inicial")
    private String fechaInicial;
    @Column(name = "fecha_final")
    private String fechaFinal;
    @Column(name = "observacion")
    private String observacion;
    
    @OneToOne
    @JoinColumn(name = "estado_id")
    Estado estadoId;
    
    @OneToOne
    @JoinColumn(name = "vendedor_id")
    Vendedor vendedorId;
}
