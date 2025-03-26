package com.promo_viajes.api.domain.repository;


import com.promo_viajes.api.domain.dto.TripDTO;

import java.time.LocalDate;
import java.util.Optional;

public interface TripRepository {
    // Consultar todos los registros
    Iterable<TripDTO> findAll();

    // Consultar por ID
    Optional<TripDTO> findById(Long id);

    // Guardar un registro
    TripDTO save(TripDTO tripDTO);

    // Actualizar un registro
    TripDTO update(TripDTO tripDTO);

    // Eliminar un registro
    boolean delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    // Consultar todos los viajes a un precio específico o menor
    Iterable<TripDTO> findTripsByPriceOrLower(float price);

    // Consultar viajes en un fecha especifica
    Iterable<TripDTO> findTripsByDate(LocalDate date);

    // Contar todos los registros
    long count();
}
