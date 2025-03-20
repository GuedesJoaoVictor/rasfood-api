package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.dto.MenuDto;
import com.csi.api.rasfood.entity.Menu;
import com.csi.api.rasfood.repository.MenuRepository;
import com.csi.api.rasfood.repository.projection.MenuProjection;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping
    public ResponseEntity<List<Menu>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findById(id).orElse(null));
    }

    @GetMapping("/category/{id}/available")
    public ResponseEntity<List<MenuProjection>> findAllByCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAllByCategory(id));
    }

    @GetMapping("/name/{name}/available")
    public ResponseEntity<List<MenuDto>> findAllByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAllByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Menu> deleteById(@PathVariable("id") Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
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

        if (currentMenu.isPresent()) {
            Menu existingMenu = currentMenu.get();
            mapper.updateValue(existingMenu, menu);
            return ResponseEntity.status(HttpStatus.OK).body(menuRepository.save(existingMenu));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
