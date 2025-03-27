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
public class TripDTO {
    
    private Long id;
    private String name;
    private String departureLocation;
    private Long destinationId;
    private float price;
    private int durationDays;
    private LocalDate date;
    private String description;

    public TripDTO(Long id, String name, String departureLocation, Long destinationId, float price, int durationDays, LocalDate date, String description) {
        this.id = id;
        this.name = name;
        this.departureLocation = departureLocation;
        this.destinationId = destinationId;
        this.price = price;
        this.durationDays = durationDays;
        this.date = date;
        this.description = description;
    }
}
