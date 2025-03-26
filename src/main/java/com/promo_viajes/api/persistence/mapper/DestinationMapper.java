package com.promo_viajes.api.persistence.mapper;

import com.promo_viajes.api.domain.dto.DestinationDTO;
import com.promo_viajes.api.persistence.entity.Destino;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DestinationMapper {

    @Mapping(source = "destinoId", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "ciudad", target = "city")
    @Mapping(source = "pais", target = "country")
    @Mapping(source = "clima", target = "climate")
    @Mapping(source = "descripcion", target = "description")
    DestinationDTO toDto(Destino destino);

    @InheritInverseConfiguration
    @Mapping(target = "viajes", ignore = true) // Ignorar para evitar ciclos
    Destino toEntity(DestinationDTO destinationDTO);
}
