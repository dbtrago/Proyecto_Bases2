package com.uniquindio.edu.skinity.SKINITY.modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "referido")
public class Referido implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "referido_id")
    private Integer referidoId;
    
    @OneToOne
    @JoinColumn(name = "vendedorprincipal_id")
    Vendedor vendedorPrincipalId;
    
    @OneToOne
    @JoinColumn(name = "vendedorreferido_id")
    Vendedor vendedorReferidoId;
    
}
