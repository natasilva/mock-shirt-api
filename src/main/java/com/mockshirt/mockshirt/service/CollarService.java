package com.mockshirt.mockshirt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Collar;
import com.mockshirt.mockshirt.repository.CollarRepository;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("CollarService")
public class CollarService implements IService<Collar> {
    private CollarRepository repository;

    public CollarService(CollarRepository collarRepository) {
        this.repository = collarRepository;
    }

    @Override
    public List<Collar> list() {
        List<Collar> result = repository.findAll();
        return result;
    }
}
