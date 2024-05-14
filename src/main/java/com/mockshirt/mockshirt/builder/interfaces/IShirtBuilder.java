package com.mockshirt.mockshirt.builder.interfaces;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

import com.mockshirt.mockshirt.builder.ShirtBuilder;
import com.mockshirt.mockshirt.entity.Shirt;

public interface IShirtBuilder {
    ShirtBuilder builder();

    Shirt build();

    ShirtBuilder setLogo(MultipartFile logo);

    ShirtBuilder setBackUrl(String backUrl);

    ShirtBuilder setFrontUrl(String frontUrl);

    ShirtBuilder setLogoColorsQuantity(int logoColorsQuantity);

    ShirtBuilder setMaterial(String material);

    ShirtBuilder setSize(String size);

    ShirtBuilder setSleeve(String sleeve);

    ShirtBuilder setSleeveLogo(boolean sleeveLogo);

    Blob getBackImage();

    Blob getFrontImage();
}
