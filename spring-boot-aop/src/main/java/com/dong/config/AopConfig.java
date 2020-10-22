package com.dong.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author DongJian
 * @date Created in 2020/10/22 14:43
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
@Slf4j
@Aspect
@Component
public class AopConfig {


    /**
     * 定义一个切点
     **/
    @Pointcut(value = "execution(public String test(..))")
    public void cutOffPoint() {

    }

    /**
     * 前置通知
     **/
    @Before("cutOffPoint()")
    public void before() {
        System.out.println("前置通知，在切点前执行");
    }

    /**
     * 后置通知
     **/
    @After("cutOffPoint()")
    public void after() {
        System.out.println("后置通知，在切点后执行");
    }

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 环绕执行的应用场景
     * 1.全局异常处理
     * 2.日志操作
     * 3.全局的事务处理
     * 4.数据的全局缓存
     **/
    @Around("cutOffPoint()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        startTime.set(System.currentTimeMillis());
        // ...做一些类似于日志操作
        log.info("我是环绕通知执行");
        Object obj;
        try {
            // 必须要执行proceed方法，用于启动目标方法的执行，若不执行则目标方法也不会执行
            obj = proceedingJoinPoint.proceed();
            // 执行记录
            log.info("{} 方法执行时间为：{}", proceedingJoinPoint.getSignature().getName(), (System.currentTimeMillis() - startTime.get()));
        } catch (Throwable throwable) {
            obj = throwable.toString();
        }
        return obj;
    }


    /**
     * @Description 切入点返回执行结果之后执行
     * 可以用于支付完成后对参数的二次校验，防止在方法执行时参数被修改
     **/
    @AfterReturning(returning = "result", pointcut = "cutOffPoint()")
    public void doAfterReturning(Object result) {
        // 制造错误
        int i = 1 / 0;
        log.info("大家好，我是xx,他们都秀完了，该我上场了！");
    }

    /**
     * 切入执行报错的时候执行
     **/
    @AfterThrowing(throwing = "e",pointcut = "cutOffPoint()")
    public void doAfterThrowing(Throwable e){
        log.info("我是AfterThrowing,他们犯的错误，我来背锅！");
        log.error("错误信息：{}",e.getMessage());
    }
}
