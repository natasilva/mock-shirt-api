package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mockshirt.mockshirt.entity.Material;
import com.mockshirt.mockshirt.service.interfaces.IService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private final IService materialService;

    public MaterialController(@Qualifier("MaterialService") IService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<Material> list() {
        List<Material> result = this.materialService.list();
        return result;
    }
}
