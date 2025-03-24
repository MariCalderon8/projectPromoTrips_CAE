package com.promo_viajes.api.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AdminDTO {

    private Long id;
    private String name;
    private String password;
    private String document;
    private String address;
    private String phone;

    public AdminDTO(Long id, String name, String password, String document, String address, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.document = document;
        this.address = address;
        this.phone = phone;
    }
}
