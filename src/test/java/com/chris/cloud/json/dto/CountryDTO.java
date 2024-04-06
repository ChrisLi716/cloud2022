package com.chris.cloud.json.dto;

import lombok.Data;

@Data
public class CountryDTO {
    private String country;

    public Boolean isChinaName() {
        return this.country.equals("中国");
    }
}
