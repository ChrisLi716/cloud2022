package com.chris.cloud.condition.util;

import com.chris.cloud.condition.constant.EnvConstants;
import com.chris.cloud.condition.constant.SystemEnvEnum;

import java.util.Objects;

public final class EnvUtil {

    public static SystemEnvEnum getSystemEnv() {
        String env_value = System.getenv(EnvConstants.PROPERTY_SYSTEM_ENV);
        SystemEnvEnum envEnum = SystemEnvEnum.getEnv(env_value);
        if (Objects.isNull(envEnum)) {
            envEnum = SystemEnvEnum.LOCAL;
        }
        return envEnum;
    }
}
