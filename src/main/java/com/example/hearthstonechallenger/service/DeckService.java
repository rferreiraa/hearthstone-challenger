package com.example.hearthstonechallenger.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hearthstonechallenger.controller.dto.DeckDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.entity.Deck;
import com.example.hearthstonechallenger.enums.VocationsEnum;
import com.example.hearthstonechallenger.repository.DeckRepository;

@Service
public class DeckService {

	@Autowired
	private DeckRepository deckRepository;

	public Deck saveDeck(DeckDTO dto) throws BusinessException {
		try {
			Deck deck = new Deck();
			BeanUtils.copyProperties(dto, deck);

			deck.setIdClass(VocationsEnum.getByName(dto.getVocation()));

			return deckRepository.save(deck);

		} catch (Exception e) {
			throw new BusinessException("Error in deck save.");
		}

	}

}
