package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/collars")
public class CollarController {
    // TODO: Ajustar Controller
    @GetMapping
    public String list() {
        return "Golas de uniforme";
    }

}
