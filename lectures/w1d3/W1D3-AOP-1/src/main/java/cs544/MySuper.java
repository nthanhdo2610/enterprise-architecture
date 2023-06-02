package cs544;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public abstract class MySuper {
    @Value("Test")
    private String text;
    public MySuper() {
        System.out.println("MySuper Constructor - text: " + this.text);
    }
    public void setText(String text) {
        System.out.println("Setting text to: " + text);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
