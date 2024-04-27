package com.mockshirt.mockshirt.builder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.mockshirt.mockshirt.builder.interfaces.IShirtImageBuilder;
import com.mockshirt.mockshirt.service.ImageService;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShirtImageBuilder implements IShirtImageBuilder {
    @Autowired
    private ImageService imageService;

    private BufferedImage logo;
    private BufferedImage shape;
    private BufferedImage shirtImage;

    public ShirtImageBuilder builder() {
        return new ShirtImageBuilder();
    }

    public ShirtImageBuilder setLogo(BufferedImage logo) {
        this.logo = logo;
        return this;
    }

    public ShirtImageBuilder setShape(BufferedImage shape) {
        this.shape = shape;
        return this;
    }

    public ShirtImageBuilder withBackLogo() {
        int x = 150;
        int y = 150;

        BufferedImage logo = imageService.scaleImage(this.logo, 150, 150);
        this.shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());
        drawLogoWithPosition(logo, x, y, false);

        return this;
    }

    public ShirtImageBuilder withFrontLogo() {
        int x = 200;
        int y = 200;

        BufferedImage logo = imageService.scaleImage(this.logo, 50, 50);
        this.shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());
        drawLogoWithPosition(logo, x, y, false);

        return this;
    }

    public ShirtImageBuilder withRightSleeveLogo() {
        int x = 300;
        int y = 100;

        BufferedImage logo = imageService.scaleImage(this.logo, 50, 50);
        this.shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());
        drawLogoWithPosition(logo, x, y, true);

        return this;
    }

    public ShirtImageBuilder withLeftSleeveLogo() {
        int x = 50;
        int y = 100;

        BufferedImage logo = imageService.scaleImage(this.logo, 50, 50);
        this.shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());
        drawLogoWithPosition(logo, x, y, true);

        return this;
    }

    private void drawLogoWithPosition(BufferedImage logo, int x, int y, boolean rotate) {
        Graphics2D g2d = shirtImage.createGraphics();
        imageService.drawBaseImage(g2d, shirtImage);
        if (rotate) {
            imageService.rotateAndDrawLogo(g2d, logo, x, y);
        } else {
            imageService.drawLogo(g2d, logo, x, y);
        }
        g2d.dispose();
    }

    public Blob getBlob() {
        return imageService.convertToBlob(shirtImage);
    }
}
