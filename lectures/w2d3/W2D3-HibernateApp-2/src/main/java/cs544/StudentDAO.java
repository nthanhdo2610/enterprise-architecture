package cs544;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

import java.util.HashMap;
import java.util.Map;

public class StudentDAO {

    public StudentDAO() {
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
