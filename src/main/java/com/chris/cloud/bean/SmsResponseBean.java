package com.chris.cloud.bean;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class SmsResponseBean {
    private String code;
    private String message;
    private boolean success;
    private String result;
}
