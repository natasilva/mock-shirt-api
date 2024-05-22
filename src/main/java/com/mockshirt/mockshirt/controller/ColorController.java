package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mockshirt.mockshirt.service.interfaces.IService;
import com.mockshirt.mockshirt.entity.Color;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/colors")
public class ColorController {
    private final IService<Color> colorService;

    public ColorController(@Qualifier("ColorService") IService<Color> colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public List<Color> list() {
        List<Color> result = this.colorService.list();
        return result;
    }

}
