package com.chris.cloud.service;

import com.chris.cloud.bean.SmsRequestBean;
import com.chris.cloud.bean.SmsResponseBean;
import org.springframework.plugin.core.Plugin;

public interface ISmsPlugin extends Plugin<SmsRequestBean> {
    SmsResponseBean sendSms(SmsRequestBean smsRequestBean);
}
