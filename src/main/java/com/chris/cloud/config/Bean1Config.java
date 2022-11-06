package com.chris.cloud.config;

import com.chris.cloud.config.bean.Bean1;
import com.chris.cloud.config.bean.Bean2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bean1Config {

    /**
     * 代码中bean1是定义在配置类中的，当执行到配置类解析的时候，@Component，@Service，@Controller ,@Configuration标注的类已经全部扫描，所以这些BeanDefinition已经被同步。 但是bean1的条件注解依赖的是bean2，bean2是被定义的配置类中的，所以此时配置类的解析无法保证先后顺序，就会出现不生效的情况。
     * 在spring ioc的过程中，优先解析@Component，@Service，@Controller注解的类。其次解析配置类，也就是@Configuration标注的类。最后开始解析配置类中定义的bean
     * <p>
     * 解决方法
     * 1. ConditionalOnClass(Bean2.class)来代替。
     * 2. 如果一定要区分两个配置类的先后顺序，可以将这两个类交与EnableAutoConfiguration管理和触发。
     * 也就是定义在META-INF\spring.factories中声明是配置类，然后通过
     * <p>
     * '@AutoConfigureBefore'
     * '@AutoConfigureAfter'
     * '@AutoConfigureOrder'
     * 控制先后顺序。之所以这么做是因为这三个注解只对自动配置类的先后顺序生效
     */
    @Bean
    // @ConditionalOnBean(value = Bean2.class)
    @ConditionalOnClass(value = Bean2.class)
    public Bean1 bean1() {
        return new Bean1();
    }
}
