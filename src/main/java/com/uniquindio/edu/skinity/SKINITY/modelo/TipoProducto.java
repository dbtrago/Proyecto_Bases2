package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "tipoproducto")
public class TipoProducto implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipoproducto_id")
    private Integer tipoProductoId;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "nombre")
    private String nombre;
    
}
