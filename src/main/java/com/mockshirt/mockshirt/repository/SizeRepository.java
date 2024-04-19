package com.mockshirt.mockshirt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockshirt.mockshirt.entity.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

}