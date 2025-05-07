package org.example.demo.Repository;

import org.example.demo.Entity.LocationEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class LocationRepository {
    private final SessionFactory sessionFactory;

    public LocationRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<LocationEntity> findAllByUserId(int userId) {
        return getCurrentSession().createQuery("from LocationEntity where userEntity.id = :userId ",LocationEntity.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    public List<LocationEntity> findAllByNameAndUserId(String name, Integer userId) {

        return getCurrentSession().createQuery("select l from LocationEntity l  where userEntity.id = :userId and l.name LIKE :wheater",LocationEntity.class)
                .setParameter("weather","%"+name+"%")
                .setParameter(userId,userId)
                .getResultList();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void deleteWeather(BigDecimal longitude,BigDecimal latitude,String loginID) {
        LocationEntity locationEntity = getCurrentSession()
                .createQuery("from LocationEntity l where userEntity.id = :loginID " +
                        "and longitude = :longitude and latitude = :latitude",LocationEntity.class)
                .setParameter("loginID",loginID)
                .setParameter("longitude",longitude)
                .setParameter("latitude",latitude)
                .setMaxResults(1)
                .getSingleResult();

        getCurrentSession().remove(locationEntity);
    }
}
