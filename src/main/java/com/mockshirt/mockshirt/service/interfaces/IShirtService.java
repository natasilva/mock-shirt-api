package com.mockshirt.mockshirt.service.interfaces;

import java.util.List;

import com.mockshirt.mockshirt.entity.Shirt;

public interface IShirtService {
    List<Shirt> list(String color, byte[] logo);
}
