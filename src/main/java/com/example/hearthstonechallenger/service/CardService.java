package com.example.hearthstonechallenger.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.hearthstonechallenger.controller.dto.CardDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.enums.NaturesEnum;
import com.example.hearthstonechallenger.enums.VocationsEnum;
import com.example.hearthstonechallenger.repository.CardRepository;
import com.example.hearthstonechallenger.repository.criteria.ObjectSpecification;
import com.example.hearthstonechallenger.repository.criteria.SearchCriteria;

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

	public List<CardDTO> retrieveCard(SearchCriteria searchCriteria) {
		Specification<Card> cardSpecifications = ObjectSpecification.dynamicSearch(searchCriteria);
		List<CardDTO> cardsDto = new ArrayList<>();
		List<Card> cards = cardRepository.findAll(cardSpecifications);
		
		cards.stream().forEach(card -> {
			CardDTO dto = new CardDTO();
			BeanUtils.copyProperties(card, dto);
			
			dto.setVocation(VocationsEnum.getById(card.getIdClass()));
			dto.setNature(NaturesEnum.getById(card.getIdType()));
			
			cardsDto.add(dto);
		});
		
		return cardsDto;
	}
}
