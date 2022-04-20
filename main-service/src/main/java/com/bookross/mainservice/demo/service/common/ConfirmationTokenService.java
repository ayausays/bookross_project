package com.bookross.mainservice.demo.service.common;

import com.bookross.mainservice.demo.entity.ConfirmationToken;
import com.bookross.mainservice.demo.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository tokenRepository;
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        tokenRepository.save(confirmationToken);
    }
    public Optional<ConfirmationToken> getToken(String token){
        return tokenRepository.findByToken(token);
    }
    public int setConfirmedAt(String token) {
        return tokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
