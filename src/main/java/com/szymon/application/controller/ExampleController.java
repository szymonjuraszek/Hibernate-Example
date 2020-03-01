package com.szymon.application.controller;

import com.szymon.application.model.Example;
import com.szymon.application.service.ExampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExampleController {

    private final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping(path = "/examples")
    public List<Example> getAllExample() {
        return exampleService.getAllExample();
    }

    @GetMapping(path = "/examples/{id}")
    public Optional<Example> getAllExample(@PathVariable Long id) {
        return exampleService.getExampleById(id);
    }

    @PostMapping(path = "/examples")
    public ResponseEntity<Example> addExample(@RequestBody Example example) {
        return new ResponseEntity<>(exampleService.addExample(example), HttpStatus.CREATED);
    }
}
