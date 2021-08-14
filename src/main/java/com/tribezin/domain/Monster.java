package com.tribezin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tribezin.domain.enums.ElementType;
import com.tribezin.domain.enums.MonsterType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Monster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JsonManagedReference
	private Location location;
	
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private MonsterType type;
	
	@NotNull
	@Column
	private String imagePath;
	
	@NotNull
	@Column
	@Enumerated(EnumType.STRING)
	private ElementType elementType;
	
}
