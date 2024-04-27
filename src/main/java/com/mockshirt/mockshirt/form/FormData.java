package com.mockshirt.mockshirt.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class FormData {
    private MultipartFile file;
    private String color;
    private String material;
    private String size;
    private String sleeveLogo;

    public FormData(MultipartFile file, String color, String material, String size, String sleeveLogo) {
        this.file = file;
        this.color = color;
        this.material = material;
        this.size = size;
        this.sleeveLogo = sleeveLogo;
    }
}