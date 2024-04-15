package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mockshirt.mockshirt.service.interfaces.IService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/shapes")
public class ShapeController {
    // TODO: Ajustar Controller
    // private final IService shapeService;

    // public ShapeController(@Qualifier("ShapeService") IService shapeService) {
    // this.shapeService = shapeService;
    // }

    @GetMapping
    public String list() {
        // String result = this.shapeService.list();
        // return result;
        return "modelos de uniforme";
    }

}
