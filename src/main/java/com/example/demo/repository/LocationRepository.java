package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
