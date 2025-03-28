package com.promo_viajes.api.domain.service;

import com.promo_viajes.api.domain.dto.PromotionDTO;
import com.promo_viajes.api.domain.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TripService tripService;

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
        validatePromotionData(promotionDTO);
        return promotionRepository.save(promotionDTO);
    }

    public PromotionDTO update(PromotionDTO promotionDTO) {
        validatePromotionData(promotionDTO);
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

    // Valiidaciones

    public void validatePromotionData(PromotionDTO promotionDTO) {
        if(!adminService.existsById(promotionDTO.getCreatedBy())) {
            throw new IllegalArgumentException("El administrador no existe");
        }
        if (!tripService.existsById(promotionDTO.getTripId())) {
            throw new IllegalArgumentException("El viaje no exite");
        }
        if (!isValidDateRange(promotionDTO.getStartDate(), promotionDTO.getEndDate())) {
            throw new IllegalArgumentException("El rango de fechas no es válido");
        }
        if (!isDiscountValid(promotionDTO.getDiscount())){
            throw new IllegalArgumentException("El valor del descuento no es válido");
        }
    }

    public boolean isValidDateRange(LocalDate startDate, LocalDate endDate) {
        LocalDate today = LocalDate.now();
        return startDate != null && endDate != null
                && !startDate.isBefore(today)
                && startDate.isBefore(endDate);
    }

    public boolean isDiscountValid(float discount) {
        return discount > 0 && discount <= 100.00;
    }

}
