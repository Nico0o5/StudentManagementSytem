import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class GradeManagementService {

    private GradeRepository gradeRepository;
    private CsvProcessor csvProcessor;
    private TxtProcessor txtProcessor;
    private StreamProcessor streamProcessor;

    GradeManagementService(GradeRepository gradeRepository, CsvProcessor csvProcessor, TxtProcessor txtProcessor, StreamProcessor streamProcessor){
        this.gradeRepository = gradeRepository;
        this.csvProcessor = csvProcessor;
        this.txtProcessor = txtProcessor;
        this.streamProcessor = streamProcessor;
    }

    public float computeAverage(){
        float sum = 0;
        List<Student> students = gradeRepository.getStudents();
        for (Student student : students) {
            sum += student.getGrade();
       }
        return sum / students.size();
    }

    public boolean isPassed(float grade){
        return grade >= 70;
    }

    public float getRating(float grade){
        return  grade / 10 ;
    }

    public String getCategory(int rating){
        return switch (rating) {
            case 10, 9 -> "Excellent Category";
            case 8 -> "Good Category";
            case 7 -> "Pass Category";
            case 6, 5, 4, 3, 2, 1 -> "Failed Category";
            default -> "Uncategorizable";
        };

    }

    public List<Student> getAverageToHighAchievers(){
        Predicate<Student> highAchievers = student -> student.getGrade() > 85;
        return streamProcessor.filter(gradeRepository.getStudents(), highAchievers);
    }

    public List<Student> getTopTwenty(){
        Comparator<Student> compareGrades = (student1, student2) -> Float.compare(student2.getGrade(), student1.getGrade());
        return streamProcessor.sort(gradeRepository.getStudents(), compareGrades);
    }

    public List<String> getEmails(){
        WebsiteValidator websiteValidator = new WebsiteValidator();
        Predicate<Student> isEmailValid = student -> websiteValidator.checkWebsite(student.getWebsite());
        return streamProcessor.extractEmail(gradeRepository.getStudents(), isEmailValid);
    }

    public float getAllAverage(){
        return streamProcessor.computeAllAverageGrade(gradeRepository.getStudents());
    }

    public Map<String, List<Student>> getStudentByCategory(){
        return streamProcessor.getStudentByCategory(gradeRepository.getStudents());
    }

    public List<String> getUniqueEmail(){
        return streamProcessor.getUniqueEmailDomain(gradeRepository.getStudents());
    }

    public Optional<Student> getInvalidStudent(){
        EmailValidator emailValidator = new EmailValidator();
        WebsiteValidator websiteValidator = new WebsiteValidator();
        Predicate<Student> isInvalid = student -> !emailValidator.checkEmail(student.getEmail()) && !websiteValidator.checkWebsite(student.getWebsite());
        return streamProcessor.findInvalidFirst(gradeRepository.getStudents(), isInvalid);
    } 

    public void writeTextFormat(){

        for (Student student : this.getAverageToHighAchievers()) {
            txtProcessor.writeText("\n \n \nGRADE 85: " + student);
        }
        for (Student student : this.getTopTwenty()){
            txtProcessor.writeText("\n \n \nTOP 20: " + student);
        }
        for(String email : this.getEmails()){
            txtProcessor.writeText("\n \n \nVALID EMAIL: " + email);
        }

        txtProcessor.writeText("\n \nAVERAGE GRADE: " + this.getAllAverage());
        for(String email : this.getUniqueEmail()){
            txtProcessor.writeText("\n \n \nUNIQUE EMAIL: " + email);
        }

        Optional<Student> student = this.getInvalidStudent();
        if(student.isPresent()){
            txtProcessor.writeText("\n \n \nINVALID STUDENT: " + student.get());
        }
        for (Map.Entry<String, List<Student>> entry : this.getStudentByCategory().entrySet()) {
                    String category = entry.getKey();
                    List<Student> students = entry.getValue();
    
                    for (Student std : students) {
                       txtProcessor.writeText(category + " " + std);
                    }
        }
        /* 
         * float grade = computeAverage();
         * EmailValidator emailValidator = new EmailValidator();
         * WebsiteValidator websiteValidator = new WebsiteValidator();

         * if(grade >= 80 && grade <= 90){
         *     txtProcessor.writeResult("Class average is " + grade + " and in the expected grade range.");
         * }
         * else{
         *     txtProcessor.writeResult("Class average is " + grade + " and not in the expected grade range.");
         * }

         * for (Student student : gradeRepository.getStudents()) {
         *     int result = (int) getRating(student.getGrade());
         *     String category = getCategory(result);
         *     String gradeRemarks = isPassed(student.getGrade()) ? "passed" : "failed"; 
         *     String emailResult = emailValidator.checkEmail(student.getEmail()) ? "valid" : "invalid";
         *     String websiteResult = websiteValidator.checkWebsite(student.getWebsite()) ? "valid" : "invalid";

         *     txtProcessor.writeResult("Student ID " + student.getId() + student.getFirstName() + " " + student.getLastName() +  " has a grade of " + (int) student.getGrade() + " and " + gradeRemarks);
         *     txtProcessor.writeResult("Student ID " + student.getId() +  " is in the " + category);
         *     txtProcessor.writeResult("The student email " + emailResult);
         *     txtProcessor.writeResult("The student website is " + websiteResult);
         * }
         */   
    }

    public void retrieveResult(){
        txtProcessor.readResult();
    }

    public void readFile(){
        gradeRepository.setStudents(csvProcessor.readStudent());
    }

    public void writeFile(){
        List<Student> students = gradeRepository.getStudents();
        for (Student student : students) {
            csvProcessor.writeStudent(student);
        }
    }

    

    /* public List<Student> getHighAchievers(){
     *      Predicate<Student> highAchievers = student -> student.getGrade() > 90;
     *      return streamProcessor.filter(gradeRepository.getStudents(), highAchievers);
     * }

     * public List<Student> getAverageStudent(){
     *      Predicate<Student> average = student -> student.getGrade() >= 75 || student.getGrade() <= 89;
     *      return streamProcessor.filter(gradeRepository.getStudents(), average);
     * }

     * public List<Student> getNeedsImprovement(){
     *      Predicate<Student> average = student -> student.getGrade() < 75;
     *      return streamProcessor.filter(gradeRepository.getStudents(), average);
     * }
     */ 

  



    

    
}