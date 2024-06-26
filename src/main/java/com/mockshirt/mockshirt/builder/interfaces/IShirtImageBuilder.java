package com.mockshirt.mockshirt.builder.interfaces;

import java.awt.image.BufferedImage;
import java.sql.Blob;

import com.mockshirt.mockshirt.builder.ShirtImageBuilder;

public interface IShirtImageBuilder {

    ShirtImageBuilder setLogo(BufferedImage logo);

    ShirtImageBuilder setShape(BufferedImage shape);

    ShirtImageBuilder withBackLogo();

    ShirtImageBuilder withFrontLogo();

    ShirtImageBuilder withRightSleeveLogo(boolean execute);

    ShirtImageBuilder withLeftSleeveLogo(boolean execute);

    Blob getBlob();
}
