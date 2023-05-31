package cs544;

public class ClassA {
    private String text;
    private ClassB classB;

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public ClassA(String text) {
        this.text = text;
        System.out.println("Class A constructor - text: " + this.text);
    }
    public void showText() {
        System.out.println("Class A text: " + this.text);
        this.classB.showText();
    }
    public void init() {
        System.out.println("Class A init - text: " + this.text);
    }
    public void destroy() {
        System.out.println("Class A destroy");
    }
}
