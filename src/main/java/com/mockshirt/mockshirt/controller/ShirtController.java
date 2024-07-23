
package com.mockshirt.mockshirt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.form.FormData;
import com.mockshirt.mockshirt.service.interfaces.IShirtService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/shirt")
public class ShirtController {
    private final IShirtService shirtService;

    public ShirtController(@Qualifier("ShirtService") IShirtService shirtService) {
        this.shirtService = shirtService;
    }

    @PostMapping(value = "/image-process", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Shirt>> processFile(@ModelAttribute FormData formData) throws IOException {

        List<Shirt> shirts = shirtService.list(formData);

        return new ResponseEntity<>(shirts, HttpStatus.OK);
    }
}
