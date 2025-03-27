package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Viaje;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TripCrudRepository extends CrudRepository<Viaje, Long>{

    Iterable<Viaje> findByPrecioLessThanEqual(float price);

    Iterable<Viaje> findByFecha(LocalDate date);

//    @Modifying
//    @Query(value = "INSERT INTO viaje_destino (viaje_id, destino_id) VALUES (:idViaje, :idDestino)", nativeQuery = true)
//    int addNewDestination(@Param("idViaje") Long idViaje, @Param("idDestino") Long idDestino);

}
