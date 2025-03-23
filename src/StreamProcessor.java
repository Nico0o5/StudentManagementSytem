import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class StreamProcessor {

    Map<String, List<Student>> getStudentByCategory(List<Student> list){
        return list.stream()
            .collect(Collectors.groupingBy( (student) -> {if(student.getGrade() >= 90) return "High Achievers";
                                                            else if(student.getGrade() < 90 && student.getGrade() > 75) return "Average";
                                                            else return "Needs Improvement";}));
    }

    List<Student> filter(List<Student> list, Predicate<Student> condition){
        return list.stream()
                   .filter(condition)
                   .toList();
        
    }

    List<Student> sort(List<Student> list, Comparator<Student> comparator){
        return list.stream()
                   .sorted(comparator)
                   .limit(20)
                   .toList();
    }

    List<String> extractEmail(List<Student> list, Predicate<Student> condition){
        return list.stream()
                   .filter(condition)
                   .map((student) -> student.getEmail())
                   .toList();
    }

    float computeAllAverageGrade(List<Student> list){
        float average =  0f;
        Optional<Float> sum = list.stream()
                              .map((student) -> student.getGrade())
                              .reduce((grade1, grade2) -> grade1 + grade2);
        if(sum.isPresent()){
            average =  sum.get() / list.size();
        }
        return average;
    }

    List<String> getUniqueEmailDomain(List<Student> list){
        
        return list.stream()
                   .map((student) -> {
                        String[] emailParts = student.getEmail().split("@");
                        if(emailParts.length > 1){
                            String[] rightpart = emailParts[emailParts.length - 1].split("\\.");
                            if(rightpart.length > 1)
                                return rightpart[rightpart.length - 1];
                        }
                        return ""; 
                    })
                   .distinct()
                   .filter(domain -> domain.length() > 0)
                   .toList();

    }

    Optional<Student> findInvalidFirst(List<Student> list, Predicate<Student> condition){
        return list.stream()
                   .filter(condition)
                   .findFirst();

    }



}
