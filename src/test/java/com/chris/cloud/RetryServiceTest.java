package com.chris.cloud;

import com.chris.cloud.retry.TestRetryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Chris
 * @date 2022-04-11 9:00 PM
 */
@SpringBootTest
@Slf4j
public class RetryServiceTest {

    @Autowired
    private TestRetryServiceImpl testRetryService;

    @Test
    public void testRetry() {
        try {
            testRetryService.test(0);
        } catch (Exception e) {
            log.error("error happened", e);
        }
    }
}
