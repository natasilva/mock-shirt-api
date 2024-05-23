package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mockshirt.mockshirt.service.interfaces.IService;
import com.mockshirt.mockshirt.entity.Collar;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/collars")
public class CollarController {
    private final IService<Collar> collarService;

    public CollarController(@Qualifier("CollarService") IService<Collar> collarService) {
        this.collarService = collarService;
    }

    @GetMapping
    public List<Collar> list() {
        List<Collar> result = this.collarService.list();
        return result;
    }

}
