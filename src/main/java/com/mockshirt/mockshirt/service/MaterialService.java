package com.mockshirt.mockshirt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Material;
import com.mockshirt.mockshirt.repository.MaterialRepository;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("MaterialService")
public class MaterialService implements IService<Material> {
    private MaterialRepository repository;

    public MaterialService(MaterialRepository materialRepository) {
        this.repository = materialRepository;
    }

    @Override
    public List<Material> list() {
        List<Material> result = repository.findAll();
        return result;

    }
}
