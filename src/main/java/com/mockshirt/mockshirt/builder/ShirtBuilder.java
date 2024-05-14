package com.mockshirt.mockshirt.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mockshirt.mockshirt.builder.interfaces.IShirtBuilder;
import com.mockshirt.mockshirt.builder.interfaces.IShirtImageBuilder;
import com.mockshirt.mockshirt.builder.interfaces.IShirtValueBuilder;
import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.repository.MaterialRepository;
import com.mockshirt.mockshirt.repository.SizeRepository;
import com.mockshirt.mockshirt.repository.SleeveRepository;
import com.mockshirt.mockshirt.service.interfaces.IImageService;

import java.awt.image.BufferedImage;
import java.sql.Blob;

@Component
public class ShirtBuilder implements IShirtBuilder {
    @Autowired
    private IImageService imageService;

    @Autowired
    private IShirtValueBuilder shirtValueBuilder;

    @Autowired
    private IShirtImageBuilder shirtImageBuilder;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private SleeveRepository sleeveRepository;

    private BufferedImage logo;
    private int logoColorsQuantity;
    private boolean sleeveLogo;
    private String material;
    private String size;
    private String sleeve;

    private String frontUrl;
    private String backUrl;

    public ShirtBuilder() {
    }

    public ShirtBuilder builder() {
        return new ShirtBuilder();
    }

    public Shirt build() {
        float value = getValue();
        Blob back = getBackImage();
        Blob front = getFrontImage();

        return new Shirt(back, front, value);
    }

    public ShirtBuilder setLogo(MultipartFile logo) {
        this.logo = this.imageService.convertToBufferedImage(logo);
        return this;
    }

    public ShirtBuilder setLogoColorsQuantity(int logoColorsQuantity) {
        this.logoColorsQuantity = logoColorsQuantity;
        return this;
    }

    public ShirtBuilder setMaterial(String material) {
        this.material = material;
        return this;
    }

    public ShirtBuilder setSize(String size) {
        this.size = size;
        return this;
    }

    public ShirtBuilder setSleeve(String sleeve) {
        this.sleeve = sleeve;
        return this;
    }

    public ShirtBuilder setSleeveLogo(boolean sleeveLogo) {
        this.sleeveLogo = sleeveLogo;
        return this;
    }

    public ShirtBuilder setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
        return this;
    }

    public ShirtBuilder setBackUrl(String backUrl) {
        this.backUrl = backUrl;
        return this;
    }

    public Blob getBackImage() {
        try {
            // BufferedImage shape = imageService.downloadImage(backUrl);

            BufferedImage shapeImage = imageService.loadImage(backUrl);

            Blob shirtImage = shirtImageBuilder
                    .setLogo(logo)
                    .setShape(shapeImage)
                    .withBackLogo()
                    .getBlob();

            return shirtImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Blob getFrontImage() {
        try {
            // BufferedImage shape = imageService.downloadImage(frontUrl);

            BufferedImage shapeImage = imageService.loadImage(frontUrl);

            Blob shirtImage = shirtImageBuilder
                    .setLogo(logo)
                    .setShape(shapeImage)
                    .withFrontLogo()
                    .withLeftSleeveLogo(this.sleeveLogo)
                    .withRightSleeveLogo(this.sleeveLogo)
                    .getBlob();

            return shirtImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private float getValue() {
        float sizeValue = sizeRepository.findByName(this.size).getValue();
        float materialValue = materialRepository.findByKey(this.material).getValue();
        float sleeveValue = sleeveRepository.findByKey(this.sleeve).getValue();

        return shirtValueBuilder
                .setSizeValue(sizeValue)
                .setMaterialValue(materialValue)
                .setSleeveValue(sleeveValue)
                .setSleeveLogo(sleeveLogo)
                .setLogoColorsQuantity(logoColorsQuantity)
                .calculateValue();
    }
}
