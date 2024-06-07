package com.mockshirt.mockshirt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockshirt.mockshirt.builder.interfaces.IShirtBuilder;
import com.mockshirt.mockshirt.entity.Shape;
import com.mockshirt.mockshirt.entity.Shirt;
import com.mockshirt.mockshirt.form.FormData;
import com.mockshirt.mockshirt.repository.ShapeRepository;
import com.mockshirt.mockshirt.service.interfaces.IShirtService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("ShirtService")
public class ShirtService implements IShirtService {
    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private IShirtBuilder shirtBuilder;

    @Override
    public List<Shirt> list(FormData formData) {
        try {
            List<Shape> shapes = shapeRepository.findByColor(formData.getColor());

            Iterator<Shape> shapeIterator = shapes.iterator();

            List<Shirt> shirts = new ArrayList<Shirt>();

            while (shapeIterator.hasNext()) {
                Shape shape = shapeIterator.next();
                Shirt shirt = shirtBuilder
                        .setLogo(formData.getFile())
                        .setSleeveLogo("true".equals(formData.getSleeveLogo()))
                        .setMaterial(formData.getMaterial())
                        .setSleeve(shape.getTypeSleeve())
                        .setCollar(shape.getTypeCollar())
                        .setLogoColorsQuantity(Integer.parseInt(formData.getLogoColorsQuantity()))
                        .setFrontUrl(shape.getFrontUrl())
                        .setBackUrl(shape.getBackUrl())
                        .build();

                shirts.add(shirt);
            }

            return shirts;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
