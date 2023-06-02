package cs544;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Aspect
public class AwesomeAspect {
    @Value("Test")
    private String text;

    public AwesomeAspect() {
        System.out.println("AwesomeAspect Constructor - text: " + text);
    }

    @PostConstruct
    public void start() {
        System.out.println("AwesomeAspect start method - text: " + text);
    }

    @Around("execution(* cs544.*.get*(..))")
    public Object beforeTrace(ProceedingJoinPoint jp) throws Throwable {
        String name = jp.getTarget().getClass().getSimpleName();
        if (name.equals("BClass")) {
            return "Something";
        }
        return jp.proceed();
    }
}
