package com.promo_viajes.api.domain.repository;

import com.promo_viajes.api.domain.dto.AdminDTO;
import java.util.Optional;

public interface AdminRepository {
    
    // Consultar todos los registros
    Iterable<AdminDTO> findAll();

    // Consultar por ID
    Optional<AdminDTO> findById(Long id);

    // Guardar un registro
    AdminDTO save(AdminDTO adminDTO);

    // Actualizar un registro
    AdminDTO update(AdminDTO adminDTO);

    // Eliminar un registro
    void delete(Long id);

    // Validar si existe por ID
    boolean existsById(Long id);

    // Contar todos los registros
    long count();
}
