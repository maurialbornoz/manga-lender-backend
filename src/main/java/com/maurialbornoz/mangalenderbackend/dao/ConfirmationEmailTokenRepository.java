package com.maurialbornoz.mangalenderbackend.dao;

import com.maurialbornoz.mangalenderbackend.entity.ConfirmationEmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationEmailTokenRepository extends JpaRepository<ConfirmationEmailToken, Long> {

    Optional<ConfirmationEmailToken> findByToken(String token);
}
