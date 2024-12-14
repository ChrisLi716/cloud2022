package com.chris.cloud.controller;

import com.chris.cloud.bean.RspDTO;
import com.chris.cloud.bean.UserDTO;
import com.chris.cloud.service.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validated")
@Slf4j
public class ValidatedTestController {

    @PostMapping("/save")
    public RspDTO save(@RequestBody @Validated(Update.class) UserDTO userDTO) {
        return RspDTO.success();
    }
}
