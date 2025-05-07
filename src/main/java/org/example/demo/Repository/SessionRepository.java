package org.example.demo.Repository;

import org.example.demo.Entity.SessionEntity;
import org.example.demo.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

@Repository
public class SessionRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public SessionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UUID save(Long loginID) {


        SessionEntity sessionEntity = SessionEntity.builder()
                .userEntity(UserEntity.builder().id(loginID).build())
                .expiresAt(ZonedDateTime.now().plusHours(1))
                .build();

        getCurrentSession().persist(sessionEntity);

        return sessionEntity.getId();
    }

    public ZonedDateTime getEndTime(UUID id) {
        SessionEntity sessionEntity = getCurrentSession().createQuery("from SessionEntity s where s.id = :id",SessionEntity.class)
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
        if(sessionEntity == null) {
            return ZonedDateTime.now().minusDays(1);
        }
        return sessionEntity.getExpiresAt();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
