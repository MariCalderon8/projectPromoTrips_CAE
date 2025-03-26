package com.promo_viajes.api.domain.repository;


import com.promo_viajes.api.domain.dto.PromotionDTO;

import java.util.Optional;

public interface PromotionRepository {
    // Consultar todos los registros
    Iterable<PromotionDTO> findAll();

    // Consultar por ID
    Optional<PromotionDTO> findById(Long id);

    // Guardar un registro
    PromotionDTO save(PromotionDTO promotionDTO);

    // Actualizar un registro
    PromotionDTO update(PromotionDTO promotionDTO);

    // Eliminar un registro
    boolean delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    // Calcular el valor del viaje con descuento
    float calcuteTripUpdatedPrice(Long tripId, Long promotionId);

    // Consultar todas las promociones activas
    Iterable<PromotionDTO> findAllActivesPromotions();

    // Consultar todas las promociones de un viajes
    Iterable<PromotionDTO> findAllPromotionsByTrip(Long tripId);

    // Contar todos los registros
    long count();
}
