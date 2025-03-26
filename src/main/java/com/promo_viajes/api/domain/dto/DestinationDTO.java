package com.promo_viajes.api.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class DestinationDTO {
    
    private Long id;
    private String name;
    private String city;
    private String country;
    private String climate;
    private String description;

    public DestinationDTO(Long id, String name, String city, String country, String climate, String description) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.climate = climate;
        this.description = description;
    }
}
