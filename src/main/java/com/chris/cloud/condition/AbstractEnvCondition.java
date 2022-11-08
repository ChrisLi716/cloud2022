package com.chris.cloud.condition;

import cn.hutool.core.util.StrUtil;
import com.chris.cloud.condition.constant.SystemEnvEnum;
import com.chris.cloud.condition.util.EnvUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public abstract class AbstractEnvCondition implements Condition {

    @Override
    public boolean matches(@NotNull ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {
        String conditionProperty = getConditionProperty();
        if (StrUtil.isBlank(conditionProperty)) {
            return false;
        }

        //优先处理指定的开关配置
        String property = context.getEnvironment().getProperty(conditionProperty);
        if (StrUtil.isNotBlank(property)) {
            return Boolean.parseBoolean(property);
        }

        //没有指定开关，则判断容器环境，本地关闭，其它环境打开
        return EnvUtil.getSystemEnv() != SystemEnvEnum.LOCAL;

    }

    protected abstract String getConditionProperty();
}
