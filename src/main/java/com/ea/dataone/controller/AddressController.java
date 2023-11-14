package com.ea.dataone.controller;

import com.ea.dataone.dto.AddressDto;
import com.ea.dataone.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping
    public void create(@RequestBody AddressDto address) {
        addressService.create(address);
    }

    @GetMapping
    public List<AddressDto> getAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDto getAddressById(@PathVariable Long id) {
        return addressService.getAddress(id);
    }

    @PutMapping
    public void update(@RequestBody AddressDto address) {
        addressService.update(address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        AddressDto address = addressService.getAddress(id);
        addressService.delete(address);
    }
}