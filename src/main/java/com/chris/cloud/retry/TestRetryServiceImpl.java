package com.chris.cloud.retry;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author Chris
 * @date 2022-04-11 8:58 PM
 */
@Service
public class TestRetryServiceImpl implements ITestRetryService {

    @Override
    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 1))
    public int test(int code) throws Exception {
        System.out.println("test被调用,时间：" + LocalTime.now());
        if (code == 0) {
            throw new Exception("情况不对头！");
        }
        System.out.println("test被调用,情况对头了！");
        return 200;
    }


    @Recover
    public int recover(Throwable e, int code) {
        System.out.println("recover1 回调方法执行");
        //记日志到数据库 或者调用其余的方法
        return 400;
    }

}
