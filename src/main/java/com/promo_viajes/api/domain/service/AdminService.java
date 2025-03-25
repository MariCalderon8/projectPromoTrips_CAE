package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.AdminDTO;
import com.promo_viajes.api.domain.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

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
        if(isValidEmail(adminDTO.getEmail())) {
            return adminRepository.save(adminDTO);
        } else {
            throw new RuntimeException("El email no es valido");
        }
    }

    // Actualizar un registro
    public AdminDTO update(AdminDTO adminDTO) {
        if(isValidEmail(adminDTO.getEmail())) {
            return adminRepository.update(adminDTO);
        } else {
            throw new RuntimeException("El email no es valido");
        }
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

    // Validar correo electronico
    public static boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        if (email == null || email.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }
}
