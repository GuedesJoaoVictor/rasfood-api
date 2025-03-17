package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.entity.Menu;
import com.csi.api.rasfood.repository.MenuRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/menu")
@RestController
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<Menu>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> deleteById(@PathVariable("id") Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if(menu.isPresent()) {
            menuRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(menu.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody Menu menu) {
        menuRepository.save(menu);

        return ResponseEntity.status(HttpStatus.CREATED).body(menu);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Menu> update(@PathVariable("id") Long id, @RequestBody Menu menu) throws JsonMappingException {
        Optional<Menu> currentMenu = menuRepository.findById(id);

        if(currentMenu.isPresent()) {
            Menu existingMenu = currentMenu.get();
            existingMenu.setId(menu.getId());
            mapper.updateValue(existingMenu, menu);

            return ResponseEntity.status(HttpStatus.OK).body(existingMenu);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
