package com.chris.cloud.spel.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Inventor {
    private String name;
    private Date birthday;
    private String nationality;
}
