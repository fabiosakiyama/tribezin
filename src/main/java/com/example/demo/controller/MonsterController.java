package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Monster;
import com.example.demo.domain.enums.ElementType;
import com.example.demo.domain.enums.MonsterType;
import com.example.demo.repository.MonsterRepository;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/monsters")
@AllArgsConstructor
public class MonsterController {

	private MonsterRepository repository;

	private ModelMapper mapper;

	@ApiOperation(value = "List monsters given the filters",
			  notes = "The query matcher is 'contains' ")
	@GetMapping
	public ResponseEntity<Page<Monster>> list(@Valid Optional<MonsterFilter> filter, @ApiIgnore Pageable pageable) {
		if (filter.isPresent()) {
			ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
			Monster model = mapper.map(filter.get(), Monster.class);
		    Example<Monster> example = Example.of(model, caseInsensitiveExampleMatcher);
			return ResponseEntity.ok(repository.findAll(example, pageable));
		}
		return ResponseEntity.ok(repository.findAll(pageable));
	}
	
	@ApiOperation(value = "List all monsters types")
	@GetMapping("/types")
	public ResponseEntity<List<MonsterType>> listTypes() {
		return ResponseEntity.ok(repository.findDistinctType());
	}
	
	@ApiOperation(value = "List all monsters element types")
	@GetMapping("/element-types")
	public ResponseEntity<List<ElementType>> listelementTypes() {
		return ResponseEntity.ok(repository.findDistinctElementType());
	}
}
