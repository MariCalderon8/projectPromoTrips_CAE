package com.promo_viajes.api.persistence.entity;

import lombok.Data;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "destino")
public class Destino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id_destino")
    private Long destinoId;

    private String nombre;

    private String ciudad;

    private String pais;

    private String clima;

    private String descripcion;

    @ManyToMany
    @JoinTable(
        name = "viaje_destino",
        joinColumns = @JoinColumn(name = "id_destino"),
        inverseJoinColumns = @JoinColumn(name = "id_viaje")
    )
    private List<Viaje> viajes;
}
