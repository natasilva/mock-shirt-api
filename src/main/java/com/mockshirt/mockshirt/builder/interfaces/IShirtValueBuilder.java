package com.mockshirt.mockshirt.builder.interfaces;

import com.mockshirt.mockshirt.builder.ShirtValueBuilder;

public interface IShirtValueBuilder {

    ShirtValueBuilder setMaterialValue(float materialValue);

    ShirtValueBuilder setSleeveValue(float sleeveValue);

    ShirtValueBuilder setSleeveLogo(boolean sleeveLogo);

    ShirtValueBuilder setLogoColorsQuantity(float logoColorsQuantity);

    float calculateValue();
}
