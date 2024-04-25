package com.mockshirt.mockshirt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shape")
public class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeCollar;
    private String typeSleeve;
    private boolean sleeveLogo;
    private String color;
    private String frontUrl;
    private String backUrl;

    public Shape() {
    }
}
