package com.example.hearthstonechallenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.hearthstonechallenger.controller.dto.CardDTO;
import com.example.hearthstonechallenger.controller.dto.DeckDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.controller.exception.GenericException;
import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.entity.Deck;
import com.example.hearthstonechallenger.enums.VocationsEnum;
import com.example.hearthstonechallenger.repository.CardRepository;
import com.example.hearthstonechallenger.repository.DeckRepository;
import com.example.hearthstonechallenger.repository.criteria.ObjectSpecification;
import com.example.hearthstonechallenger.repository.criteria.SearchCriteria;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeckService {

	@Autowired
	private DeckRepository deckRepository;

	@Autowired
	private CardRepository cardRepository;

	public Deck saveDeck(DeckDTO dto) throws Exception {
		try {
			Deck deck = new Deck();
			BeanUtils.copyProperties(dto, deck);

			deck.setIdClass(VocationsEnum.getByName(dto.getVocation()));
			copyCards(dto, deck);

			return deckRepository.save(deck);

		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			log.error("Error in deck save.", e);
			throw new GenericException("Error in deck save.");
		}
	}
	
	public List<DeckDTO> retrieveDecks(SearchCriteria searchCriteria) {
		Specification<Deck> cardSpecifications = ObjectSpecification.dynamicSearch(searchCriteria);
		List<DeckDTO> deckDto = new ArrayList<>();
		List<Deck> decks = deckRepository.findAll(cardSpecifications);
		
		decks.stream().forEach(deck -> {
			DeckDTO dto = new DeckDTO();
			List<CardDTO> cardList = dto.getCards();
			
			BeanUtils.copyProperties(deck, dto);
			
			for (Card cards : deck.getCards()) {
				CardDTO cardDto = new CardDTO();
				
				BeanUtils.copyProperties(cards, cardDto);

				cardList.add(cardDto);
			}
			
			dto.setCards(cardList);
			dto.setVocation(VocationsEnum.getById(deck.getIdClass()));
			
			deckDto.add(dto);
		});
		
		return deckDto;
	}
	
	private void copyCards(DeckDTO dto, Deck deck) throws BusinessException {
		List<Card> cards = new ArrayList<>();
		List<CardDTO> dtoCards = dto.getCards();

		for (CardDTO cardDTO : dtoCards) {
			Card card = new Card();

			validateCards(dto, cardDTO);

			BeanUtils.copyProperties(cardDTO, card);

			cards.add(card);
		}

		deck.setCards(cards);
	}

	private void validateCards(DeckDTO dto, CardDTO item) throws BusinessException {
		Optional<Card> card = cardRepository.findById(item.getId());

		List<CardDTO> sameCard = dto.getCards().stream().filter(c -> c.getId() == item.getId())
				.collect(Collectors.toList());

		if (ObjectUtils.isEmpty(card))
			throw new BusinessException("Card with id '" + item.getId() + "' not found.");

		String cardVocation = VocationsEnum.getById(card.get().getIdClass());
		String cardName = card.get().getName();

		if (!cardVocation.equals("ANY") && !cardVocation.equals(dto.getVocation()))
			throw new BusinessException("Your deck contains cards that are not compatible with your class.");

		if (sameCard.size() > 2)
			throw new BusinessException(
					"You cannot add more than two of the same cards to the same deck. Please remove a card with the name "
							+ cardName);
	}
	
	
}
