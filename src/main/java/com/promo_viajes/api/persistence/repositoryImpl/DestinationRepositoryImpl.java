package com.promo_viajes.api.persistence.repositoryImpl;

import com.promo_viajes.api.domain.dto.DestinationDTO;
import com.promo_viajes.api.domain.repository.DestinationRepository;
import com.promo_viajes.api.persistence.crud.DestinationCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    @Autowired
    private DestinationCrudRepository destinationCrudRepository;

    @Override
    public Iterable<DestinationDTO> findAll() {
        return null;
    }

    @Override
    public Optional<DestinationDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DestinationDTO save(DestinationDTO destinationDTO) {
        return null;
    }

    @Override
    public DestinationDTO update(DestinationDTO destinationDTO) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public Long count() {
        return 0L;
    }
}
