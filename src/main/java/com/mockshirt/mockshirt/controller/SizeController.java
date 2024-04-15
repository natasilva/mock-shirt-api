package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/sizes")
public class SizeController {
    // TODO: Ajustar Controller
    @GetMapping
    public String list() {
        return "tamanhos de uniforme";
    }

}
