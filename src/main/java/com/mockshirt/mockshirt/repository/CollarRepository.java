package com.mockshirt.mockshirt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mockshirt.mockshirt.entity.Collar;

@Repository
public interface CollarRepository extends JpaRepository<Collar, Long> {
    Collar findByAcronym(String acronym);
}
