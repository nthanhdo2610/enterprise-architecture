package cs544;

public class ClassB {
    private String text;

    public ClassB() {
        System.out.println("Class B constructor - text: " + this.text);
    }

    public void showText() {
        System.out.println("Class B text: " + this.text);
    }
    public void init() {
        System.out.println("Class B init - text: " + this.text);
    }
    public void destroy() {
        System.out.println("Class B destroy");
    }
    public void setText(String text) {
        this.text = text;
    }

}
