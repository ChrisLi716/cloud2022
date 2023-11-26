package com.chris.cloud.service.impl;

import cn.hutool.json.JSONUtil;
import com.chris.cloud.bean.SmsRequestBean;
import com.chris.cloud.bean.SmsResponseBean;
import com.chris.cloud.enums.SmsTypeEnum;
import com.chris.cloud.service.ISmsPlugin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TencentSmsPlugin implements ISmsPlugin {
    @Override
    public SmsResponseBean sendSms(SmsRequestBean smsRequestBean) {
        log.info("tencent sms:{}", JSONUtil.toJsonStr(smsRequestBean));
        return SmsResponseBean.builder().code("200").message("send success").success(true).result("tencent sms response").build();

    }

    @Override
    public boolean supports(SmsRequestBean smsRequestBean) {
        return SmsTypeEnum.TENCENT == smsRequestBean.getSmsType();
    }
}
