package com.token.auth.password.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.token.auth.password.model.PasswordResetToken;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

	@Query(value = "SELECT * FROM password_reset_token where token =:token limit 1", nativeQuery = true)
	public Optional<PasswordResetToken> findByToken(@Param("token") String token);

	@Query(value = "SELECT * FROM password_reset_token where  id =:userId limit 1", nativeQuery = true)
	public Optional<PasswordResetToken> findUserById(@Param("userId") Long id);

	@Modifying
	@Query(value = "DELETE FROM password_reset_token WHERE id =:userId", nativeQuery = true)
	public void deleteTokenByUserId(@Param("userId") Long id);
	
	@Modifying
	@Query(value = "UPDATE password_reset_token SET token=:userToken, expiration_time=:expirationTime WHERE id =:userId", nativeQuery = true)
	public void updateToken(@Param("userToken") String token, @Param("expirationTime") LocalDateTime expiration_time, @Param("userId") Long id);

}
