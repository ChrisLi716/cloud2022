package com.chris.cloud.bean.wrapper.beans;

import lombok.Data;

import java.util.Map;

@Data
public class Employee {
    private String name;
    private float salary;
    private String[] address;
    private Map<String, String> account;
}
