package com.mockshirt.mockshirt.builder.interfaces;

import java.sql.Blob;

import org.springframework.web.multipart.MultipartFile;

import com.mockshirt.mockshirt.builder.ShirtBuilder;
import com.mockshirt.mockshirt.entity.Shirt;

public interface IShirtBuilder {
    ShirtBuilder builder();

    Shirt build();

    ShirtBuilder setFrontLogo(MultipartFile logo);

    ShirtBuilder setBackLogo(MultipartFile logo);

    ShirtBuilder setShapeBackUrl(String backUrl);

    ShirtBuilder setShapeFrontUrl(String frontUrl);

    ShirtBuilder setLogoColorsQuantity(int logoColorsQuantity);

    ShirtBuilder setMaterial(String material);

    ShirtBuilder setSleeve(String sleeve);

    ShirtBuilder hasSleeveLogo(boolean sleeveLogo);

    Blob getBackImage();

    Blob getFrontImage();
}
