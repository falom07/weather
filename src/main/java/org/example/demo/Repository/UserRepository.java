package org.example.demo.Repository;

import org.example.demo.Entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean existByLogin(String login) {
        return getCurrentSession().createQuery
                ("select u.login from UserEntity u where u.login = :login",String.class)
                .setParameter("login",login)
                .setMaxResults(1)
                .uniqueResult() != null;
    }

    public Long save(UserEntity newUserEntity) {
        getCurrentSession().persist(newUserEntity);

        return newUserEntity.getId();

    }

    public UserEntity getUserByUserName(String login) {
        return getCurrentSession().createQuery("FROM UserEntity u where u.login = :login",UserEntity.class)
                .setParameter("login",login)
                .uniqueResult();
    }

    public String getLoginByID(String loginID) {
        return getCurrentSession().get(UserEntity.class,loginID).getLogin();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}
