package com.promo_viajes.api.persistence.entity;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @Column(name = "lugar_salida")
    private String lugarSalida;

    @ManyToOne
    @JoinColumn (name = "id_destino", nullable = false)
    private Destino destino;

    private float precio;

    @Column(name = "dias_duracion")
    private int diasDuracion;

    private LocalDate fecha;

    private String descripcion;

    @OneToMany(mappedBy = "viaje")
    private List<Promocion> promociones;
}
