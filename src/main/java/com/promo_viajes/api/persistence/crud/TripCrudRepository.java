package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Viaje;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface TripCrudRepository extends CrudRepository<Viaje, Long>{

    Iterable<Viaje> findByPrecioLessThanEqual(float price);

    Iterable<Viaje> findByFecha(LocalDate date);

}
