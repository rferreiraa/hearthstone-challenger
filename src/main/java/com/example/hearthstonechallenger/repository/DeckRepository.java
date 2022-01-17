package com.example.hearthstonechallenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hearthstonechallenger.entity.Deck;

@Repository
public interface DeckRepository extends CrudRepository<Deck, Long>,  BaseRepository<Deck> {

}
