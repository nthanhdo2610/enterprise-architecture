package cs544;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.springframework.util.StopWatch;

//@Aspect
//@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class.getName());

    @After("execution(* cs544.EmailSender.sendEmail(String, String))")
    public void logAfter(JoinPoint joinpoint) {
        EmailSender em = (EmailSender) joinpoint.getTarget();
        Object[] args = joinpoint.getArgs();
        logger.warn(String.format("method=%s address=%s message=%s outgoing mail server = %s", joinpoint.getSignature().getName(), args[0], args[1], em.getOutgoingMailServer()));
    }

    @Around("execution(* cs544.CustomerDAO.*(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {

        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        logger.warn(String.format("Time to execute save = %s ms", totalTime));
        return retVal;
    }
}
