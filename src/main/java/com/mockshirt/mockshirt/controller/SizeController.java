package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.mockshirt.mockshirt.entity.Size;
import com.mockshirt.mockshirt.service.interfaces.IService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/sizes")
public class SizeController {
    private final IService<Size> sizeService;

    public SizeController(@Qualifier("SizeService") IService<Size> sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public ResponseEntity<List<Size>> list() {
        List<Size> result = this.sizeService.list();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
