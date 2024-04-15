package com.mockshirt.mockshirt.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Collar;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("CollarService")
public class CollarService implements IService<Collar> {
    // TODO: Ajustar Serviço
    // private CollarRepository repository;

    // public CollarService(@Qualifier("CollarRepository") IService
    // collarRepository) {
    // this.repository = collarRepository;
    // }

    @Override
    public List<Collar> list() {
        return Arrays.asList();
        // List<Collar> result = repository.findAll();
        // return result;
    }

}
