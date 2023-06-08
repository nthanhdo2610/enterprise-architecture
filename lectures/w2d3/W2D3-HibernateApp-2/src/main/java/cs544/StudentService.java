package cs544;

public class StudentService {
    private final StudentDAO studentdao;

    public StudentService() {
        studentdao = new StudentDAO();
    }

    public Student getStudent(long studentid) {
        return studentdao.load(studentid);
    }
}
