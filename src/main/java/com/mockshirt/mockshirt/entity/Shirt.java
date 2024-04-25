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

    public Shirt(Blob back, Blob front, float value) {
        this.back = back;
        this.front = front;
        this.value = value;
    }
}
