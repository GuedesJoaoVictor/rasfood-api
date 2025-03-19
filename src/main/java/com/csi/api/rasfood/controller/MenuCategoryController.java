package com.csi.api.rasfood.controller;

import com.csi.api.rasfood.entity.MenuCategory;
import com.csi.api.rasfood.repository.MenuCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu-category")
public class MenuCategoryController {
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<MenuCategory>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(menuCategoryRepository.findAll());
    }
}
