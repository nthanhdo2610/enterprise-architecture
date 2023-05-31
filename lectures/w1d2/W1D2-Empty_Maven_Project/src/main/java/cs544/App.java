package cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {
    public static void main(String[] args) {
//        System.out.println("FIXME: Empty Project");
//        ApplicationContext context = new
//                AnnotationConfigApplicationContext(Config.class);
        ConfigurableApplicationContext context = new
                ClassPathXmlApplicationContext("springconfig.xml");
//        Greeting greeting = context.getBean("greeting", Greeting.class);
//        greeting.sayHello();
        System.out.println("Testing Spring startup");
        ClassA classA = context.getBean("classA", ClassA.class);
        classA.showText();
        context.close();
    }
}
