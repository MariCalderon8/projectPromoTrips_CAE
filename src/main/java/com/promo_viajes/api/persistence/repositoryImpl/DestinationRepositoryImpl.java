package com.promo_viajes.api.persistence.repositoryImpl;

import com.promo_viajes.api.domain.dto.DestinationDTO;
import com.promo_viajes.api.domain.repository.DestinationRepository;
import com.promo_viajes.api.persistence.crud.DestinationCrudRepository;
import com.promo_viajes.api.persistence.entity.Destino;
import com.promo_viajes.api.persistence.mapper.DestinationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    @Autowired
    private DestinationCrudRepository destinationCrudRepository;

    @Autowired
    private DestinationMapper destinationMapper;

    @Override
    public Iterable<DestinationDTO> findAll() {
        Iterable<Destino> destinations = destinationCrudRepository.findAll();

        return ((List<Destino>) destinations).stream()
                .map(destinationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DestinationDTO> findById(Long id) {
        Optional<Destino> destination = destinationCrudRepository.findById(id);
        return destination.map(destinationMapper::toDto);
    }

    @Override
    public DestinationDTO save(DestinationDTO destinationDTO) {
        if (destinationDTO.getId() == null) {
            Destino destination = destinationMapper.toEntity(destinationDTO);
            Destino savedDestination = destinationCrudRepository.save(destination);
            return destinationMapper.toDto(savedDestination);
        }
        throw new IllegalArgumentException("El registro no es valido");
    }

    @Override
    public DestinationDTO update(DestinationDTO destinationDTO) {
        Destino destination = destinationMapper.toEntity(destinationDTO);
        if(existsById(destination.getDestinoId())){
            Destino updatedDestination = destinationCrudRepository.save(destination);
            return destinationMapper.toDto(updatedDestination);
        }
        throw new IllegalArgumentException("El registro no existe");
    }

    @Override
    public boolean existsById(Long id) {
        return destinationCrudRepository.existsById(id);
    }

    @Override
    public boolean delete(Long id) {
        if(existsById(id)){
            destinationCrudRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long count() {
        return destinationCrudRepository.count();
    }

    @Override
    public List<DestinationDTO> findAllDestinationsByTrip(Long tripId) {
        return destinationCrudRepository.findAllDestinationsByTrip(tripId).stream()
                .map(destinationMapper::toDto)
                .collect(Collectors.toList());
    }
}
