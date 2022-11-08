package com.chris.cloud.condition.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class Person {
    private String name;

    @Autowired
    private Animal animal;
}
