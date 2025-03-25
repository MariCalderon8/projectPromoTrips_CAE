package com.promo_viajes.api.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.promo_viajes.api.domain.dto.AdminDTO;
import com.promo_viajes.api.persistence.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    
    //Mapeo de Admin a AdminDTO
    @Mapping(source = "adminId", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "documento", target = "document")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phone")
    AdminDTO toDto(Admin admin);

    //Mapeo inverso
    @InheritInverseConfiguration
    Admin toEntity(AdminDTO adminDTO);
}
