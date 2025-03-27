package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.PromotionDTO;
import com.promo_viajes.api.domain.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    public Iterable<PromotionDTO> findAll(){
        Iterable<PromotionDTO> promotions = promotionRepository.findAll();
        List<PromotionDTO> updatedPromotions = new ArrayList<>();

        for (PromotionDTO promo : promotions) {
            float updatedPrice = calculateUpdatedTripPrice(promo.getTripId(), promo.getId());
            promo.setTripFinalPrice(updatedPrice);
            updatedPromotions.add(promo);
        }
        return updatedPromotions;
    }

    public Optional<PromotionDTO> findById(Long id) {
        return promotionRepository.findById(id)
                .map(promotionDTO -> {
                    promotionDTO.setTripFinalPrice(calculateUpdatedTripPrice(promotionDTO.getTripId(), promotionDTO.getId()));
                    return promotionDTO;
                });
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
        Iterable<PromotionDTO> promotions = promotionRepository.findAllActivesPromotions();
        List<PromotionDTO> activePromotions = new ArrayList<>();

        for (PromotionDTO promo : promotions) {
            float updatedPrice = calculateUpdatedTripPrice(promo.getTripId(), promo.getId());
            promo.setTripFinalPrice(updatedPrice);
            activePromotions.add(promo);
        }
        return activePromotions;
    }

    public Iterable<PromotionDTO> findAllPromotionsByTrip(Long tripId){
        Iterable<PromotionDTO> promotions = promotionRepository.findAllPromotionsByTrip(tripId);
        List<PromotionDTO> promosByTrip = new ArrayList<>();

        for (PromotionDTO promo : promotions) {
            float updatedPrice = calculateUpdatedTripPrice(promo.getTripId(), promo.getId());
            promo.setTripFinalPrice(updatedPrice);
            promosByTrip.add(promo);
        }
        return promosByTrip;
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
