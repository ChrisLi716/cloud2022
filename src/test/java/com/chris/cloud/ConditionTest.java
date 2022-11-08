package com.chris.cloud;

import com.chris.cloud.condition.bean.Person;
import com.chris.cloud.condition.config.BeanConfig;
import com.chris.cloud.condition.config.BeanConfigV2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConditionTest {

    @Test
    public void test(){
        // AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfigV2.class);
        Person person = (Person)annotationConfigApplicationContext.getBean("person");
        System.out.println(person.getAnimal().getName());
    }
}
