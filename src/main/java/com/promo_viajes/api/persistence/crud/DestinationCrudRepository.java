package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Destino;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinationCrudRepository extends CrudRepository<Destino, Long>{
}
