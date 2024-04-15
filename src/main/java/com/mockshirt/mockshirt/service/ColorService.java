package com.mockshirt.mockshirt.service;

import java.util.Arrays;
import java.util.List;

// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Color;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("ColorService")
public class ColorService implements IService<Color> {
    // TODO: Ajustar Serviço
    // private MaterialRepository repository;

    // public MaterialService(@Qualifier("MaterialRepository") IService
    // materialRepository) {
    // this.repository = materialRepository;
    // }

    @Override
    public List<Color> list() {
        // List<Material> result = repository.findAll();
        // return result;
        return Arrays.asList();
    }

}
