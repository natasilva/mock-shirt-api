package com.mockshirt.mockshirt.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Shape;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("ShapeService")
public class ShapeService implements IService<Shape> {
    // TODO: Ajustar Serviço

    @Override
    public List<Shape> list() {
        return Arrays.asList();
    }
}
