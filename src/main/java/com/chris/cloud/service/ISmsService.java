package com.chris.cloud.service;

import com.chris.cloud.bean.SmsRequestBean;
import com.chris.cloud.bean.SmsResponseBean;

public interface ISmsService {
    SmsResponseBean sendSms(SmsRequestBean  smsRequestBean);
}
