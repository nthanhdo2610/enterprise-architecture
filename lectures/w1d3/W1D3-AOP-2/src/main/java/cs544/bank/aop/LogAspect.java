package cs544.bank.aop;

import cs544.bank.domain.Account;
import cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAspect {

    private final ILogger logger;

    public LogAspect(ILogger logger) {
        this.logger = logger;
    }

    @After("execution(* cs544.bank.dao.*.*(..))")
    public void logAfterDAO(JoinPoint jp) {
        Object[] args = jp.getArgs();
        StringBuilder sb = new StringBuilder()
                .append(jp.getTarget().getClass().getSimpleName())
                .append(": ")
                .append(jp.getSignature().getName());
        if (args == null || args.length == 0) {
            logger.log(sb.toString());
        } else if (args[0] instanceof Account ac) {
            sb.append(" account with accountnr= ").append(ac.getAccountnumber());
            logger.log(sb.toString());
        } else if (args[0] instanceof Number n) {
            sb.append(" account with accountnr= ").append(n);
            logger.log(sb.toString());
        }
    }

    @Around("execution(* cs544.bank.service.*.*(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        logger.log(String.format("Time to %s execute %s = %s ms", call.getTarget().getClass().getSimpleName(), call.getSignature().getName(), totalTime));
        return retVal;
    }

    @After("execution(* cs544.bank.jms.JMSSender.sendJMSMessage(String))")
    public void logAfterSendJMSMessage(JoinPoint jp) {
        Object[] args = jp.getArgs();
        logger.log("JMSSender: sending JMS message =" + args[0]);
    }
}
