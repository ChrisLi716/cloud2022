package com.chris.cloud.bean;

import com.chris.cloud.enums.SmsTypeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class SmsRequestBean implements Serializable {
    private SmsTypeEnum smsType;
}
