package com.promo_viajes.api.persistence.crud;

import com.promo_viajes.api.persistence.entity.Destino;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinationCrudRepository extends CrudRepository<Destino, Long>{

    @Query(value = "SELECT d.id_destino, d.nombre, d.ciudad, d.pais, d.clima, d.descripcion\n" +
            "FROM destino d\n" +
            "JOIN viaje_destino vd ON d.id_destino = vd.id_destino\n" +
            "JOIN viaje v ON v.id_viaje = vd.id_viaje\n" +
            "WHERE v.id_viaje = :idViaje;", nativeQuery = true)
    List<Destino> findAllDestinationsByTrip(@Param("idViaje") Long idViaje);
}
