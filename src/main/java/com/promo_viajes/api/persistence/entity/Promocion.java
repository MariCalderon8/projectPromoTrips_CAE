package com.promo_viajes.api.persistence.entity;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@Table(name = "promocion")
public class Promocion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promo")
    private Long promoId;

    @ManyToOne
    @JoinColumn(name = "creado_por", nullable = false)
    private Admin creadoPor;

    @Column(name = "porcentaje_descuento")
    private float descuento;

    @ManyToOne
    @JoinColumn(name = "id_viaje", nullable = false)
    private Viaje viaje;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    private String condiciones;

    private String descripcion;
}
