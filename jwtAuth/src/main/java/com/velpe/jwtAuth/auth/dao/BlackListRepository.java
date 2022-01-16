package com.velpe.jwtAuth.auth.dao;

import com.velpe.jwtAuth.auth.domain.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    boolean existsByRefreshToken(String refreshToken);

}
