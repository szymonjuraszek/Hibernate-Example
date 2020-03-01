package com.szymon.application.dao;

import com.szymon.application.model.Market;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MarketRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Market market) {
        Session session1 = entityManager.unwrap(Session.class);
        System.out.println(session1);
        entityManager.persist(market);
    }

    public void check() {
        Session session1 = entityManager.unwrap(Session.class);

        Market a = entityManager.find(Market.class, 1L);
        Market b = entityManager.find(Market.class, 4L);

        if(a.equals(b)){
            System.out.println("zle");
        } else {
            System.out.println("ok");
        }

        Market c = entityManager.find(Market.class, 1L);
        Market d = entityManager.find(Market.class, 1L);

        if(c.equals(d)){
            System.out.println("ok");
        } else {
            System.out.println("zle");
        }

        entityManager.detach(c);
        Market e = entityManager.find(Market.class, 1L);
        if(c.equals(e)){
            System.out.println("ok");
        } else {
            System.out.println("zle");
        }

        e = entityManager.merge(e);
        if(c.equals(e)){
            System.out.println("ok");
        } else {
            System.out.println("zle");
        }

    }


}
