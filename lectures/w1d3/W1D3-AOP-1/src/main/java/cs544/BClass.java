package cs544;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
@Lazy
public class BClass {
    @Value("Other")
    private String text;

    public BClass() {
        System.out.println("BClass constructor");
    }

    public String getText() {
        return text;
    }

    @PreDestroy
    public void done(){
        System.out.println("BClass done!");
    }
}
