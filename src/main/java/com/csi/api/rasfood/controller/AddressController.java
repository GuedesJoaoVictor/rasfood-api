package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.entity.Address;
import com.csi.api.rasfood.repository.AddressRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/address")
@RestController
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjectMapper mapper;

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(addressRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        return addressRepository.findById(id).map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable("id") Long id, @RequestBody Address address) throws JsonMappingException {
        Optional<Address> currentAddress = addressRepository.findById(id);
        if (currentAddress.isPresent()) {
            Address existingAddress = currentAddress.get();
            address.setId(existingAddress.getId());
            mapper.updateValue(existingAddress, address);
            return ResponseEntity.status(HttpStatus.OK).body(addressRepository.save(existingAddress));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
