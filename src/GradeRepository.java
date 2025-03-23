import java.util.ArrayList;
import java.util.List;

public class GradeRepository {

    private ArrayList<Student> students = new ArrayList<>();

    public void add(Student student){
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /* public Optional<Student> find(long id){
     *    for (Student student : students) {
     *        if(student.getId() == id){
     *            return student;
     *        }
     *    }
     *    return null;
     * }
     */ 
     
}