package com.promo_viajes.api.persistence.repositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.promo_viajes.api.domain.dto.AdminDTO;
import com.promo_viajes.api.domain.repository.AdminRepository;
import com.promo_viajes.api.persistence.crud.AdminCrudRepository;
import com.promo_viajes.api.persistence.entity.Admin;
import com.promo_viajes.api.persistence.mapper.AdminMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl implements AdminRepository{
    
    @Autowired
    private AdminCrudRepository adminCrudRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Iterable<AdminDTO> findAll() {
        Iterable<Admin> admins = adminCrudRepository.findAll(); 
        
        return ((List<Admin>) admins).stream()
                .map(adminMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdminDTO> findById(Long id) {
        Optional<Admin> admin = adminCrudRepository.findById(id);
        return admin.map(adminMapper::toDto);
    }

    @Override
    public AdminDTO save(AdminDTO adminDTO) {
        Admin admin = adminMapper.toEntity(adminDTO);
            Admin savedAdmin = adminCrudRepository.save(admin);
            return adminMapper.toDto(savedAdmin);
        
    }

    @Override
    public AdminDTO update(AdminDTO adminDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return adminCrudRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

}
