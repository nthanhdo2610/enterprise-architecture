package cs544;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Aspect
public class TraceAspect {
    @Value("Trace")
    private String text;

    public TraceAspect() {
        System.out.println("TraceAspect Constructor - text: " + text);
    }

    @PostConstruct
    public void start() {
        System.out.println("TraceAspect start method - text: " + text);
    }

    @Before("execution(* cs544.*.*(..))")
    public void beforeTrace(JoinPoint jp) {
        if (jp.getTarget() instanceof MyClass mc) {
            mc.setText("Change");
        }
        System.out.println(text + " Before Method " + jp.getSignature().getName());
    }
}
