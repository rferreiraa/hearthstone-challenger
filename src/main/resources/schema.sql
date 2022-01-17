--CREATE SCHEMA HEARTHSTONE
CREATE SCHEMA IF NOT EXISTS hearthstone; 

DROP TABLE IF EXISTS hearthstone.deck_cards;
DROP TABLE IF EXISTS hearthstone.decks;
DROP TABLE IF EXISTS hearthstone.cards;
DROP TABLE IF EXISTS hearthstone.card_types;
DROP TABLE IF EXISTS hearthstone.card_classes;

--CREATE TABLE CARD_TYPE
CREATE TABLE hearthstone.card_types (
	id BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY,
	`type` varchar(100) NOT NULL
);

--CREATE TABLE CARD_CLASS
CREATE TABLE hearthstone.card_classes (
	id BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY,
	`type` varchar(100) NOT NULL
);

--CREATE TABLE DECKS
CREATE TABLE hearthstone.decks (
	id BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY,
	name varchar(100) NOT NULL,
	id_class BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY(id_class) REFERENCES hearthstone.card_classes
);

    
--CREATE TABLE CARDS
CREATE TABLE hearthstone.cards (
	id BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY,
	coust INT NOT NULL,
	name varchar(100) NOT NULL,
	description varchar(100) NOT NULL,
	attack INT,
	defense INT,
	id_type BIGINT UNSIGNED NOT NULL,
	id_class BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY(id_class) REFERENCES hearthstone.card_classes,
	FOREIGN KEY(id_type) REFERENCES hearthstone.card_types
);

--CREATE TABLE CARD_TYPE
CREATE TABLE hearthstone.deck_cards (
	id BIGINT UNSIGNED auto_increment NOT NULL PRIMARY KEY,
	id_deck BIGINT UNSIGNED NOT NULL,
	id_card BIGINT UNSIGNED NOT NULL,
	FOREIGN KEY(id_deck) REFERENCES hearthstone.decks,
	FOREIGN KEY(id_card) REFERENCES hearthstone.cards
);
