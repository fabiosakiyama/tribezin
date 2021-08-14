package com.tribezin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tribezin.domain.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{

}
