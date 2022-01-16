package com.example.hearthstonechallenger.controller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.hearthstonechallenger.entity.Card;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DeckDTO {

	@NotNull(message = "Name cannot be null")
	@Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
	private String name;
	
	@Size(max=30, message="Decks can only have 30 cards")
	private List<Card> cards;
	
	@Pattern(regexp = "MAGE|PALADIN|HUNTER|DRUID|ANY", message = "Must match to 'MAGE|PALADIN|HUNTER|DRUID|ANY'")
	private String vocation;

}
