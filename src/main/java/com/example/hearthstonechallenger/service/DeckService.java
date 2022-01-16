package com.example.hearthstonechallenger.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.example.hearthstonechallenger.controller.dto.DeckDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.entity.Deck;
import com.example.hearthstonechallenger.enums.VocationsEnum;
import com.example.hearthstonechallenger.repository.DeckRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeckService {

	@Autowired
	private DeckRepository deckRepository;

	public Deck saveDeck(DeckDTO dto) throws BusinessException {
		try {
			Deck deck = new Deck();
			BeanUtils.copyProperties(dto, deck);

			deck.setIdClass(VocationsEnum.getByName(dto.getVocation()));
			copyCards(dto, deck);

			return deckRepository.save(deck);

		} catch (JpaObjectRetrievalFailureException e) {
			throw new EntityNotFoundException("Card not found.");
		} catch (Exception e) {
			log.error("Error in deck save.", e);
			throw new BusinessException("Error in deck save.");
		}

	}

	private void copyCards(DeckDTO dto, Deck deck) {
		Set<Card> cards = new HashSet<Card>();

		dto.getCards().forEach(item -> {
			Card card = new Card();

			BeanUtils.copyProperties(item, card);

			cards.add(card);
		});
		deck.setCards(cards);
	}

}
