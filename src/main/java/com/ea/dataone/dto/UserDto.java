package com.ea.dataone.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private AddressDto address;
    private List<ReviewDto> reviews;
}
