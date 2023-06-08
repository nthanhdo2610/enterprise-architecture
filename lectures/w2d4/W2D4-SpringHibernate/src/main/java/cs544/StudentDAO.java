package cs544;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class StudentDAO {
    @PersistenceContext
    private EntityManager em;

    public Student load(long studentid) {
        return em.find(Student.class, studentid);
    }
}
