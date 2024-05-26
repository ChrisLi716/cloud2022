package com.chris.cloud.threadlocal;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalTest {

    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    /**
     * 可以在父线程中设置线程本地变量的值，然后在子线程中获取这个值，从而实现线程之间数据的传递和共享。
     */
    @Test
    public void testInheritableThreadLocal01() throws InterruptedException {
        threadLocal.set("value");
        Thread thread = new Thread(() -> {
            String value = threadLocal.get();
            System.out.println("Value in child thread: " + value);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * InheritableThreadLocal 类支持线程之间的继承，即子线程可以继承父线程中的线程本地变量。
     * 这意味着可以在父线程中设置线程本地变量的值，在子线程中直接获取到这个值。
     */
    @Test
    public void testInheritableThreadLocal02() {
        Thread parentThread = new Thread(() -> {
            threadLocal.set("value");
            Thread childThread = new Thread(() -> {
                String value = threadLocal.get();
                System.out.println("Value in child thread: " + value);
            });
            childThread.start();
        });
        parentThread.start();
    }
}
