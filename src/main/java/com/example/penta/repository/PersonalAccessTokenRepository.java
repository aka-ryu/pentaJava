package com.example.penta.repository;

import com.example.penta.entity.PersonalAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonalAccessTokenRepository extends JpaRepository<PersonalAccessToken, Long> {
    Optional<PersonalAccessToken> findByToken(String token);




    Optional<PersonalAccessToken> findByTokenableId(Long tokenable_id);
}
