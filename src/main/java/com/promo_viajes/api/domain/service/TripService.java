package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.DestinationDTO;
import com.promo_viajes.api.domain.dto.TripDTO;
import com.promo_viajes.api.domain.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired DestinationService destinationService;

    public Iterable<TripDTO> findAll(){
        return tripRepository.findAll();
    }

    public Optional<TripDTO> findById(Long id) {
        return tripRepository.findById(id);
    }

    public TripDTO save(TripDTO tripDTO){
        return tripRepository.save(tripDTO);
    }

    public TripDTO update(TripDTO tripDTO) {
        return tripRepository.update(tripDTO);
    }

    public boolean delete(Long id){
        return tripRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return tripRepository.existsById(id);
    }

    // Consultar todos los viajes a un precio específico o menor
    public Iterable<TripDTO> findTripsByPriceOrLower(float price) {
        return  tripRepository.findTripsByPriceOrLower(price);
    }

    // Consultar viajes en un fecha especifica
    public Iterable<TripDTO> findTripsByDate(LocalDate date) {
        return tripRepository.findTripsByDate(date);
    }

    public long count() {
        return  tripRepository.count();
    }

//    public boolean addNewDestination(Long tripId, Long destinationId) {
//
//        if(existsById(tripId) && destinationService.existsById(destinationId)){
//            if (validateDestinationInTrip(tripId, destinationId)){
//                throw new IllegalArgumentException("El destino ya existe dentro del viaje");
//            }
//
//            return tripRepository.addNewDestination(tripId, destinationId);
//        }
//        throw new IllegalArgumentException("No existe el viaje o el destino");
//    }
//
//    public boolean removeDestination(Long tripId, Long destinationId) {
//        if(existsById(tripId) && destinationService.existsById(destinationId)){
//
//            if (validateDestinationInTrip(tripId, destinationId)){
//                throw new IllegalArgumentException("El destino ya existe dentro del viaje");
//            }
//
//            return tripRepository.removeDestination(tripId, destinationId);
//        }
//        throw new IllegalArgumentException("No existe el viaje o el destino");
//
//    }
//
//    public boolean validateDestinationInTrip(Long tripId, Long destinationId) {
//        List<DestinationDTO> destinations = destinationService.findAllDestinationsByTrip(tripId);
//        return destinations.stream().anyMatch(d -> d.getId().equals(destinationId));
//    }
}
