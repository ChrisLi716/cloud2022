package com.chris.cloud.condition;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.StandardMethodMetadata;

public class PetCondition implements Condition {
    @Override
    public boolean matches(@NotNull ConditionContext context, @NotNull AnnotatedTypeMetadata metadata) {

        System.out.println(metadata.getClass().getName());
        StandardMethodMetadata standardMethodMetadata = (StandardMethodMetadata) metadata;
        System.out.println(standardMethodMetadata.getMethodName());

        //获取注册的bean
        BeanDefinitionRegistry registry = context.getRegistry();

        //获取环境配置
        //context.getEnvironment();

        return registry.containsBeanDefinition("person") && !registry.containsBeanDefinition("cat");
    }
}
