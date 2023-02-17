package com.maurialbornoz.mangalenderbackend.service;

import com.maurialbornoz.mangalenderbackend.dao.ConfirmationEmailTokenRepository;
import com.maurialbornoz.mangalenderbackend.entity.ConfirmationEmailToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationEmailTokenService {
    private final ConfirmationEmailTokenRepository confirmationEmailTokenRepository;

    public void saveConfirmationToken (ConfirmationEmailToken token){
        confirmationEmailTokenRepository.save(token);
    }
    public Optional<ConfirmationEmailToken> getToken(String token){
        return confirmationEmailTokenRepository.findByToken(token);
    }
}
