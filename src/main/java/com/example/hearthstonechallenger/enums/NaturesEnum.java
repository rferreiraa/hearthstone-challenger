package com.example.hearthstonechallenger.enums;

import lombok.Getter;

@Getter
public enum NaturesEnum {

	MAGIC(1, "MAGIC"),
	CREATURE(2, "CREATURE");
	
	private Integer id;
	private String name;


	NaturesEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	};

	public static String getById(Integer id) {
		for (NaturesEnum n : NaturesEnum.values()) {
			if(n.id == id)
				return n.name();
		}
		return null;
	}
	
}
