package com.chris.cloud.controller;

import cn.hutool.core.map.MapUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController()
@RequestMapping("/thread-local")
@Slf4j
public class ThreadLocalController {

    private static final ExecutorService executor = new ThreadPoolExecutor(4, 8, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    private static final ThreadLocal<Integer> tl = new ThreadLocal<>();
    private static final TransmittableThreadLocal<Integer> ttl = new TransmittableThreadLocal<>();
    private static final TransmittableThreadLocal<Map<Integer, Integer>> ttlMap = new TransmittableThreadLocal<>();
    private static final TransmittableThreadLocal<Map<Integer, Integer>> ttlMap2 = new TransmittableThreadLocal() {

        @Override
        protected Object initialValue() {
            return MapUtil.newHashMap(true);
        }

        @Override
        protected Map<Integer, Integer> childValue(Object parentValue) {
            if (parentValue instanceof Map) {
                return new LinkedHashMap<Integer, Integer>((Map) parentValue);
            }
            return null;
        }

        /**
         * 重写copy方法，replay时给子线程设置变量的value不再是父线程的map
         */
        @Override
        public Object copy(Object parentValue) {
            if (parentValue instanceof Map) {
                return new LinkedHashMap<Integer, Integer>((Map) parentValue);
            }
            return null;
        }
    };
    private final AtomicInteger i = new AtomicInteger();

    private Integer m = 0;

    static {
        init();
    }

    @GetMapping("/tl")
    public boolean getThreadLocal() {
        tl.set(i.incrementAndGet());
        Integer value = tl.get();
        log.info("value:{}", value);
        tl.remove();
        return true;
    }

    @GetMapping("/ttl/test1")
    public void getTtl1() {
        log.info("begin main thread");
        ttl.set(i.incrementAndGet());
        executor.execute(TtlRunnable.get(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                log.info("sub thread get the value:{}", ttl.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        ttl.remove();
        log.info("end main thread");
    }

    /**
     * begin main thread:{}
     * main thread put:1
     * after main thread remove, get value:null
     * end main thread
     * sub thread get the value:{1=1}
     * begin main thread:{1=1}
     * main thread put:2
     * after main thread remove, get value:null
     * end main thread
     * sub thread get the value:{1=1, 2=2}
     * <p>
     * 在静态代码块里面调用init方法，main线程加载的类， ttlMap属于主线程
     * 当请求到来呢，main线程开了子线程，子线程复制了main线程并引用了main线程的引用的map
     * ttlMap.remove()只是把当前子线程从ThreadLocal中移除掉，使用的map还是主线程的map, 即map共享了。
     */
    @GetMapping("/ttl/test2")
    public void getTtl2() {
        Map<Integer, Integer> map = ttlMap.get();
        if (Objects.isNull(map)) {
            init();
        }
        log.info("begin main thread:{}", ttlMap.get());
        ttlMap.get().put(++m, m);
        log.info("main thread put:{}", m);

        executor.execute(TtlRunnable.get(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                log.info("sub thread get the value:{}", ttlMap.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        ttlMap.remove();
        log.info("after main thread remove, get value:{}", ttlMap.get());
        log.info("end main thread");
    }


    /**
     * begin init~~
     * begin main thread:{}
     * main thread put:1
     * after main thread submit task, then put:2
     * after main thread remove, get value:null
     * end main thread
     * sub thread get the value:{1=1, 2=2}
     * <p>
     * 子线程持有的map都是同一个
     */
    @GetMapping("/ttl/test3")
    public void getTtl3() {
        log.info("begin main thread:{}", ttlMap2.get());
        ttlMap2.get().put(++m, m);
        log.info("main thread put:{}", m);

        executor.execute(TtlRunnable.get(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                log.info("sub thread get the value:{}", ttlMap2.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
        ttlMap2.get().put(++m, m);
        log.info("after main thread submit task, then put:{}", m);
        ttlMap2.remove();
        log.info("after main thread remove, get value:{}", ttlMap2.get());
        log.info("end main thread");
    }

    private static synchronized void init() {
        log.info("begin init~~");
        Map<Integer, Integer> map = ttlMap.get();
        if (Objects.isNull(map)) {
            map = MapUtil.newHashMap(true);
            ttlMap.set(map);
        }
    }


}

