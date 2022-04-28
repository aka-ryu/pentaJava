package com.example.penta.service.etc;

import com.example.penta.entity.PersonalAccessToken;
import com.example.penta.repository.PersonalAccessTokenRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class UpdateJWTService {

    @Autowired
    private PersonalAccessTokenRepository personalAccessTokenRepository;

    // PersonalAccessToken update
    public void updatePersonalAccessToken(PersonalAccessToken personalAccessToken) {
        personalAccessTokenRepository.save(PersonalAccessToken.lastUsedAt(personalAccessToken));
    }
}
