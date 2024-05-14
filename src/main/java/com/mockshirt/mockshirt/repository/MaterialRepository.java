package com.mockshirt.mockshirt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mockshirt.mockshirt.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByKey(String key);
}
