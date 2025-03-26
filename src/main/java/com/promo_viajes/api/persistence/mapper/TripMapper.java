package com.promo_viajes.api.persistence.mapper;

import com.promo_viajes.api.domain.dto.TripDTO;
import com.promo_viajes.api.persistence.entity.Viaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(source = "viajeId", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "diasDuracion", target = "daysDuration")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "descripcion", target = "description")
    TripDTO toDto(Viaje viaje);

    @InheritInverseConfiguration
    @Mapping(target = "promociones", ignore = true)
    @Mapping(target = "destinos", ignore = true)
    Viaje toEntity(TripDTO tripDTO);
}
