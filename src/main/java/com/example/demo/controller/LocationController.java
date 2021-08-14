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

import com.example.demo.domain.Location;
import com.example.demo.repository.LocationRepository;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {

	private LocationRepository repository;

	private ModelMapper mapper;

	@ApiOperation(value = "List locations given the filters",
			  notes = "The query matcher is 'contains' ")
	@GetMapping
	public ResponseEntity<Page<Location>> list(@Valid Optional<LocationFilter> filter, @ApiIgnore Pageable pageable) {
		if (filter.isPresent()) {
			ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
			Location model = mapper.map(filter.get(), Location.class);
		    Example<Location> example = Example.of(model, caseInsensitiveExampleMatcher);
			return ResponseEntity.ok(repository.findAll(example, pageable));
		}
		return ResponseEntity.ok(repository.findAll(pageable));
	}
	
}
