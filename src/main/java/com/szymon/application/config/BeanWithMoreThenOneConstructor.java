package com.szymon.application.config;

import com.szymon.application.service.EmployeeService;
import com.szymon.application.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanWithMoreThenOneConstructor {

    private EmployeeService employeeService;

    private ExampleService exampleService;

    private String beanName;

    public BeanWithMoreThenOneConstructor(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public BeanWithMoreThenOneConstructor() {
    }

    @Autowired
    public BeanWithMoreThenOneConstructor(EmployeeService employeeService, ExampleService exampleService, String beanName) {
        this.employeeService = employeeService;
        this.exampleService = exampleService;
        this.beanName = beanName;

        System.out.println(beanName);
    }
}
