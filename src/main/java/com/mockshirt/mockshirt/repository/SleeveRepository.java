package com.mockshirt.mockshirt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockshirt.mockshirt.entity.Sleeve;

@Repository
public interface SleeveRepository extends JpaRepository<Sleeve, Long> {

}