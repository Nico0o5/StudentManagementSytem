// import java.io.File;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

public class GradeManagementMain {

    public static void main(String[] args) throws Exception{

        
        GradeRepository gradeRepository = new GradeRepository();
        CsvProcessor csvProcessor = new CsvProcessor();
        TxtProcessor txtProcessor = new TxtProcessor();
        StreamProcessor streamProcessor = new StreamProcessor();

        GradeManagementService gradeManagementService = new GradeManagementService(gradeRepository, csvProcessor, txtProcessor, streamProcessor);

        gradeManagementService.readFile();
        gradeManagementService.writeTextFormat();


        /*
         * Student student1 = new Student(101, "Keith", "Diaz", 22, 'M', 85, "1", "keidiaz@gmail.com", "http://keithdiaz.com");
         * Student student2 = new Student(102, "Nico", "Carnate", 18, 'M', 90, "1", "nicocarnate@spinifexit.com", "https://-invalid.com");
         * Student student3 = new Student(103, "Paulo", "Evangelista", 23, 'M', 69, "1", "pauloeva@fsafkas", "http://example.com");
         * Student student4 = new Student(104, "Brent", "David", 24, 'M', 71, "1", "SSFAi@@@gmail.com", "https://sub.domain.net/page");
         * gradeRepository.add(student1);
         * gradeRepository.add(student2);
         * gradeRepository.add(student3);
         * gradeRepository.add(student4);
         */


    }
}