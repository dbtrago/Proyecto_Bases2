package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "sesion")
public class Sesion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesion_id")
    private Integer sesionId;
    
    @Column(name = "usuario")
    private String usuario;
    
    @Column(name = "contrasena")
    private String contrasena;
    
    @Column(name = "rol")
    private String rol;
    
    @OneToOne
    @JoinColumn(name = "vendedor_id")
    Vendedor vendedor;
    
}
