package com.example.hearthstonechallenger.controller.dto;

import java.util.List;

import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.entity.Deck;
import com.example.hearthstonechallenger.enums.NaturesEnum;

import lombok.Data;

@Data
public class DeckDTO {

	private Long id;
	private String name;
	private List<Card> cards;
	private String vocation;

	public DeckDTO(Deck deck) {
	        this.id = deck.getId();
	        this.name = deck.getName();
	        this.cards = deck.getCards();
	        this.vocation = NaturesEnum.getById(deck.getIdClass());
	    }
}
