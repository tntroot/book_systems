package com.example.book_systems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.book_systems.entity.User;
import com.example.book_systems.entity.UserShow;

public interface UserDao extends JpaRepository<User, String> {
	
	@Query(value = "select account, user_name, email, born, redate, locked_status"
			+ " from user where email=:input_email",nativeQuery = true)
	public List<UserShow> findByEmail(@Param("input_email") String email);

//	@Modifying
//	@Transactional
//	@Query(value = "")
//	public int insertUser();
}
