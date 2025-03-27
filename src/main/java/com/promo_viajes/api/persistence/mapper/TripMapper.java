package com.promo_viajes.api.persistence.mapper;

import com.promo_viajes.api.domain.dto.TripDTO;
import com.promo_viajes.api.persistence.entity.Destino;
import com.promo_viajes.api.persistence.entity.Viaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(source = "viajeId", target = "id")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "lugarSalida", target = "departureLocation")
    @Mapping(source = "destino", target = "destinationId")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "diasDuracion", target = "durationDays")
    @Mapping(source = "fecha", target = "date")
    @Mapping(source = "descripcion", target = "description")
    TripDTO toDto(Viaje viaje);

    @InheritInverseConfiguration
    @Mapping(source = "destinationId", target = "destino")
    @Mapping(target = "promociones", ignore = true)
    Viaje toEntity(TripDTO tripDTO);

    //Convertir de Destino a Long
    default Long map(Destino destino) {
        return  (destino != null) ? destino.getDestinoId() : null;
    }

    //Convertir de Long a Destino
    default Destino map(Long destinoId) {
        if (destinoId != null) {
            Destino destino = new Destino();
            destino.setDestinoId(destinoId);
            return destino;
        }
        return null;
    }
}
