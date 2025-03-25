package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.AdminDTO;
import com.promo_viajes.api.domain.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminService {    
    
    @Autowired
    private AdminRepository adminRepository;
    
     // Consultar todos los registros
    public Iterable<AdminDTO> findAll(){
        return adminRepository.findAll();
    }

    // Consultar por ID
    public Optional<AdminDTO> findById(Long id) {
        return adminRepository.findById(id);
    }

    // Guardar un registro
    public AdminDTO save(AdminDTO adminDTO) {
        return adminRepository.save(adminDTO);
    }

    // Actualizar un registro
    public AdminDTO update(AdminDTO adminDTO) {
        return adminRepository.update(adminDTO);
    }

    // Eliminar un registro
    public boolean delete(Long id) {
        return adminRepository.delete(id);
    }

    // Validar si existe por ID
    public boolean existsById(Long id) {
        return adminRepository.existsById(id);
    }

    // Contar todos los registros
    public long count() {
        return adminRepository.count();
    }
}
