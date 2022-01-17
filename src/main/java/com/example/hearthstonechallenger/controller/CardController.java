package com.example.hearthstonechallenger.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hearthstonechallenger.controller.dto.CardDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.controller.exception.ExceptionsResolver;
import com.example.hearthstonechallenger.entity.Card;
import com.example.hearthstonechallenger.repository.CardRepository;
import com.example.hearthstonechallenger.repository.criteria.SearchCriteria;
import com.example.hearthstonechallenger.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController extends ExceptionsResolver {

	@Autowired
	private CardService cardService;

	@Autowired
	private CardRepository cardRepository;

	@PostMapping
	public @ResponseBody ResponseEntity<String> save(@Valid @RequestBody CardDTO dto) throws BusinessException {
		cardService.saveCard(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Card saved successfully");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		Optional<Card> card = cardRepository.findById(id);
		if (card.isPresent()) {
			cardRepository.delete(card.get());
			return ResponseEntity.status(HttpStatus.OK).body("Card deleted sucessfully");
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found");
	}

	@GetMapping
	public ResponseEntity<List<CardDTO>> get(@Valid @RequestParam(required = false) Optional<Long> id,
			@RequestParam(required = false) Optional<String> name,
			@RequestParam(required = false) Optional<String> vocation,
			@RequestParam(required = false) Optional<String> nature) {

		SearchCriteria search = SearchCriteria.builder()
				.id(id)
				.name(name)
				.vocation(vocation)
				.nature(nature)
				.build();

		List<CardDTO> cards = cardService.retrieveCard(search);
		return new ResponseEntity<>(cards, HttpStatus.OK);
	}
}
