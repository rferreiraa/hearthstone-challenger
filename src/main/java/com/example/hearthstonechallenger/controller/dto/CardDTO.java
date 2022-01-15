package com.example.hearthstonechallenger.controller.dto;


import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.enums.NaturesEnum;
import com.example.hearthstonechallenger.enums.VocationsEnum;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDTO {

	private Integer coust;
	private String name;
	private String description;
	private Integer attack;
	private Integer defense;
	private String vocation;
	private String nature;
	
    public CardDTO(Card card) {
        this.coust = card.getCoust();
        this.name = card.getName();
        this.description = card.getDescription();
        this.attack = card.getAttack();
        this.defense = card.getDefense();
        this.vocation = NaturesEnum.getById(card.getIdClass());
        this.vocation = VocationsEnum.getById(card.getIdClass());
    }
}
