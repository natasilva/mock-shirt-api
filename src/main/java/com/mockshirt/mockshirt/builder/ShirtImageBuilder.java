package com.mockshirt.mockshirt.builder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.mockshirt.mockshirt.builder.interfaces.IShirtImageBuilder;
import com.mockshirt.mockshirt.service.interfaces.IImageService;

import java.sql.Blob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShirtImageBuilder implements IShirtImageBuilder {
    @Autowired
    private IImageService imageService;

    private BufferedImage logo;
    private BufferedImage shape;
    private BufferedImage shirtImage;

    public ShirtImageBuilder() {
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
        int x = 105;
        int y = 70;

        BufferedImage logo = imageService.scaleImage(this.logo, 141, 100);
        shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());

        Graphics2D g2d = shirtImage.createGraphics();
        imageService.drawBaseImage(g2d, shape);
        drawLogoWithPosition(logo, x, y, false, 0);

        try {
            imageService.saveImage(this.shirtImage, "back.png");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return this;
    }

    public ShirtImageBuilder withFrontLogo() {
        int x = 340;
        int y = 140;

        BufferedImage logo = imageService.scaleImage(this.logo, 90, 90);
        shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());

        Graphics2D g2d = shirtImage.createGraphics();
        imageService.drawBaseImage(g2d, shape);
        drawLogoWithPosition(logo, x, y, false, 0);

        return this;
    }

    public ShirtImageBuilder withRightSleeveLogo(boolean execute) {
        if (!execute) {
            return this;
        }

        int x = 485;
        int y = 170;

        BufferedImage logo = imageService.scaleImage(this.logo, 70, 70);
        drawLogoWithPosition(logo, x, y, true, 330);

        try {
            imageService.saveImage(this.shirtImage, "front.png");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return this;
    }

    public ShirtImageBuilder withLeftSleeveLogo(boolean execute) {
        if (!execute) {
            return this;
        }

        int x = 35;
        int y = 170;

        BufferedImage logo = imageService.scaleImage(this.logo, 70, 70);
        drawLogoWithPosition(logo, x, y, true, 30);

        return this;
    }

    private void drawLogoWithPosition(BufferedImage logo, int x, int y, boolean rotate, int ang) {
        Graphics2D g2d = shirtImage.createGraphics();
        imageService.drawBaseImage(g2d, shirtImage);

        if (rotate) {
            imageService.rotateAndDrawLogo(g2d, logo, x, y, ang);
        } else {
            imageService.drawLogo(g2d, logo, x, y);
        }

        g2d.dispose();
    }

    public Blob getBlob() {
        return imageService.convertToBlob(shirtImage);
    }
}
