package com.csi.api.rasfood.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/menu")
@RestController
public class MenuController {

    @GetMapping(value = "/test")
    private String test() {
        return "I'm Alive!";
    }
}
