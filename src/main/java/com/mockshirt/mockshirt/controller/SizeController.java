package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import com.mockshirt.mockshirt.entity.Size;
import com.mockshirt.mockshirt.service.interfaces.IService;
@RestController
@RequestMapping("/sizes")
public class SizeController {
    private final IService sizeService;

    public SizeController(@Qualifier("SizeService") IService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping
    public List<Size> list() {
        List<Size> result = this.sizeService.list();
        return result;
    }
}
