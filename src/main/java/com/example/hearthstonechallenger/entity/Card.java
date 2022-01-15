package com.example.hearthstonechallenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Card {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "coust")
	private Integer coust;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "attack")
	private Integer attack;
	
	@Column(name = "defense")
	private Integer defense;
	
	@Column(name = "id_class")
	private Integer idClass;
	
	@Column(name = "id_type")
	private Integer idType;

}
