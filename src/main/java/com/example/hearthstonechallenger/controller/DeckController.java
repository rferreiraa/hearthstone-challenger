package com.example.hearthstonechallenger.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hearthstonechallenger.controller.dto.DeckDTO;
import com.example.hearthstonechallenger.controller.exception.ExceptionsResolver;
import com.example.hearthstonechallenger.entity.Deck;
import com.example.hearthstonechallenger.repository.DeckRepository;
import com.example.hearthstonechallenger.repository.criteria.SearchCriteria;
import com.example.hearthstonechallenger.service.DeckService;

@RestController
@RequestMapping("/deck")
public class DeckController extends ExceptionsResolver {

	@Autowired
	private DeckService deckService;

	@Autowired
	private DeckRepository deckRepository;

	@PostMapping
	public @ResponseBody ResponseEntity<String> saveDeck(@Validated @RequestBody DeckDTO dto) throws Exception {
		deckService.saveDeck(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Deck saved successfully");
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable(value = "id") long id) {
		Optional<Deck> deck = deckRepository.findById(id);
		if (deck.isPresent()) {
			deckRepository.delete(deck.get());
			return ResponseEntity.status(HttpStatus.OK).body("Deck deleted sucessfully");
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deck not found");
	}
	
	@GetMapping
	public ResponseEntity<List<DeckDTO>> get(@Valid @RequestParam(required = false) Optional<Long> id,
			@RequestParam(required = false) Optional<String> name,
			@RequestParam(required = false) Optional<String> vocation) {

		SearchCriteria search = SearchCriteria.builder()
				.id(id)
				.name(name)
				.vocation(vocation)
				.build();

		List<DeckDTO> decks = deckService.retrieveDecks(search);
		return new ResponseEntity<>(decks, HttpStatus.OK);
	}

}
