package com.mockshirt.mockshirt.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mockshirt.mockshirt.builder.interfaces.IShirtBuilder;
import com.mockshirt.mockshirt.builder.interfaces.IShirtImageBuilder;
import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.service.ImageService;

import java.awt.image.BufferedImage;
import java.sql.Blob;

@Component
public class ShirtBuilder implements IShirtBuilder {
    @Autowired
    private ImageService imageService;

    @Autowired
    private IShirtImageBuilder shirtImageBuilder;

    private BufferedImage logo;
    private boolean sleeveLogo;
    private String frontUrl;
    private String backUrl;

    public ShirtBuilder() {
    }

    public ShirtBuilder builder() {
        return new ShirtBuilder();
    }

    public Shirt build() {
        Blob back = getBackImage();
        Blob front = getFrontImage();
        float value = getValue(null);

        return new Shirt(back, front, value);
    }

    public ShirtBuilder setLogo(byte[] logo) {
        this.logo = imageService.convertToBufferedImage(logo);
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
            BufferedImage shape = imageService.downloadImage(backUrl);

            Blob shirtImage = shirtImageBuilder.builder()
                    .setLogo(logo)
                    .setShape(shape)
                    .withBackLogo()
                    .getBlob();

            return shirtImage;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public Blob getFrontImage() {
        try {
            BufferedImage shape = imageService.downloadImage(frontUrl);

            Blob shirtImage = shirtImageBuilder.builder()
                    .setLogo(logo)
                    .setShape(shape)
                    .withFrontLogo()
                    .withLeftSleeveLogo() // TODO: verificar se deve ou não adicionar logo na manga
                    .withRightSleeveLogo() // TODO: verificar se deve ou não adicionar logo na manga
                    .getBlob();

            return shirtImage;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    private float getValue(Object data) {
        return 30.50f; // TODO: Fazer lógica para gerar orçamento
    }
}
