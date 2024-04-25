package com.mockshirt.mockshirt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockshirt.mockshirt.entity.Shape;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {
    List<Shape> findByColor(String color);
}