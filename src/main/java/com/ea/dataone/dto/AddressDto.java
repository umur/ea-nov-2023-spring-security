package com.ea.dataone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private Long id;
    private String street;
    private String zip;
    private String city;
}
