package com.chris.cloud.service.impl;

import com.chris.cloud.bean.SmsRequestBean;
import com.chris.cloud.bean.SmsResponseBean;
import com.chris.cloud.service.ISmsPlugin;
import com.chris.cloud.service.ISmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService implements ISmsService {

    private final PluginRegistry<ISmsPlugin, SmsRequestBean> pluginRegistry;

    @Override
    public SmsResponseBean sendSms(SmsRequestBean smsRequestBean) {
        return pluginRegistry.getPluginFor(smsRequestBean)
                .orElseThrow(() -> new RuntimeException("sms plugin is not binder with type:" + smsRequestBean.getSmsType()))
                .sendSms(smsRequestBean);

    }
}
