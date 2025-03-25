package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Viaje;
import org.springframework.data.repository.CrudRepository;

public interface TripCrudRepository extends CrudRepository<Viaje, Long>{
}
