package com.example.hearthstonechallenger.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.hearthstonechallenger.enums.Natures;
import com.example.hearthstonechallenger.enums.Vocations;

import lombok.Data;
import lombok.NoArgsConstructor;

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
	
	private Vocations vocation;
	
	private Natures nature;

}
