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
    private String country;
    private String climate;

    public DestinationDTO(Long id, String name, String country, String climate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.climate = climate;
    }
}
