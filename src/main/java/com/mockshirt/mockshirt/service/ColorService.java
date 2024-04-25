package com.mockshirt.mockshirt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Color;
import com.mockshirt.mockshirt.repository.ColorRepository;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("ColorService")
public class ColorService implements IService<Color> {
    private ColorRepository repository;

    public ColorService(ColorRepository colorRepository) {
        this.repository = colorRepository;
    }

    @Override
    public List<Color> list() {
        List<Color> result = repository.findAll();
        return result;
    }
}
