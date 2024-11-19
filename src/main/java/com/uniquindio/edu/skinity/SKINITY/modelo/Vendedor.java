package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "VENDEDOR")
public class Vendedor implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedor_id")
    private Integer vendedorId;
    
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "celular")
    private String celular;
    @Column(name = "correo")
    private String correo;
    @Column(name = "identificacion")
    private String identificacion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "totalventas")
    private Double totalVentas;
    @Column(name = "identificacionreferido")
    private String identificacionReferido;
    
    @OneToOne
    @JoinColumn(name = "nivel_id")
    Nivel nivelId;
}
