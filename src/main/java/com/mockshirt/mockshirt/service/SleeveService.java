package com.mockshirt.mockshirt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Sleeve;
import com.mockshirt.mockshirt.repository.SleeveRepository;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("SleeveService")
public class SleeveService implements IService<Sleeve> {
    private SleeveRepository repository;

    public SleeveService(SleeveRepository sleeveRepository) {
        this.repository = sleeveRepository;
    }

    @Override
    public List<Sleeve> list() {
        List<Sleeve> result = repository.findAll();
        return result;
    }

}
