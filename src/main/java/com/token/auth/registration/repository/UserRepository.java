package com.token.auth.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.token.auth.registration.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM auth where email =:email limit 1", nativeQuery = true)
	public Optional<User> findByEmail(@Param("email") String email);

}
