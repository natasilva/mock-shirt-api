package com.mockshirt.mockshirt.service;

import java.util.Arrays;
import java.util.List;

// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.entity.Size;
import com.mockshirt.mockshirt.service.interfaces.IService;

@Service("SizeService")
public class SizeService implements IService<Size> {
    // TODO: Ajustar Serviço
    // private MaterialRepository repository;

    // public MaterialService(@Qualifier("MaterialRepository") IService
    // materialRepository) {
    // this.repository = materialRepository;
    // }

    @Override
    public List<Size> list() {
        // List<Material> result = repository.findAll();
        // return result;
        return Arrays.asList();
    }

}
