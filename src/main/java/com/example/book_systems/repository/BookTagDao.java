package com.example.book_systems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_systems.entity.BookTag;

public interface BookTagDao extends JpaRepository<BookTag, Integer>{

	public List<BookTag> findAll();
	
	public boolean existsByTag(String tag);
	public List<BookTag> findByTag(String tag);
}
