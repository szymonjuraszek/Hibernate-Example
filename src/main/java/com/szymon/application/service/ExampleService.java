package com.szymon.application.service;

import com.szymon.application.dao.ExampleRepository;
import com.szymon.application.model.Example;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExampleService {

    private ExampleRepository repository;

    public ExampleService(ExampleRepository repository) {
        this.repository = repository;
    }

    public void saveByEntityManager(Example example) {
        repository.saveByEntityManager(example);
    }

    public void saveNormal(Example example) {
        repository.saveNormal(example);
    }

    public Example getByGet() {
        return repository.getByGet();
    }

    public Example getByLoad() {
        return repository.getByLoad();
    }

}
