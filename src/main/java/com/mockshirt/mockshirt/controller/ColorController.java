package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/colors")
public class ColorController {
    // TODO: Ajustar Controller
    @GetMapping
    public String list() {
        return "Cores de uniforme";
    }

}
