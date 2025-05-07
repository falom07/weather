package org.example.demo.Service;

import org.example.demo.Repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public UUID saveSession(Long loginID) {
        return sessionRepository.save(loginID);

    }

    public boolean isSessionActive(String loginID) {
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime expiresAt = sessionRepository.getEndTime(UUID.fromString(loginID));
        return now.isBefore(expiresAt);
    }
}
