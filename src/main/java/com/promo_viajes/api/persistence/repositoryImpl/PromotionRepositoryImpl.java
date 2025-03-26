package com.promo_viajes.api.persistence.repositoryImpl;

import com.promo_viajes.api.domain.dto.PromotionDTO;
import com.promo_viajes.api.domain.repository.PromotionRepository;
import com.promo_viajes.api.persistence.crud.PromotionCrudRepository;
import com.promo_viajes.api.persistence.entity.Promocion;
import com.promo_viajes.api.persistence.mapper.PromotionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PromotionRepositoryImpl implements PromotionRepository {

    @Autowired
    private PromotionCrudRepository promotionCrudRepository;

    @Autowired
    private PromotionMapper promotionMapper;

    @Override
    public Iterable<PromotionDTO> findAll() {
        Iterable<Promocion> promotions = promotionCrudRepository.findAll();

        return ((List<Promocion>) promotions).stream()
                .map(promotionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PromotionDTO> findById(Long id) {
        Optional<Promocion> promotion = promotionCrudRepository.findById(id);
        return promotion.map(promotionMapper::toDto);
    }

    @Override
    public PromotionDTO save(PromotionDTO promotionDTO) {
        if(promotionDTO.getId() == null) {
            Promocion promotion = promotionMapper.toEntity(promotionDTO);
            Promocion savedPromotion = promotionCrudRepository.save(promotion);
            return promotionMapper.toDto(savedPromotion);
        }
        throw new IllegalArgumentException("Registro no valido");
    }

    @Override
    public PromotionDTO update(PromotionDTO promotionDTO) {
        Promocion promotion = promotionMapper.toEntity(promotionDTO);
        if(existsById(promotion.getPromoId())) {
            Promocion updatedPromotion = promotionCrudRepository.save(promotion);
            return promotionMapper.toDto(updatedPromotion);
        }
        throw new IllegalArgumentException("El registro no existe");
    }

    @Override
    public boolean delete(Long id) {
        if(existsById(id)) {
            promotionCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return promotionCrudRepository.existsById(id);
    }

    @Override
    public float calcuteTripUpdatedPrice(Long tripId, Long promotionId) {
        return promotionCrudRepository.calculatePriceWithDiscount(tripId, promotionId);
    }

    @Override
    public Iterable<PromotionDTO> findAllActivesPromotions() {
        Iterable<Promocion> promotions = promotionCrudRepository.findAllActivesPromotions();

        return ((List<Promocion>) promotions).stream()
                .map(promotionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<PromotionDTO> findAllPromotionsByTrip(Long tripId) {
        Iterable<Promocion> promotions = promotionCrudRepository.findAllPromotionsByTrip(tripId);

        return ((List<Promocion>) promotions).stream()
                .map(promotionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return promotionCrudRepository.count();
    }
}
