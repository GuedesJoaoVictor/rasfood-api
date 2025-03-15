package com.csi.api.rasfood.repository;

import com.csi.api.rasfood.entity.Client;
import com.csi.api.rasfood.entity.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientId> {

    @Query("select c from Client c where c.clientId.email = :id or c.clientId.cpf = :id")
    Optional<Client> findByEmailOrCpf(@Param("id") String id);

}
