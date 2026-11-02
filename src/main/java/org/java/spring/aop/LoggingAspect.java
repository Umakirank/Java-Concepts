package org.java.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP - ASPECT ORIENTED PROGRAMMING
 *
 * DEFINITION: separate CROSS-CUTTING concerns (logging, security, transactions, caching, timing)
 *   from business logic.
 *
 * TERMS:
 *   Aspect     : class holding the cross-cutting logic (@Aspect).
 *   Advice     : the action: @Before, @After, @AfterReturning, @AfterThrowing, @Around.
 *   Pointcut   : expression that selects WHICH methods (execution(* pkg..*(..))).
 *   JoinPoint  : a point in execution (in Spring AOP always a method call).
 *   Weaving    : linking aspects to targets. Spring does it at RUNTIME through proxies
 *                (JDK dynamic proxy for interfaces, CGLIB for classes).
 *
 * @Transactional, @Cacheable, @Async and Spring Security method security all work through AOP proxies.
 */
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* org.java.spring.service.EmployeeServiceImpl.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void before(JoinPoint jp) {
        System.out.println("[AOP] -> " + jp.getSignature().getName());
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void onError(JoinPoint jp, Exception ex) {
        System.out.println("[AOP] !! " + jp.getSignature().getName() + " failed: " + ex.getMessage());
    }

    @Around("serviceMethods()")
    public Object time(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        try {
            return pjp.proceed();                        // call the real method
        } finally {
            System.out.println("[AOP] " + pjp.getSignature().getName() + " took "
                    + (System.nanoTime() - start) / 1_000_000.0 + " ms");
        }
    }
}
