package cs544;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class.getName());

    //    @Before("execution(* cs544.EmailSender.*(..))")
//    public void logBefore(JoinPoint joinpoint) {
//        logger.warn("About to exec: " + joinpoint.getSignature().getName());
//    }
//
    @After("execution(* cs544.EmailSender.sendEmail(String, String))")
    public void logAfter(JoinPoint joinpoint) {
        EmailSender em = (EmailSender) joinpoint.getTarget();
        Object[] args = joinpoint.getArgs();
        logger.warn(String.format("method=%s address=%s message=%s outgoing mail server = %s", joinpoint.getSignature().getName(), args[0], args[1], em.outgoingMailServer));
    }

//    @AfterReturning(pointcut="execution(* cs544.EmailSender.getOutgoingMailServer())", returning="ret")
//    public void afterRet(JoinPoint jp, String ret) {
//        logger.warn(String.format("outgoing mail server = %s", ret));
//    }

//    @Around("execution(* cs544.EmailSender.sendEmail(String, String))")
//    public Object aroundSetName(ProceedingJoinPoint joinpoint) throws Throwable {
//        Object[] args = joinpoint.getArgs();
//        logger.warn(String.format("method=%s address=%s message=%s", joinpoint.getSignature().getName(), args[0], args[1]));
//        return joinpoint.proceed(args);
//    }
}
