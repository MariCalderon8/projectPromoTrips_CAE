package com.promo_viajes.api.persistence.entity;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@Table(name = "viaje")
public class Viaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long viajeId;

    private String nombre;

    private float precio;

    @Column(name = "dias_duracion")
    private int diasDuracion;

    private LocalDate fecha;

    private String descripcion;

    @ManyToMany
    @JoinTable(
        name = "viaje_destino",
        joinColumns = @JoinColumn(name = "id_viaje"),
        inverseJoinColumns = @JoinColumn(name = "id_destino")
    )
    private List<Destino> destinos;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Promocion> promociones;
}
