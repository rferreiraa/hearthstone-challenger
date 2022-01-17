package com.example.hearthstonechallenger.controller.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.enums.NaturesEnum;
import com.example.hearthstonechallenger.enums.VocationsEnum;
import com.example.hearthstonechallenger.utils.NotNullIfIsCreature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNullIfIsCreature(
	    fieldName = "nature",
	    fieldValue = "CREATURE",
	    dependFieldName = "attack")
@NotNullIfIsCreature(
	    fieldName = "nature",
	    fieldValue = "CREATURE",
	    dependFieldName = "defense")
public class CardDTO {
	
	@Positive(message = "Must be greater than 0")
	private Long id;

	@NotNull
	@Max(value = 13, message = "Must be less than or equal to {value}")
	@Positive(message = "Must be greater than 0")
	private Integer coust;
	
	@NotNull
	@Size(min = 1, max = 100, message = "Must be between 1 and 100 characters")
	private String name;
	
	@NotNull
	@Size(min = 1, max = 100, message = "Must be between 1 and 100 characters")
	private String description;
	
	@Max(value = 10, message = "Must be less than or equal to {value}")
	@Positive(message = "Must be greater than 0")
	private Integer attack;
	
	@Max(value = 10, message = "Must be less than or equal to {value}")
	@Positive(message = "Must be greater than 0")
	private Integer defense;
	
	@NotNull
	@Pattern(regexp = "MAGE|PALADIN|HUNTER|DRUID|ANY", message = "Vocation must match to 'MAGE|PALADIN|HUNTER|DRUID|ANY'")
	private String vocation;
	
	@NotNull
	@Pattern(regexp = "MAGIC|CREATURE", message = "Nature must match to 'MAGIC|CREATURE'")
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
