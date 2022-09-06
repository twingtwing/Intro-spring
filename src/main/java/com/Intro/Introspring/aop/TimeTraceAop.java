package com.Intro.Introspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //@으로 등록 가능하지만, Bean으로 직접 기입하는것을 권장
//AOP는 사용함을 인지할 수 있는게 좋기 때문, 또한, 정형화 된게 아니기 때문?
public class TimeTraceAop {

    @Around("execution(* com.Intro.Introspring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START:"+joinPoint.toString());
        try{
            return joinPoint.proceed(); // 다음메서드로 진행이됨
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("START:"+joinPoint.toString()+" "+timeMs+"ms");
        }
    }
}
