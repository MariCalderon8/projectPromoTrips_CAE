package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.PromotionDTO;
import com.promo_viajes.api.domain.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Iterable<PromotionDTO> findAll(){
        return promotionRepository.findAll();
    }

    public Optional<PromotionDTO> findById(Long id) {
        return promotionRepository.findById(id);
    }

    public PromotionDTO save(PromotionDTO promotionDTO){
        return promotionRepository.save(promotionDTO);
    }

    public PromotionDTO update(PromotionDTO promotionDTO) {
        return promotionRepository.update(promotionDTO);
    }

    public float calculateUpdatedTripPrice(Long tripId, Long promotionId) {
        return promotionRepository.calcuteTripUpdatedPrice(tripId, promotionId);
    }

    public Iterable<PromotionDTO> findAllActivesPromotions(){
        return promotionRepository.findAllActivesPromotions();
    }

    public Iterable<PromotionDTO> findAllPromotionsByTrip(Long tripId){
        return promotionRepository.findAllPromotionsByTrip(tripId);
    }

    public boolean delete(Long id){
        return promotionRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return promotionRepository.existsById(id);
    }

    public long count() {
        return  promotionRepository.count();
    }
}
