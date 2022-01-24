package com.velpe.jwtAuth.auth.dao;

import com.velpe.jwtAuth.auth.domain.AuthStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthStorage, Long> {

    boolean existsByRefreshToken(String refreshToken);

}
