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
    private boolean hasLongSleeve;
    private boolean hasRoundCollar;
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

    public ShirtImageBuilder hasLongSleeve(boolean hasLongSleeve) {
        this.hasLongSleeve = hasLongSleeve;
        return this;
    }

    public ShirtImageBuilder hasRoundCollar(boolean hasRoundCollar) {
        this.hasRoundCollar = hasRoundCollar;
        return this;
    }

    public ShirtImageBuilder withBackLogo() {
        int x = 135;
        int y = 70;

        BufferedImage logo = imageService.scaleImage(this.logo, 141, 100);
        shirtImage = imageService.createBlankImage(shape.getWidth(), shape.getHeight());

        Graphics2D g2d = shirtImage.createGraphics();
        imageService.drawBaseImage(g2d, shape);
        drawLogoWithPosition(logo, x, y, false, 0);

        return this;
    }

    public ShirtImageBuilder withFrontLogo() {
        int x = this.hasLongSleeve ? (this.hasRoundCollar ? 230 : 235) : (this.hasRoundCollar ? 210 : 230);
        int y = 100;

        BufferedImage logo = imageService.scaleImage(this.logo, 70, 70);
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

        int x = this.hasLongSleeve ? 310 : (this.hasRoundCollar ? 290 : 310);
        int y = this.hasLongSleeve ? 135 : 135;

        BufferedImage logo = imageService.scaleImage(this.logo, 55, 55);
        drawLogoWithPosition(logo, x, y, true, 340);

        return this;
    }

    public ShirtImageBuilder withLeftSleeveLogo(boolean execute) {
        if (!execute) {
            return this;
        }

        int x = this.hasLongSleeve ? 35 : 30;
        int y = this.hasLongSleeve ? 135 : 135;

        BufferedImage logo = imageService.scaleImage(this.logo, 55, 55);
        drawLogoWithPosition(logo, x, y, true, 20);

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
