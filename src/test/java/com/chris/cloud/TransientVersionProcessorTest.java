package com.chris.cloud;

import com.chris.annotation.TransientVersion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TransientVersionProcessorTest {

    @TransientVersion
    private static final String version = "";

    @Test
    public void testProcess() {
        log.info("version: {}", version);
    }
}
