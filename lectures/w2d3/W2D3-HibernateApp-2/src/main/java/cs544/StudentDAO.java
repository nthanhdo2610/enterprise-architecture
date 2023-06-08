package cs544;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

import java.util.HashMap;
import java.util.Map;

public class StudentDAO {

    public StudentDAO() {
        EntityManager em = EntityManagerHelper.getCurrent();
        Student student = new Student(12345, "Frank", "Brown");
        Course course1 = new Course(1101, "Java", "A");
        Course course2 = new Course(1102, "Math", "B+");
        student.addCourse(course1);
        student.addCourse(course2);
        em.persist(student);
    }

    public Student load(long studentid) {
        EntityManager em = EntityManagerHelper.getCurrent();
        EntityGraph<Student> graph = em.createEntityGraph(Student.class);
        graph.addSubgraph("courselist");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        return em.find(Student.class, studentid, properties);
    }
}
