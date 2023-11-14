package com.ea.dataone.service;

import com.ea.dataone.dto.AddressDto;
import com.ea.dataone.entity.Address;

import java.util.List;

public interface AddressService {
    void create(AddressDto address);

    List<AddressDto> findAll();

    void update(AddressDto address);

    AddressDto getAddress(Long id);

    void delete(AddressDto address);
}
