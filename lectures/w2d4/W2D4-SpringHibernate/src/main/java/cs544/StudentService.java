package cs544;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentService {
    private final StudentDAO studentdao;

    public StudentService(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }

    public Student getStudent(long studentid) {
        return studentdao.load(studentid);
    }
}
