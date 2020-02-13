package com.szymon.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class MapperClass {
    private String zipcode;
    private String city;
    private Long id;
}
