package com.chris.cloud.controller;

import com.chris.cloud.bean.SmsRequestBean;
import com.chris.cloud.bean.SmsResponseBean;
import com.chris.cloud.service.ISmsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/smsReqeust")
public class IdGeneratorController {
    @Resource
    private ISmsService smsService;

    @PostMapping("/send")
    public SmsResponseBean smsReuqest(@RequestBody SmsRequestBean smsRequestBean) {
        return smsService.sendSms(smsRequestBean);
    }
}
