package com.chris.cloud.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author Chris
 * @date 2022-04-11 8:58 PM
 */
public interface ITestRetryService {

    int test(int code) throws Exception;
}
