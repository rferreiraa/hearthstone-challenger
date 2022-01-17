package com.example.hearthstonechallenger.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "decks", schema = "hearthstone")
public class Deck {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(
	  name = "deck_cards", schema = "hearthstone",
	  joinColumns = @JoinColumn(name = "id_deck"), 
	  inverseJoinColumns = @JoinColumn(name = "id_card"))
	private List<Card> cards;

	@Column(name = "id_class")
	private Integer idClass;

}
