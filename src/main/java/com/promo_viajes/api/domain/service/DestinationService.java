package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.DestinationDTO;
import com.promo_viajes.api.domain.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    public Iterable<DestinationDTO> findAll(){
        return destinationRepository.findAll();
    }

    public Optional<DestinationDTO> findById(Long id) {
        return destinationRepository.findById(id);
    }

    public DestinationDTO save(DestinationDTO destinationDTO){
        return destinationRepository.save(destinationDTO);
    }

    public DestinationDTO update(DestinationDTO destinationDTO) {
        return destinationRepository.update(destinationDTO);
    }

    public boolean delete(Long id){
        return destinationRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return destinationRepository.existsById(id);
    }

    public long count() {
        return  destinationRepository.count();
    }
}
