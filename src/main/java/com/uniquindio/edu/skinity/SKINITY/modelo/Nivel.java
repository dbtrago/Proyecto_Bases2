package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "nivel")
public class Nivel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nivel_id")
    private Integer nivelId;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "nombre")
    private String nombre;
    
}
