package com.promo_viajes.api.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PromotionDTO {
    
    private Long id;
    private Long createdBy;
    private float discount;
    private Long tripId;
    private float tripFinalPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String conditions;
    private String description;

    public PromotionDTO(Long id, Long createdBy, float discount, Long tripId, LocalDate startDate, LocalDate endDate, String conditions, String description) {
        this.id = id;
        this.createdBy = createdBy;
        this.discount = discount;
        this.tripId = tripId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.conditions = conditions;
        this.description = description;
    }
    
}
