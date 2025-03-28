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
        validateTripData(tripDTO);
        return tripRepository.save(tripDTO);
    }

    public TripDTO update(TripDTO tripDTO) {
        if(tripDTO.getPrice() <= 0){
            throw new IllegalArgumentException("El precio no es válido");
        }
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

    void validateTripData(TripDTO tripDTO) {
        LocalDate today = LocalDate.now();
        if(tripDTO.getDate() == null || tripDTO.getDate().isBefore(today)) {
            throw new IllegalArgumentException("La fecha no es válida. La fecha no puede estar vacía o ser antes de hoy");
        }
        if(tripDTO.getPrice() <= 0){
            throw new IllegalArgumentException("El precio no es válido");
        }
        if(!destinationService.existsById(tripDTO.getDestinationId())) {
            throw new IllegalArgumentException("El destino no existe");
        }
    }

}
