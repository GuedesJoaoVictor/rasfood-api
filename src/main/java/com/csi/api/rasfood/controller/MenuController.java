package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.dto.MenuDto;
import com.csi.api.rasfood.entity.Menu;
import com.csi.api.rasfood.repository.MenuRepository;
import com.csi.api.rasfood.repository.projection.MenuProjection;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ObjectMapper mapper;

//    @GetMapping
//    public ResponseEntity<List<Menu>> findAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAll());
//    }

    @GetMapping
    public ResponseEntity<Page<Menu>> findAll(@RequestParam("page") int page, @RequestParam("size") int size,
                                              @RequestParam(value = "sort", required = false) Sort.Direction sort,
                                              @RequestParam(value = "property", required = false) String property) {
        Pageable pageable = Objects.nonNull(sort)
                ? PageRequest.of(page, size, Sort.by(sort, property))
                : PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findById(id).orElse(null));
    }

    @GetMapping("/category/{id}/available")
    public ResponseEntity<Page<MenuProjection>> findAllByCategory(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAllByCategory(id, pageable));
    }

    @GetMapping("/name/{name}/available")
    public ResponseEntity<Page<MenuDto>> findAllByName(@PathVariable String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(menuRepository.findAllByName(name, pageable));
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
