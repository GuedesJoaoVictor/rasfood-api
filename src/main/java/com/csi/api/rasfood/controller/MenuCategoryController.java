package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.entity.MenuCategory;
import com.csi.api.rasfood.repository.MenuCategoryRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menu-category")
public class MenuCategoryController {
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping
    public ResponseEntity<List<MenuCategory>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(menuCategoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuCategory> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuCategoryRepository.findById(id).orElse(null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MenuCategory> deleteById(@PathVariable Long id) {
        Optional<MenuCategory> menuCategory = menuCategoryRepository.findById(id);
        if(menuCategory.isPresent()) {
            menuCategoryRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(menuCategory.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<MenuCategory> create(@RequestBody MenuCategory menuCategory) {
        menuCategoryRepository.save(menuCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuCategory);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MenuCategory> update(@PathVariable Long id, @RequestBody MenuCategory menuCategory) throws JsonMappingException {
        Optional<MenuCategory> currentCategory = menuCategoryRepository.findById(id);
        if(currentCategory.isPresent()) {
            MenuCategory existingCategory = currentCategory.get();
            mapper.updateValue(existingCategory, menuCategory);
            return ResponseEntity.status(HttpStatus.OK).body(menuCategoryRepository.save(existingCategory));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
