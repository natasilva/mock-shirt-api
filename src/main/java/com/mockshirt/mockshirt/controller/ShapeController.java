package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mockshirt.mockshirt.service.interfaces.IService;
import com.mockshirt.mockshirt.entity.Shape;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/shapes")
public class ShapeController {
    private final IService shapeService;

    public ShapeController(@Qualifier("ShapeService") IService shapeService) {
        this.shapeService = shapeService;
    }

    @GetMapping
    public List<Shape> list() {
        List<Shape> result = this.shapeService.list();
        return result;
    }

}
