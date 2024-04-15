package com.mockshirt.mockshirt.service;

import java.util.Arrays;
import java.util.List;

// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Sleeve;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("SleeveService")
public class SleeveService implements IService<Sleeve> {
    // TODO: Ajustar Serviço
    // private SleeveRepository repository;

    // public SleeveService(@Qualifier("SleeveRepository") IService
    // sleeveRepository) {
    // this.repository = sleeveRepository;
    // }

    @Override
    public List<Sleeve> list() {
        return Arrays.asList();

        // List<Material> result = repository.findAll();
        // return result;
    }

}
