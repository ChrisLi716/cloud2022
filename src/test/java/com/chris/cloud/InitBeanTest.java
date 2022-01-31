package com.chris.cloud;

import com.chris.cloud.bean.init.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class InitBeanTest {

    @Autowired
    private Person person;

    @Test
    public void testInitPersonBean() {
        log.info(person.toString());
    }


}
