package org.example.demo.Repository;

import org.example.demo.Entity.User;
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

    public void save(User user) {
        getCurrentSession().persist(user);
    }

    public User findById(Long id) {
        return getCurrentSession().get(User.class, id);
    }

    public List<User> findAll() {
        return getCurrentSession()
                .createQuery("from User", User.class)
                .getResultList();
    }

    public void delete(User user) {
        getCurrentSession().remove(user);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}