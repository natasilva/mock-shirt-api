package com.mockshirt.mockshirt.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Size;
import com.mockshirt.mockshirt.repository.SizeRepository;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("SizeService")
public class SizeService implements IService<Size> {
    private SizeRepository repository;

    public SizeService(SizeRepository sizeRepository) {
        this.repository = sizeRepository;
    }

    @Override
    public List<Size> list() {
        List<Size> result = repository.findAll();
        return result;

    }
}
