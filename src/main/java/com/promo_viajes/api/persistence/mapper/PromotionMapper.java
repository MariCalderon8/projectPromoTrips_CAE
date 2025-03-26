package com.promo_viajes.api.persistence.mapper;

import com.promo_viajes.api.domain.dto.PromotionDTO;
import com.promo_viajes.api.persistence.entity.Admin;
import com.promo_viajes.api.persistence.entity.Promocion;
import com.promo_viajes.api.persistence.entity.Viaje;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PromotionMapper {

    @Mapping(source = "promoId", target = "id")
    @Mapping(source = "creadoPor", target = "createdBy")
    @Mapping(source = "descuento", target = "discount")
    @Mapping(source = "viaje.viajeId", target = "tripId")
    @Mapping(source = "fechaInicio", target = "startDate")
    @Mapping(source = "fechaFin", target = "endDate")
    @Mapping(source = "condiciones", target = "conditions")
    @Mapping(source = "descripcion", target = "description")
    PromotionDTO toDto(Promocion promocion);

    // Mapeo inverso de PromotionDTO a Promocion
    @InheritInverseConfiguration
    @Mapping(target = "viaje", source = "tripId")
    @Mapping(target = "creadoPor", source = "createdBy")
    Promocion toEntity(PromotionDTO promotionDTO);

    //Convertir de Admin a Long
    default Long map(Admin admin) {
        return (admin != null) ? admin.getAdminId() : null;
    }

    // Metodo explicito para convertir de Long a Admin
    default Admin map(Long adminId) {
        if (adminId != null) {
            Admin admin = new Admin();
            admin.setAdminId(adminId);
            return admin;
        }
        return null;
    }

    // Metodo explicito para convertir de Viaje a Long
    default Long map(Viaje viaje) {
        return (viaje != null) ? viaje.getViajeId() : null;
    }

    // Convertir de Long a Viaje
    default Viaje mapTrip(Long tripId) {
        if (tripId != null) {
            Viaje viaje = new Viaje();
            viaje.setViajeId(tripId);
            return viaje;
        }
        return null;
    }

}
