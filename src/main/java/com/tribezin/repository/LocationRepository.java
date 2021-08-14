package com.tribezin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tribezin.domain.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
