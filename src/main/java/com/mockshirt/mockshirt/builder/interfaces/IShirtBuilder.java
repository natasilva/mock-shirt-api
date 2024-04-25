package com.mockshirt.mockshirt.builder.interfaces;

import java.sql.Blob;

import com.mockshirt.mockshirt.builder.ShirtBuilder;
import com.mockshirt.mockshirt.entity.Shirt;

public interface IShirtBuilder {
    ShirtBuilder builder();

    Shirt build();

    ShirtBuilder setLogo(byte[] logo);

    ShirtBuilder setBackUrl(String backUrl);

    ShirtBuilder setFrontUrl(String frontUrl);

    ShirtBuilder setSleeveLogo(boolean sleeveLogo);

    Blob getBackImage();

    Blob getFrontImage();
}
