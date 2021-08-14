package com.example.demo.controller;

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

import com.example.demo.domain.Material;
import com.example.demo.repository.MaterialRepository;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class MaterialController {

	private MaterialRepository repository;

	private ModelMapper mapper;

	@ApiOperation(value = "List materials given the filters",
			  notes = "The query matcher is 'contains' ")
	@GetMapping
	public ResponseEntity<Page<Material>> list(@Valid Optional<MaterialFilter> filter, @ApiIgnore Pageable pageable) {
		if (filter.isPresent()) {
			ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
			Material model = mapper.map(filter.get(), Material.class);
		    Example<Material> example = Example.of(model, caseInsensitiveExampleMatcher);
			return ResponseEntity.ok(repository.findAll(example, pageable));
		}
		return ResponseEntity.ok(repository.findAll(pageable));
	}
	
}
