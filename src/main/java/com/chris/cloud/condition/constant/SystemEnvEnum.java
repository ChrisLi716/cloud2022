package com.chris.cloud.condition.constant;

import cn.hutool.core.util.StrUtil;

public enum SystemEnvEnum {

    DEV("dev"),
    TEST("test", "test01"),
    PROD("prod"),
    LOCAL("local");
    private final String[] values;

    SystemEnvEnum(String... values) {
        this.values = values;
    }

    public static SystemEnvEnum getEnv(String value) {
        SystemEnvEnum[] envs = SystemEnvEnum.values();
        for (SystemEnvEnum env : envs) {
            String[] values = env.values;
            for (String s : values) {
                if (StrUtil.equalsIgnoreCase(s, value)) {
                    return env;
                }
            }
        }
        return null;
    }
}
