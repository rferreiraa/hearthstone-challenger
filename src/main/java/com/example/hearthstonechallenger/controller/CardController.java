package com.example.hearthstonechallenger.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hearthstonechallenger.controller.dto.CardDTO;
import com.example.hearthstonechallenger.controller.exception.BusinessException;
import com.example.hearthstonechallenger.controller.exception.ExceptionsResolver;
import com.example.hearthstonechallenger.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController extends ExceptionsResolver {
	
	@Autowired
	private CardService cardService;

	@PostMapping
	public @ResponseBody ResponseEntity<String> saveCard(@Valid @RequestBody CardDTO dto) throws BusinessException{
		cardService.saveCard(dto);
		return ResponseEntity.status(HttpStatus.OK).body("Card saved successfully");
	}
}
