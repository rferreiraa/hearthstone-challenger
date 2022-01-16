package com.example.hearthstonechallenger.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hearthstonechallenger.controller.dto.CardDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.enums.NaturesEnum;
import com.example.hearthstonechallenger.enums.VocationsEnum;
import com.example.hearthstonechallenger.repository.CardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CardService {

	@Autowired
	private CardRepository cardRepository;
	
	public Card saveCard(CardDTO dto) throws BusinessException {
		try {
			Card card = new Card();
			BeanUtils.copyProperties(dto, card);

			card.setIdClass(VocationsEnum.getByName(dto.getVocation()));
			card.setIdType(NaturesEnum.getByName(dto.getNature()));

			return cardRepository.save(card);

		} catch (Exception e) {
			log.error("Error in card save.", e);
			throw new BusinessException("Error in card save.");
		}
	}

}
