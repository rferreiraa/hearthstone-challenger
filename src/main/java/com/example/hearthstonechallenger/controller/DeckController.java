package com.example.hearthstonechallenger.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hearthstonechallenger.controller.dto.DeckDTO;
import com.example.hearthstonechallenger.controller.exception.ExceptionsResolver;


@RestController
@RequestMapping("/deck")
public class DeckController extends ExceptionsResolver{

	@PostMapping
	public @ResponseBody ResponseEntity<Object> saveCard(@Validated @RequestBody DeckDTO dto) {
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
}
