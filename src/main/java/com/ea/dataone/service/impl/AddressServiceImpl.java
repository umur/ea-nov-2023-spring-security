package com.ea.dataone.service.impl;

import com.ea.dataone.dto.AddressDto;
import com.ea.dataone.service.AddressService;
import com.ea.dataone.entity.Address;
import com.ea.dataone.repository.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final ModelMapper modelMapper;

    private final AddressRepo addressRepo;

    public void create(AddressDto address) {
        addressRepo.save(modelMapper.map(address, Address.class));
    }

    public List<AddressDto> findAll() {
        return addressRepo.findAll().stream()
                .map(address ->modelMapper.map(address, AddressDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void update(AddressDto address) {
        addressRepo.save(modelMapper.map(address, Address.class));
    }

    @Override
    public AddressDto getAddress(Long id) {
        Address address = addressRepo.findById(id).orElse(null);
        if(address == null) return null;
        return modelMapper.map(address, AddressDto.class);
    }

    @Override
    public void delete(AddressDto address) {
        addressRepo.delete(modelMapper.map(address, Address.class));
    }
}
