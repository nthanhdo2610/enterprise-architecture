package cs544;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//		ICustomerService customerService = context.getBean("customerService", ICustomerService.class);
//
//		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
//				"mainstreet 5", "Chicago", "60613");

		System.out.println("Testing Spring Startup");

//		MyClass mc = context.getBean("myClass", MyClass.class);
//		mc.sayHello();

		AClass aClass = context.getBean(AClass.class);
		aClass.getAText();
		aClass.getBText();

		context.close();

	}
}
