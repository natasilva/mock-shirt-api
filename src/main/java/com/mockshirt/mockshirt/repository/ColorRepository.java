package com.mockshirt.mockshirt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockshirt.mockshirt.entity.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}
