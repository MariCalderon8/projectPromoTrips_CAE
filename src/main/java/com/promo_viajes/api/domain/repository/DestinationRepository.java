package com.promo_viajes.api.domain.repository;

import com.promo_viajes.api.domain.dto.DestinationDTO;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository {

    //Encontrar todos los destinos
    Iterable<DestinationDTO> findAll();

    // Consultar un destino por ID
    Optional<DestinationDTO> findById(Long id);

    // Crear un nuevo destino
    DestinationDTO save(DestinationDTO destinationDTO);

    // Actualizar un destino
    DestinationDTO update(DestinationDTO destinationDTO);

    // Eliminar un destino
    boolean delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los destinos
    Long count();

}
