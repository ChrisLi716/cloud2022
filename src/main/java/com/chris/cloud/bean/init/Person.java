package com.chris.cloud.bean.init;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Person {

    private String name;
    private String address;
    private int age;

    private void setPerson() {
        this.setAddress("baoji,shannxi,china").setName("chris").setAge(34);
    }

}
