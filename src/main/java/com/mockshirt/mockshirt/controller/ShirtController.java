
package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.service.interfaces.IService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/image-process")
public class ShirtController {
    private final IService<Shirt> shirtService;

    public ShirtController(@Qualifier("ShirtService") IService<Shirt> shirtService) {
        this.shirtService = shirtService;
    }

    @GetMapping("/")
    public List<Shirt> processFile(@RequestParam("file") MultipartFile file,
            @RequestParam("formData") String formData) {

        List<Shirt> shirts = shirtService.list();

        return shirts;
    }

}
