package com.mockshirt.mockshirt.builder.interfaces;

import java.awt.image.BufferedImage;
import java.sql.Blob;

import com.mockshirt.mockshirt.builder.ShirtImageBuilder;

public interface IShirtImageBuilder {

    ShirtImageBuilder setShape(BufferedImage shape);

    ShirtImageBuilder withBackLogo(BufferedImage backLogo);

    ShirtImageBuilder withFrontLogo(BufferedImage frontLogo);

    ShirtImageBuilder withRightSleeveLogo(BufferedImage rightSleeveLogo);

    ShirtImageBuilder withLeftSleeveLogo(BufferedImage leftSleeveLogo);

    Blob getBlob();
}
