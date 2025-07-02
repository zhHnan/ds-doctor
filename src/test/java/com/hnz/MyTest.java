package com.hnz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

/**
 * @Author：hnz
 * @Project：ds-doctor
 * @name：MyTest
 * @Date：2025/6/29 20:14
 * @Filename：MyTest
 */

@SpringBootTest
public class MyTest {

    @Test
    public void testStopWatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("开始执行");
        System.out.println("结束执行");
        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getTotalTimeMillis());
    }
}
