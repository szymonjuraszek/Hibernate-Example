package com.szymon.application.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface IEmployeeDao {

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    class MapperClassExample {
        private String zipcode;
        private String city;
        private Long id;
    }
}
