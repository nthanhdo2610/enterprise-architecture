package cs544;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

@WebServlet(name = "StudentsCourseServlet", urlPatterns = "/StudentsCourseServlet")
public class StudentsCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String studentIdStr = request.getParameter("studentid");

        long studentid;
        Student student = null;

        ServletContext context = getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
        StudentService studentService = applicationContext.getBean(
                "studentService", StudentService.class);


        if (studentIdStr != null && studentIdStr.matches("\\d+")) {
            studentid = Long.parseLong(studentIdStr);
            student = studentService.getStudent(studentid);
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("student.jsp").forward(request, response);

    }

}
