package cs544;

import org.springframework.stereotype.Component;

@Component
public class AClass extends MySuper{
    private final BClass bClass;

    public AClass(BClass bClass) {
        System.out.println("MyClass Constructor");
        setText("Hello");
        this.bClass = bClass;
    }

    public void getAText() {
        System.out.println("This is a " + getText());
    }
    public void getBText() {
        System.out.println("This is a " + bClass.getText());
    }
}
