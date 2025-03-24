package com.promo_viajes.api.persistence.entity;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long adminId;
    
    private String nombre;

    private String contrasena;

    private String documento;

    private String direccion;

    private String telefono;

    @OneToMany(mappedBy = "creadoPor")
    private List<Promocion> promocionesCreadas;
}
