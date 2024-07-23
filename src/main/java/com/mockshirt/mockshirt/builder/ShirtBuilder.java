package com.mockshirt.mockshirt.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mockshirt.mockshirt.builder.interfaces.IShirtBuilder;
import com.mockshirt.mockshirt.builder.interfaces.IShirtImageBuilder;
import com.mockshirt.mockshirt.builder.interfaces.IShirtValueBuilder;
import com.mockshirt.mockshirt.entity.Collar;
import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.entity.Sleeve;
import com.mockshirt.mockshirt.repository.CollarRepository;
import com.mockshirt.mockshirt.repository.MaterialRepository;
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
    private MaterialRepository materialRepository;

    @Autowired
    private SleeveRepository sleeveRepository;

    @Autowired
    private CollarRepository collarRepository;

    private BufferedImage frontLogo;
    private BufferedImage backLogo;
    private BufferedImage rightSleeveLogo;
    private BufferedImage leftSleeveLogo;

    private int logoColorsQuantity;
    private boolean sleeveLogo;
    private String material;
    private String typeSleeve;
    private String typeCollar;

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

        Sleeve sleeve = sleeveRepository.findByAcronym(this.typeSleeve);
        Collar collar = collarRepository.findByAcronym(this.typeCollar);

        return new Shirt(back, front, value, collar, sleeve);
    }

    public ShirtBuilder setFrontLogo(MultipartFile logo) {
        this.frontLogo = this.imageService.convertToBufferedImage(logo);
        return this;
    }

    public ShirtBuilder setBackLogo(MultipartFile logo) {
        this.backLogo = this.imageService.convertToBufferedImage(logo);
        return this;
    }

    public ShirtBuilder setRightSleeveLogo(MultipartFile logo) {
        this.rightSleeveLogo = this.imageService.convertToBufferedImage(logo);
        return this;
    }

    public ShirtBuilder setLeftSleeveLogo(MultipartFile logo) {
        this.leftSleeveLogo = this.imageService.convertToBufferedImage(logo);
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

    public ShirtBuilder setSleeve(String typeSleeve) {
        this.typeSleeve = typeSleeve;
        return this;
    }

    public ShirtBuilder setCollar(String typeCollar) {
        this.typeCollar = typeCollar;
        return this;
    }

    public ShirtBuilder hasSleeveLogo(boolean sleeveLogo) {
        this.sleeveLogo = sleeveLogo;
        return this;
    }

    public ShirtBuilder setShapeFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
        return this;
    }

    public ShirtBuilder setShapeBackUrl(String backUrl) {
        this.backUrl = backUrl;
        return this;
    }

    public Blob getBackImage() {
        try {
            BufferedImage shapeImage = imageService.loadImage(this.backUrl);

            Blob shirtImage = shirtImageBuilder
                    // .setBackLogo(this.backLogo)
                    .setShape(shapeImage)
                    .withBackLogo(this.backLogo)
                    .getBlob();

            return shirtImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Blob getFrontImage() {
        try {
            BufferedImage shapeImage = imageService.loadImage(this.frontUrl);

            Blob shirtImage = shirtImageBuilder
                    .setShape(shapeImage)
                    .hasLongSleeve("long".equals(this.typeSleeve))
                    .hasRoundCollar("round".equals(this.typeCollar))
                    .withFrontLogo(this.frontLogo)
                    .withLeftSleeveLogo(this.leftSleeveLogo)
                    .withRightSleeveLogo(this.rightSleeveLogo)
                    .getBlob();

            return shirtImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private float getValue() {
        float materialValue = materialRepository.findByAcronym(this.material).getValue();
        float sleeveValue = sleeveRepository.findByAcronym(this.typeSleeve).getValue();

        return shirtValueBuilder
                .setMaterialValue(materialValue)
                .setSleeveValue(sleeveValue)
                .hasSleeveLogo(sleeveLogo)
                .setLogoColorsQuantity(logoColorsQuantity)
                .calculateValue();
    }
}
