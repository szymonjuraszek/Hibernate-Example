package com.szymon.application.dao;

import com.szymon.application.model.Example;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ExampleRepository  {

    public ExampleRepository() {
    }

    @PersistenceContext
    private EntityManager entityManager;

    public void saveByEntityManager(Example example) {
        Session session1 = entityManager.unwrap(Session.class);
        System.out.println(session1);
        entityManager.persist(example);
    }

    public void saveNormal(Example example) {
        Session session = entityManager.unwrap(Session.class);
        System.out.println(session);
        session.save(example);
    }

    public Example getByGet() {
        Session session = entityManager.unwrap(Session.class);

        Example example = session.get(Example.class, 1L);

        return example;
    }

    public Example getByLoad() {
        Session session = entityManager.unwrap(Session.class);

        Example example = session.load(Example.class, 1L);
//        Musi tu byc jakies uzycie bo nie zostana wyciagniete dane a sesji juz nie bedzie i wywali blad
        System.out.println(example);

        return example;
    }


}
