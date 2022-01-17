package com.example.hearthstonechallenger.repository.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.example.hearthstonechallenger.enums.NaturesEnum;
import com.example.hearthstonechallenger.enums.VocationsEnum;

public final class ObjectSpecification {

	public static <T> Specification<T> dynamicSearch(SearchCriteria searchCriteria) {
		return dynamicSearch(searchCriteria.getId(), searchCriteria.getName(), searchCriteria.getNature(),
				searchCriteria.getVocation());
	}

	public static <T> Specification<T> dynamicSearch(Optional<Long> id, Optional<String> name, Optional<String> nature,
			Optional<String> vocation) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(id.isPresent())
				predicates.add(builder.equal(root.get("id"), id.get()));
			
			if(name.isPresent())
				predicates.add(builder.like(root.get("name"), "%"+name.get()+"%"));
			
			if(vocation.isPresent())
				predicates.add(builder.equal(root.get("idClass"), VocationsEnum.getByName(vocation.get())));
			
			if(nature.isPresent())
				predicates.add(builder.equal(root.get("idType"), NaturesEnum.getByName(nature.get())));
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
