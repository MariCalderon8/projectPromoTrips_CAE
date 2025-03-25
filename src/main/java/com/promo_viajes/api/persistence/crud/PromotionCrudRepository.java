package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Promocion;
import org.springframework.data.repository.CrudRepository;

public interface PromotionCrudRepository extends CrudRepository<Promocion, Long>{
}
