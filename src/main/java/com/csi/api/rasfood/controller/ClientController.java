package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.entity.Client;
import com.csi.api.rasfood.entity.ClientId;
import com.csi.api.rasfood.repository.ClientRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/client")
@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll());
    }

    @GetMapping("/{cpf}/{email}")
    public ResponseEntity<Optional<Client>> findById(@PathVariable("cpf") String cpf, @PathVariable("email") String email) {
        ClientId id = new ClientId(cpf, email);
        Optional<Client> client = clientRepository.findById(id);
        return client.map(value -> ResponseEntity.status(HttpStatus.OK).body(clientRepository.findById(value.getClientId())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable("id") String id, @RequestBody Client client) throws JsonMappingException {
        Optional<Client> currentClient = clientRepository.findByEmailOrCpf(id);

        if(currentClient.isPresent()) {
            Client existingClient = currentClient.get();
            client.setClientId(existingClient.getClientId());
            mapper.updateValue(existingClient, client);
            return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(currentClient.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
