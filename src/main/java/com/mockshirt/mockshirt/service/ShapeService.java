package com.mockshirt.mockshirt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Shape;
import com.mockshirt.mockshirt.repository.ShapeRepository;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("ShapeService")
public class ShapeService implements IService<Shape> {
    private ShapeRepository repository;

    public ShapeService(ShapeRepository shapeRepository) {
        this.repository = shapeRepository;
    }

    @Override
    public List<Shape> list() {
        List<Shape> result = repository.findAll();
        return result;
    }
}
