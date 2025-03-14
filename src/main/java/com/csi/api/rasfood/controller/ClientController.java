package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.entity.Client;
import com.csi.api.rasfood.entity.ClientId;
import com.csi.api.rasfood.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/client")
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    private ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    @GetMapping("/{cpf}/{email}")
    private ResponseEntity<Optional<Client>> findById(@PathVariable("cpf") String cpf, @PathVariable("email") String email) {
        ClientId id = new ClientId(cpf, email);
        Optional<Client> client = clientRepository.findById(id);
        if(!client.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findById(client.get().getClientId()));
    }
}
