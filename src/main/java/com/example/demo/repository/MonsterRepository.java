package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.Monster;
import com.example.demo.domain.enums.ElementType;
import com.example.demo.domain.enums.MonsterType;

public interface MonsterRepository extends JpaRepository<Monster, Long>{

	@Query("Select distinct type from Monster")
	public List<MonsterType> findDistinctType();
	
	@Query("Select distinct elementType from Monster")
	public List<ElementType> findDistinctElementType();
}
