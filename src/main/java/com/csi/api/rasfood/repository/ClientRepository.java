package com.csi.api.rasfood.repository;

import com.csi.api.rasfood.entity.Client;
import com.csi.api.rasfood.entity.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, ClientId> {}
