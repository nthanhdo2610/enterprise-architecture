package cs544;

import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

//@Configuration
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        // Create the Spring 'root' application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(Config.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Register and map the dispatcher servlet
//        ServletRegistration.Dynamic studentDispatcher = container.addServlet("StudentsCourseServlet", new StudentsCourseServlet());
//        studentDispatcher.addMapping("/StudentsCourseServlet");

        FilterRegistration.Dynamic openInView = container.addFilter("OpenInView", new OpenEntityManagerInViewFilter());
        openInView.addMappingForUrlPatterns(null, true, "/*");
    }
}
