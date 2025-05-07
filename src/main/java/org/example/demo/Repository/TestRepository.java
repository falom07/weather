package org.example.demo.Repository;

import org.example.demo.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {

    private final SessionFactory sessionFactory;


    @Autowired
    public TestRepository(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public void save(UserEntity userEntity) {
        getCurrentSession().persist(userEntity);
    }

    public UserEntity findById(Long id) {
        return getCurrentSession().get(UserEntity.class, id);
    }

    public List<UserEntity> findAll() {
        return getCurrentSession()
                .createQuery("from UserEntity", UserEntity.class)
                .getResultList();
    }

    public void delete(UserEntity userEntity) {
        getCurrentSession().remove(userEntity);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}