package com.szymon.application;

import com.example.demo.SomeClass;
import com.szymon.application.config.IBeanExample;
import com.szymon.application.model.Employee;
import com.szymon.application.model.Example;
import com.szymon.application.service.EmployeeService;
import com.szymon.application.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

    private final ExampleService exampleService;

    private final ApplicationContext appContext;

    private final EmployeeService employeeService;

    private final IBeanExample createBeanExample;

    @Autowired
    public Application (
            ExampleService exampleService,
            ApplicationContext appContext,
            EmployeeService employeeService,
            IBeanExample createBeanExample
    ) {
        this.employeeService = employeeService;
        this.exampleService = exampleService;
        this.appContext = appContext;
        this.createBeanExample = createBeanExample;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        String[] allBeanNames = appContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Runner start:");

        Example example1 = new Example("Szymon", 12);
        exampleService.saveByEntityManager(example1);

        Example example2 = new Example("Kozak", 13);
        exampleService.saveNormal(example2);

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Get from Database");
        System.out.println(exampleService.getByGet().toString());
        System.out.println(exampleService.getByLoad().toString());

        System.out.println("-----------------------------------------------------------------");
        System.out.println("Method save for Employee and Address: ");
        employeeService.saveWithoutTransaction();
        employeeService.saveWithTransaction();

        System.out.println(employeeService.getByIdCriteria(3L));
        System.out.println(employeeService.getByTimestampCriteria(new Date()));

        Optional<Employee> employee1 = employeeService.getSingleResult(3L);
        if(employee1.isPresent()) {
            System.out.println(employee1.get());
        }

        Optional<Employee> employee2 = employeeService.findBySingleResult(new Date());
        if(employee2.isPresent()) {
            System.out.println(employee2.get());
        }

        System.out.println("Pobieram Adres");
        Map<String,String> map = employeeService.getCityAndEmployeeNameByJoin(3L);
        for(Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ": " + value);
        }

        System.out.println("Pobieram dane z cusomowej klasy:");
        System.out.println(employeeService.getMapperClass().get(0).getCity());
//        System.out.println(employeeService.getMapperClassInnerClass().get(0).getCity());

        System.out.println("Check bean:");
        createBeanExample.showBean();

        SomeClass f = new SomeClass();

        System.out.println("Insert dla Market");


    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}
