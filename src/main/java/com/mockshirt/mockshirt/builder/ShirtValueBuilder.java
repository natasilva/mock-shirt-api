package com.mockshirt.mockshirt.builder;

import org.springframework.stereotype.Component;

import com.mockshirt.mockshirt.builder.interfaces.IShirtValueBuilder;

@Component
public class ShirtValueBuilder implements IShirtValueBuilder {
    private float materialValue;
    private float sleeveValue;
    private boolean sleeveLogo;
    private float logoColorsQuantity;

    public ShirtValueBuilder setMaterialValue(float materialValue) {
        this.materialValue = materialValue;
        return this;
    }

    public ShirtValueBuilder setSleeveValue(float sleeveValue) {
        this.sleeveValue = sleeveValue;
        return this;
    }

    public ShirtValueBuilder hasSleeveLogo(boolean sleeveLogo) {
        this.sleeveLogo = sleeveLogo;
        return this;
    }

    public ShirtValueBuilder setLogoColorsQuantity(float logoColorsQuantity) {
        this.logoColorsQuantity = logoColorsQuantity;
        return this;
    }

    public float calculateValue() {
        float value = materialValue + sleeveValue;

        if (sleeveLogo) {
            value += 5;
        }

        if (logoColorsQuantity > 2) {
            value += (logoColorsQuantity - 2) * 5;
        }

        return value;
    }
}
