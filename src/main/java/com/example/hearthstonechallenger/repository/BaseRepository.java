package com.example.hearthstonechallenger.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;

public interface BaseRepository<T> extends JpaSpecificationExecutor<T> {
	
	public List<T> findAll(@Nullable Specification<T> spec);
}
