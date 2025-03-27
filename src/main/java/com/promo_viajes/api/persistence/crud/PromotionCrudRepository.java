package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PromotionCrudRepository extends CrudRepository<Promocion, Long>{

    // Consultar todas las promociones activas
    @Query(value = "SELECT * FROM promocion WHERE fecha_inicio <= CURDATE() AND fecha_fin >= CURDATE()", nativeQuery = true)
    Iterable<Promocion> findAllActivesPromotions();

    // Counsultar todas la promociones por un viaje
    @Query(value = "SELECT * FROM promocion WHERE id_viaje = :idViaje", nativeQuery = true)
    Iterable<Promocion> findAllPromotionsByTrip(Long idViaje);

    @Query(value = "SELECT v.precio * (1.00 - (p.porcentaje_descuento / 100.00)) " +
            "FROM viaje v " +
            "JOIN promocion p ON v.id_viaje = p.id_viaje " +
            "WHERE v.id_viaje = :idViaje AND p.id_promo = :idPromocion",
            nativeQuery = true)
    float calculatePriceWithDiscount(@Param("idViaje") Long idViaje, @Param("idPromocion") Long idPromocion);

}
