package com.promo_viajes.api.persistence.repositoryImpl;

import com.promo_viajes.api.domain.dto.TripDTO;
import com.promo_viajes.api.domain.repository.TripRepository;
import com.promo_viajes.api.persistence.crud.TripCrudRepository;
import com.promo_viajes.api.persistence.entity.Viaje;
import com.promo_viajes.api.persistence.mapper.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TripRepositoryImpl implements TripRepository {

    @Autowired
    private TripCrudRepository tripCrudRepository;

    @Autowired
    private TripMapper tripMapper;

    @Override
    public Iterable<TripDTO> findAll() {
        Iterable<Viaje> trips = tripCrudRepository.findAll();

        return ((List<Viaje>) trips)
                .stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TripDTO> findById(Long id) {
        Optional<Viaje> trip = tripCrudRepository.findById(id);
        return trip.map(tripMapper::toDto);
    }

    @Override
    public TripDTO save(TripDTO tripDTO) {
        if(tripDTO.getId() == null) {
            Viaje trip = tripMapper.toEntity(tripDTO);
            Viaje savedTrip = tripCrudRepository.save(trip);
            return tripMapper.toDto(savedTrip);
        }
        throw new IllegalArgumentException("Registro no valido");
    }

    @Override
    public TripDTO update(TripDTO tripDTO) {
        Viaje trip = tripMapper.toEntity(tripDTO);
        if(existsById(trip.getViajeId())) {
            Viaje updatedTrip = tripCrudRepository.save(trip);
            return tripMapper.toDto(updatedTrip);
        }
        throw new IllegalArgumentException("El registro no existe");
    }

    @Override
    public boolean delete(Long id) {
        if(existsById(id)) {
            tripCrudRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean existsById(Long id) {
        return tripCrudRepository.existsById(id);
    }

    @Override
    public Iterable<TripDTO> findTripsByPriceOrLower(float price) {
        Iterable<Viaje> trips = tripCrudRepository.findByPrecioLessThanEqual(price);

        return ((List<Viaje>) trips).stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<TripDTO> findTripsByDate(LocalDate date) {
        Iterable<Viaje> trips = tripCrudRepository.findByFecha(date);

        return ((List<Viaje>) trips).stream()
                .map(tripMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return tripCrudRepository.count();
    }
}
