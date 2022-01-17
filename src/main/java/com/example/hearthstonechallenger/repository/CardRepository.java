package com.example.hearthstonechallenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.hearthstonechallenger.entity.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Long>, BaseRepository<Card> {

}
