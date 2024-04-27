
package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.form.FormData;
import com.mockshirt.mockshirt.service.interfaces.IService;
import com.mockshirt.mockshirt.service.interfaces.IShirtService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/image-process")
public class ShirtController {
    private final IShirtService shirtService;

    public ShirtController(@Qualifier("ShirtService") IShirtService shirtService) {
        this.shirtService = shirtService;
    }

    @PostMapping(value = "/", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<Shirt> processFile(@ModelAttribute FormData formData) throws IOException {

        List<Shirt> shirts = shirtService.list(formData);

        return shirts;
    }
}