package com.example.book_systems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_systems.entity.Location;

@Repository
public interface LocationDao extends JpaRepository<Location, String>{

}
