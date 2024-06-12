package com.mockshirt.mockshirt.entity;

import java.sql.Blob;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shirt {
    private Blob back;
    private Blob front;
    private float value;
    private Collar collar;
    private Sleeve sleeve;

    public Shirt(Blob back, Blob front, float value, Collar collar, Sleeve sleeve) {
        this.back = back;
        this.front = front;
        this.value = value;
        this.collar = collar;
        this.sleeve = sleeve;
    }
}
