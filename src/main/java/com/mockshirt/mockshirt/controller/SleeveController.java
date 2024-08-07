package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.mockshirt.mockshirt.entity.Sleeve;
import com.mockshirt.mockshirt.service.interfaces.IService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/sleeves")
public class SleeveController {
    private final IService<Sleeve> sleeveService;

    public SleeveController(@Qualifier("SleeveService") IService<Sleeve> sleeveService) {
        this.sleeveService = sleeveService;
    }

    @GetMapping
    public ResponseEntity<List<Sleeve>> list() {
        List<Sleeve> result = this.sleeveService.list();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
