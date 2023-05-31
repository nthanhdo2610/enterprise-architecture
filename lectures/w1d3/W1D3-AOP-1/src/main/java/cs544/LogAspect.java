package cs544;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class.getName());

//    @Before("execution(* cs544.EmailSender.*(..))")
//    public void logBefore(JoinPoint joinpoint) {
//        logger.warn("About to exec: " + joinpoint.getSignature().getName());
//    }

    @After("execution(* cs544.EmailSender.*(..))")
    public void logAfter(JoinPoint joinpoint) {
        logger.warn("method= " + joinpoint.getSignature().getName());
    }
}
