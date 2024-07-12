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

    private BufferedImage logo;
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

    public ShirtBuilder setSleeve(String typeSleeve) {
        this.typeSleeve = typeSleeve;
        return this;
    }

    public ShirtBuilder setCollar(String typeCollar) {
        this.typeCollar = typeCollar;
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
                    .hasLongSleeve("long".equals(this.typeSleeve))
                    .hasRoundCollar("round".equals(this.typeCollar))
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
        float materialValue = materialRepository.findByAcronym(this.material).getValue();
        float sleeveValue = sleeveRepository.findByAcronym(this.typeSleeve).getValue();

        return shirtValueBuilder
                .setMaterialValue(materialValue)
                .setSleeveValue(sleeveValue)
                .setSleeveLogo(sleeveLogo)
                .setLogoColorsQuantity(logoColorsQuantity)
                .calculateValue();
    }
}
