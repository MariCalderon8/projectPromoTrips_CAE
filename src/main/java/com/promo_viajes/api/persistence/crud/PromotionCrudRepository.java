package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PromotionCrudRepository extends CrudRepository<Promocion, Long>{

    // Consultar todas las promociones activas
    @Query(value = "SELECT * FROM promocion WHERE fecha_inicio >= CURDATE() AND fecha_fin <= CURDATE()", nativeQuery = true)
    Iterable<Promocion> findAllActivesPromotions();

    // Counsultar todas la promociones por un viaje
    Iterable<Promocion> findByViajeId(Long idViaje);

    @Query(value = "SELECT v.precio * (1 - (p.porcentaje_descuento / 100)) " +
            "FROM viaje v " +
            "JOIN promocion p ON v.id_viaje = p.id_viaje " +
            "WHERE v.id_viaje = :idViaje AND p.id_promocion = :idPromocion",
            nativeQuery = true)
    Double calcularPrecioConDescuento(@Param("idViaje") Long idViaje, @Param("idPromocion") Long idPromocion);

}
