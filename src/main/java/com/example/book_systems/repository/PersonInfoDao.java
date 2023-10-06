package com.example.book_systems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_systems.entity.PersonInfo;

public interface PersonInfoDao extends JpaRepository<PersonInfo, String> {

}
