package com.example.hearthstonechallenger.enums;

import lombok.Getter;

@Getter
public enum VocationsEnum {
	
	MAGE(1, "MAGE"),
	PALADIN(2, "PALADIN"),
	HUNTER(3, "HUNTER"),
	DRUID(4, "DRUID"),
	ANY(5, "ANY");

	private Integer id;
	private String name;


	VocationsEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	};

	public static String getById(Integer id) {
		for (VocationsEnum v : VocationsEnum.values()) {
			if(v.id == id)
				return v.name();
		}
		return null;
	}
	
	public static Integer getByName(String name) {
		for (VocationsEnum v : VocationsEnum.values()) {
			if(v.name.equals(name))
				return v.getId();
		}
		return null;
	}
}
