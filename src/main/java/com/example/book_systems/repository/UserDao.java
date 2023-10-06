package com.example.book_systems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_systems.entity.User;

public interface UserDao extends JpaRepository<User, String> {

}
