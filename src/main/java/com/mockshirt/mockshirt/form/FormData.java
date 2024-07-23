package com.mockshirt.mockshirt.form;

import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FormData {
    private MultipartFile frontLogo;
    private MultipartFile backLogo;
    @Nullable
    private MultipartFile rightSleeveLogo;
    @Nullable
    private MultipartFile leftSleeveLogo;
    private String color;
    private String material;
    private String logoColorsQuantity;
    private String sleeveLogo;
}
