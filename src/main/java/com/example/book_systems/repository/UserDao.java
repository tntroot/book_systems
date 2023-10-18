package com.example.book_systems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.book_systems.entity.User;

public interface UserDao extends JpaRepository<User, String> {

//	@Query(value = "select account, pwd, user_name, email, born, redate, locked_status"
//			+ " from user where email=:input_email", nativeQuery = true)
//	public List<User> findByEmail(@Param("input_email") String email);
	
	public Optional<User> findByEmail(String email);

//	@Modifying
//	@Transactional
//	@Query(value = "")
//	public int insertUser();
}
