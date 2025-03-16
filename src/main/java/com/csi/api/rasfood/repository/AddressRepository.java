package com.csi.api.rasfood.repository;

import com.csi.api.rasfood.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address>findAddressByCep(String cep);
}
