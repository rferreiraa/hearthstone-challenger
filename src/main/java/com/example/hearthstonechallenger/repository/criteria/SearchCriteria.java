package com.example.hearthstonechallenger.repository.criteria;

import java.util.Optional;

import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SearchCriteria {

	private Optional<Long> id;
	private Optional<String> name;
	
	@Pattern(regexp = "MAGE|PALADIN|HUNTER|DRUID|ANY", message = "Vocation must match to 'MAGE|PALADIN|HUNTER|DRUID|ANY'")
	private Optional<String> vocation;
	
	@Pattern(regexp = "MAGIC|CREATURE", message = "Nature must match to 'MAGIC|CREATURE'")
	private Optional<String> nature;
}
